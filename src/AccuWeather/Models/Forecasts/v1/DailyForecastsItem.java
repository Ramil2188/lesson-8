package AccuWeather.Models.Forecasts.v1;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Информация о погоде на день
 */
public class DailyForecastsItem {
    private Date Date;
    private long EpochDate;
    private Sun Sun;
    private Moon Moon;
    private TemperatureRange Temperature;
    private TemperatureRange RealFeelTemperature;
    private TemperatureRange RealFeelTemperatureShade;
    private double HoursOfSun;
    private DegreeDaySummary DegreeDaySummary;

    private Object AirAndPollen;

    private WeatherSummary Day;
    private WeatherSummary Night;

    private String MobileLink;
    private String Link;

    public Date getDate() {
        return Date;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder builder = new StringBuilder("Погода на " + formatDate.format(Date) + "\n");
        builder.append("Температура будет колебаться " + Temperature + " (ощущается как " + RealFeelTemperature + ").\n");
        builder.append("Днем " + Day + "\n");
        builder.append("Ночью " + Night + "\n");
        builder.append("Экстремумы температуры в этот день: " + DegreeDaySummary + "\n");

        return builder.toString();
    }
}
