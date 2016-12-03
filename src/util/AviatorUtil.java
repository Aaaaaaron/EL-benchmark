package util;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import fun.AviatorFun;

import java.util.Map;

/**
 * Created by AH on 2016/12/2.
 */
public class AviatorUtil {

    public static Expression compile ( String expression ) {
        AviatorEvaluator.addFunction( new AviatorFun() );
        //开启缓存
        return AviatorEvaluator.compile( expression, true );
    }

    public static boolean evaluation ( Expression compileExpression, Map< String, Object > context ) {
        return ( boolean ) compileExpression.execute( context );
    }
}
