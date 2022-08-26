package AccuWeather.Models.Forecasts.v1;

/**
 * Информация о ветре
 */
public class Wind {
    private Measure Speed;
    private WindDirection Direction;

    @Override
    public String toString() {
        return Speed + " " + Direction;
    }
}
