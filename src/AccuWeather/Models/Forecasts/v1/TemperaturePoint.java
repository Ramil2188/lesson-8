package AccuWeather.Models.Forecasts.v1;

import java.text.SimpleDateFormat;

/**
 * Данные о температуре
 */
public class TemperaturePoint {
    private double Value;
    private String Unit;
    private int UnitType;

    @Override
    public String toString() {
        return Value + Unit;
    }
}
