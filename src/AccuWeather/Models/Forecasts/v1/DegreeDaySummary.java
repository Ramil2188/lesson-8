package AccuWeather.Models.Forecasts.v1;

/**
 * Экстремумы температуры в этот день
 */
public class DegreeDaySummary {
    private TemperaturePoint Heating;
    private TemperaturePoint Cooling;

    @Override
    public String toString() {
        return "от " + Cooling + " до " + Heating;
    }
}
