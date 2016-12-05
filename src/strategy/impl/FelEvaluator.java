package strategy.impl;

import com.greenpineyu.fel.Expression;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.context.ArrayCtxImpl;
import com.greenpineyu.fel.context.FelContext;
import com.greenpineyu.fel.exception.CompileException;
import strategy.EvaluatorStrategy;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;

/**
 * Created by AH on 2016/12/5.
 */
public class FelEvaluator implements EvaluatorStrategy {
    private final static ConcurrentHashMap< String, FutureTask< Expression > > cachedExpressions
            = new ConcurrentHashMap<>();


    @Override
    public final boolean evaluation ( String expression, Map< String, Object > context ) {
        if ( expression == null || expression.trim().length() == 0 || context.isEmpty() )
            throw new CompileException( "Blank expression or context" );

        Expression compiledExpression =
                getCompiledExpression( expression, getContextFields( context ), true );
        Object result = compiledExpression.eval( context );
        return result.toString().equals( "true" );
    }

    private static Expression getCompiledExpression ( final String expression, final Set< String > contextFields, final boolean cached ) {
        if ( cached ) {
            FutureTask< Expression > task = cachedExpressions.get( expression );
            if ( task != null )
                return getCachedCompiledExpression( expression, task );

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
            return getCachedCompiledExpression( expression, existedTask );

        }
        else
            return innerCompile( expression, contextFields );
    }

    private static Expression getCachedCompiledExpression ( final String expression, final FutureTask< Expression > task ) {
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

    private static Set< String > getContextFields ( Map< String, Object > context ) {
        return context.keySet();
    }

}
