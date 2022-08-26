package AccuWeather.Services.Forecasts.v1;

import AccuWeather.Models.Forecasts.v1.Daily;
import AccuWeather.Models.Forecasts.v1.DailyForecastsItem;
import AccuWeather.Models.Forecasts.v1.Headline;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Класс хранения данных погоды
 */
public class StorageService {
    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "/accuweather.forecasts.db";

    private static StorageService instance = null;

    public static synchronized StorageService getInstance(String path) throws SQLException {
        if (instance == null) {
            instance = new StorageService(path);
        }

        return instance;
    }

    private Connection connection;
    private Gson gson;

    private StorageService(String path) throws SQLException {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + path + CON_STR);
        gson = new GsonBuilder().setPrettyPrinting().create();
        InitDatabase();
    }

    private void InitDatabase() {
        try (Statement statement = this.connection.createStatement()) {
            statement.execute("CREATE TABLE if not exists " +
                    "'daily_forecast' (" +
                    "'id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "'update_date' DATE," +
                    "'city' VARCHAR(128)," +
                    "'forecast_date' DATE, " +
                    "'details' TEXT," +
                    "CONSTRAINT daily_forecast_constraint_city_date " +
                        "UNIQUE (city, forecast_date));");

            statement.execute("CREATE INDEX if not exists daily_forecast_index_city_date ON daily_forecast(city, forecast_date);");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Создание или обновление данных по таблице
     * @param city
     * @param daily
     */
    public void UpsertDailyForecasts(String city, Daily daily) {
        for (var day: daily.getDailyForecasts()) {
            UpsertDailyForecastItem(city, day);
        }
    }

    /**
     * Получение погоды в городе на определенную дату
     * @param city
     * @param date
     * @return
     */
    public Daily GetFullDailyForecasts(String city, Date date) {
        var result = new Daily();
        var forecasts = new ArrayList<DailyForecastsItem>();
        try (PreparedStatement statement = this.connection.prepareStatement(
                "SELECT details FROM daily_forecast WHERE city = ? AND forecast_date = ?")) {
            statement.setObject(1, city);
            statement.setObject(2, date);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                var details = resultSet.getObject(1);
                if (details != null) {
                    forecasts.add(gson.fromJson(details.toString(), DailyForecastsItem.class));
                }
            }
            var forecastsArray = new DailyForecastsItem[forecasts.size()];
            var i = 0;
            for (var item: forecasts) {
                forecastsArray[i] = item;
                i++;
            }
            result.setDailyForecasts(forecastsArray);

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            for(Throwable ex : e) {
                System.err.println("Error occurred " + ex);
            }
            System.out.println("Error in fetching data");
        }

        return null;
    }

    private void UpsertDailyForecastItem(String city, DailyForecastsItem item) {
        try (PreparedStatement statement = this.connection.prepareStatement("INSERT INTO " +
                "`daily_forecast` (update_date, city, forecast_date, details) " +
                "VALUES(?, ?, ?, ?)" +
                "ON CONFLICT(city, forecast_date) DO " +
                "UPDATE SET details = ?;")) {

            var details = gson.toJson(item);

            statement.setObject(1, new Date());
            statement.setObject(2, city);
            statement.setObject(3, item.getDate());
            statement.setObject(4, details);
            statement.setObject(5, details);

            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
