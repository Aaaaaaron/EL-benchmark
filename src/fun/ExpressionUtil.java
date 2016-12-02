package fun;

/**
 * Created by AH on 2016/11/18.
 */
public class ExpressionUtil {

    public static String timeHourRange ( String time, int startHour, int endHour ) {
        int timeValue = -1;
        timeValue = Integer.parseInt( time.substring( 11, 13 ) );
        if ( timeValue == -1 )
            return "false";
        if ( startHour <= endHour ) {
            if ( timeValue >= startHour && timeValue <= endHour )
                return "true";
            else
                return "false";
        }
        return "false";
    }

    public boolean TimeHourRange ( String time, int startHour, int endHour ) {
        int timeValue = -1;
        timeValue = Integer.parseInt( time.substring( 11, 13 ) );
        if ( timeValue == -1 )
            return false;
        if ( startHour <= endHour ) {
            if ( timeValue >= startHour && timeValue <= endHour )
                return true;
            else
                return false;
        }
        return false;
    }
}
