package util;

import com.greenpineyu.fel.Expression;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.context.ArrayCtxImpl;
import com.greenpineyu.fel.context.FelContext;
import com.greenpineyu.fel.exception.CompileException;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;

/**
 * Created by AH on 2016/12/2.
 */
//todo 阿杜让我适配各个实现,我这里做的都是静态方法,并且会有中间变量 compiledExpression的产生
public final class FelUtil {
    private final static ConcurrentHashMap< String, FutureTask< Expression > > cachedExpressions
            = new ConcurrentHashMap<>();

    public static Expression compile ( final String expression, final Set< String > contextFields ) {
        if ( expression == null || expression.trim().length() == 0 )
            throw new CompileException( "Blank expression" );

        return compile( expression, contextFields, true );
    }

    public static Expression compile ( final String expression, final Set< String > contextFields, final boolean cached ) {
        if ( cached ) {
            FutureTask< Expression > task = cachedExpressions.get( expression );
            if ( task != null )
                return getCompiledExpression( expression, task );

            task = new FutureTask<>( new Callable< Expression >() {
                @Override
                public Expression call () throws Exception {
                    return innerCompile( expression, contextFields );
                }
            } );

            FutureTask< Expression > existedTask = cachedExpressions.putIfAbsent( expression, task );
            if ( existedTask == null ) {
                existedTask = task;
                existedTask.run();
            }
            return getCompiledExpression( expression, existedTask );

        }
        else
            return innerCompile( expression, contextFields );
    }

    private static Expression getCompiledExpression ( final String expression, final FutureTask< Expression > task ) {
        try {
            return task.get();
        } catch ( Exception e ) {
            cachedExpressions.remove( expression );
            throw new CompileException( "Compile expression failure:" + expression, e );
        }
    }

    private static Expression innerCompile ( final String expression, final Set< String > contextFields ) {
        FelEngine engine = FelEngine.instance;
        FelContext felContext = new ArrayCtxImpl();
        setContextFieldsType( contextFields, felContext );
        return engine.compile( expression, felContext );
    }

    private static void setContextFieldsType ( final Set< String > contextFields, final FelContext felContext ) {
        for ( String field : contextFields ) {
            felContext.set( field, "" );//设置变量的filed的类型为String
        }
    }

    //接口的抽象方法 compile好像是抽不出来了 有个接口就好策略模式了
    public static boolean evaluation ( final Expression compileExp, final Map< String, Object > context ) {
        FelContext felContext = new ArrayCtxImpl( context );
        Object result = compileExp.eval( felContext );
        return result.toString().equals( "true" );
    }
}
