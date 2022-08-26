package AccuWeather.Models.Forecasts.v1;

import java.text.SimpleDateFormat;

/**
 * Температурный диапазон
 */
public class TemperatureRange {
    private TemperaturePoint Minimum;
    private TemperaturePoint Maximum;

    @Override
    public String toString() {
        return "от " + Minimum + " до " + Maximum;
    }
}
