package util;

import com.greenpineyu.fel.Expression;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.ArrayCtxImpl;
import com.greenpineyu.fel.context.FelContext;

import java.util.Map;
import java.util.Set;

/**
 * Created by AH on 2016/12/2.
 */
//todo 写着写着 FelUtil和IKEutil感觉好像可以抽成基类了
public class FelUtil {

    //写个带缓存的编译
    public static Expression compile ( String expression, Set< String > contextFields ) {
        FelEngine engine = new FelEngineImpl();
        setContextFieldsType( contextFields, engine.getContext() );
        return engine.compile( expression, null );
    }

    private static void setContextFieldsType ( Set< String > contextFields, FelContext felContext ) {
        for ( String field : contextFields ) {
            felContext.set( field, "" );//设置变量的filed的类型为String
        }
    }

    //接口的抽象方法 compile好像是抽不出来了 有个接口就好策略模式了
    public static boolean evaluation ( Expression compileExp, Map< String, String > context ) {
        FelContext felContext = new ArrayCtxImpl();
        setContextFieldsValue( context, felContext);
        Object result = compileExp.eval( felContext );
        return result.toString().equals( "true" );
    }

    private static void setContextFieldsValue ( Map< String, String > contextValue, FelContext felContext ) {
        for ( String key : contextValue.keySet() ) {
            felContext.set( key, contextValue.get( key ) );
        }
    }
}
