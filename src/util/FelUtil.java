package util;

import com.greenpineyu.fel.Expression;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.context.FelContext;

import java.util.Map;
import java.util.Set;

/**
 * Created by AH on 2016/12/2.
 */
//todo 写着写着 FelUtil和IKEutil感觉好像可以抽成基类了
public class FelUtil {
    private static FelEngine engine;
    private static FelContext felContext;

    static {
        //engine = new FelEngineImpl();
        engine = FelEngine.instance;
        felContext = engine.getContext();
    }

    public static Expression compile ( String expression, Set< String > contextFields ) {
        //FelEngine engine = FelEngine.instance;
        setContextFieldsType( contextFields );
        return engine.compile( expression, null );
    }

    private static void setContextFieldsType ( Set< String > contextFields ) {
        for ( String field : contextFields ) {
            felContext.set( field, "" );//设置变量的filed的类型为String
        }
    }

    public static boolean evaluation ( Expression compileExp, Map< String, String > context ) {
        setContextFieldsValue( context );
        Object result = compileExp.eval( felContext );
        return result.toString().equals( "true" );
    }

    private static void setContextFieldsValue ( Map< String, String > context ) {
        for ( String key : context.keySet() ) {
            felContext.set( key, context.get( key ) );
        }
    }
}
