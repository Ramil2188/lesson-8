package AccuWeather.Models.Forecasts.v1;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Обобщенная информация о погоде днем/ночью
 */
public class WeatherSummary {
    private int Icon;
    private String IconPhrase;
    private boolean HasPrecipitation;
    private String ShortPhrase;
    private String LongPhrase;
    private int PrecipitationProbability;
    private int ThunderstormProbability;
    private int RainProbability;
    private int SnowProbability;
    private int IceProbability;
    private Wind Wind;
    private Wind WindGust;
    private Measure TotalLiquid;
    private Measure Rain;
    private Measure Snow;
    private Measure Ice;
    private int HoursOfPrecipitation;
    private int HoursOfRain;
    private int HoursOfSnow;
    private int HoursOfIce;
    private int CloudCover;

    @Override
    public String toString() {
        return IconPhrase.toLowerCase(Locale.ROOT) + ", ветер " + Wind;
    }
}
