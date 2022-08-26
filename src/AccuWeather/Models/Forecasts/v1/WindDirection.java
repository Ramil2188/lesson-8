package AccuWeather.Models.Forecasts.v1;

/**
 * Направление ветра
 */
public class WindDirection {
    private double Degrees;
    private String Localized;
    private String English;

    @Override
    public String toString() {
        return Localized;
    }
}
