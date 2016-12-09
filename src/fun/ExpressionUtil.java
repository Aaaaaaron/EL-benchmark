package fun;

/**
 * Created by AH on 2016/11/18.
 */
public class ExpressionUtil {

    public static boolean timeHourRange ( String time, int startHour, int endHour ) {
        if ( time == null || time.length() == 0 ) {
            return false;
        }
        int timeValue = -1;
        timeValue = Integer.parseInt( time.substring( 11, 13 ) );
        return timeValue != -1 && startHour <= endHour && timeValue >= startHour && timeValue <= endHour;
    }

}
