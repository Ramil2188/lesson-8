package AccuWeather.Models.Forecasts.v1;

import java.util.Date;

/**
 * Общая информация о погоде
 */
public class Headline {
    private Date EffectiveDate;
    private long EffectiveEpochDate;
    private int Severity;
    private String Text;
    private String Category;
    private Date EndDate;
    private int EndEpochDate;
    private String MobileLink;
    private String Link;

    public Date getEffectiveDate() {
        return EffectiveDate;
    }

    @Override
    public String toString() {
        return Text;
    }
}
