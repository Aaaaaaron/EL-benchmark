package fun;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

/**
 * Created by AH on 2016/12/2.
 */
public class TimeHourRange extends AbstractFunction {
    @Override
    public AviatorObject call ( Map< String, Object > env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3 ) {
        if ( arg1.getValue( env ) == null || arg2.getValue( env ) == null || arg3.getValue( env ) == null ) {
            return AviatorBoolean.FALSE;
        }
        String time = FunctionUtils.getStringValue( arg1, env );
        Number startHour = FunctionUtils.getNumberValue( arg2, env );
        Number endHour = FunctionUtils.getNumberValue( arg3, env );
        return timeHourRange( time, startHour.intValue(), endHour.intValue() );
    }

    private static AviatorBoolean timeHourRange ( String time, int startHour, int endHour ) {
        int timeValue = -1;
        timeValue = Integer.parseInt( time.substring( 11, 13 ) );
        if ( timeValue == -1 )
            return AviatorBoolean.FALSE;
        if ( startHour <= endHour ) {
            if ( timeValue >= startHour && timeValue <= endHour )
                return AviatorBoolean.TRUE;
            else
                return AviatorBoolean.FALSE;
        }
        return AviatorBoolean.FALSE;
    }

    public String getName () {
        return "timeHourRange";
    }
}
