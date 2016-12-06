package util;

import com.greenpineyu.fel.Expression;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.exception.CompileException;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;

/**
 * Created by AH on 2016/12/2.
 */
public final class FelUtil {
    private final static ConcurrentHashMap< String, FutureTask< Expression > > cachedExpressions
            = new ConcurrentHashMap<>();

    public static Expression compile ( final String expression, final Map< String, Object > context ) {
        if ( expression == null || expression.trim().length() == 0 )
            throw new CompileException( "Blank expression" );

        return compile( expression, context, true );
    }

    public static Expression compile ( final String expression, final Map< String, Object > context, final boolean cached ) {
        if ( cached ) {
            FutureTask< Expression > task = cachedExpressions.get( expression );
            if ( task != null )
                return getCompiledExpression( expression, task );

            task = new FutureTask<>( new Callable< Expression >() {
                @Override
                public Expression call () throws Exception {
                    return innerCompile( expression );
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
            return innerCompile( expression );
    }

    private static Expression getCompiledExpression ( final String expression, final FutureTask< Expression > task ) {
        try {
            return task.get();
        } catch ( Exception e ) {
            cachedExpressions.remove( expression );
            throw new CompileException( "Compile expression failure:" + expression, e );
        }
    }

    private static Expression innerCompile ( final String expression ) {
        FelEngine engine = FelEngine.instance;
        return engine.compile( expression );
    }

    public static boolean evaluation ( final Expression compileExp, final Map< String, Object > context ) {
        Object result = compileExp.eval( context );
        return result.toString().equals( "true" );
    }
}
