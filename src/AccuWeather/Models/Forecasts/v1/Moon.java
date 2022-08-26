package AccuWeather.Models.Forecasts.v1;

import java.util.Date;

/**
 * Информация о лунном времени
 */
public class Moon {
    private Date Rise;
    private long EpochRise;
    private Date Set;
    private long EpochSet;
    private String Phase;
    private int Age;
}
