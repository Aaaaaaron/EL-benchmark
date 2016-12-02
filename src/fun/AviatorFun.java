package fun;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorRuntimeJavaType;

import java.util.Map;

/**
 * Created by AH on 2016/12/2.
 */
public class AviatorFun extends AbstractFunction {
    @Override
    public AviatorObject call ( Map< String, Object > env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3 ) {
        String time = FunctionUtils.getStringValue( arg1, env );
        Number startHour = FunctionUtils.getNumberValue( arg2, env );
        Number endHour = FunctionUtils.getNumberValue( arg3, env );
        return new AviatorRuntimeJavaType( timeHourRange( time, startHour.intValue(), endHour.intValue() ) );
    }

    public static boolean timeHourRange ( String time, int startHour, int endHour ) {
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

    public String getName () {
        return "timeHourRange";
    }
}
