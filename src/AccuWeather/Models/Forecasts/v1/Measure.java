package AccuWeather.Models.Forecasts.v1;

import java.util.Locale;

/**
 * Измерение
 */
public class Measure {
    private double Value;
    private String Unit;
    private int UnitType;

    @Override
    public String toString() {
        return Value + Unit;
    }
}
