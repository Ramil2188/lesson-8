package AccuWeather.Models.Forecasts.v1;

/**
 * Погода на несколько дней
 */
public class Daily {
    private Headline Headline;
    private DailyForecastsItem[] DailyForecasts;

    public Headline getHeadline() {
        return Headline;
    }

    public void setHeadline(Headline value) {
        Headline = value;
    }

    public DailyForecastsItem[] getDailyForecasts() {
        return DailyForecasts;
    }

    public void setDailyForecasts(DailyForecastsItem[] value) {
        DailyForecasts = value;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (Headline != null)
        {
            builder.append("Важная информация! " + Headline + "\n");
        }

        for (var dayForecasts: DailyForecasts) {
            builder.append("--------------------------------------------\n");
            builder.append(dayForecasts);
        }

        return builder.toString();
    }
}
