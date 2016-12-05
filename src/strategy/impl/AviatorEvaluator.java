package strategy.impl;

import com.googlecode.aviator.Expression;
import strategy.EvaluatorStrategy;

import java.util.Map;

/**
 * Created by AH on 2016/12/5.
 */
public class AviatorEvaluator implements EvaluatorStrategy {

    @Override
    public boolean evaluation ( String expression, Map< String, Object > context ) {
        Expression compiledExpression = getCompiledExpression( expression );
        return ( boolean ) compiledExpression.execute( context );
    }

    private static Expression getCompiledExpression ( String expression ) {
        return com.googlecode.aviator.AviatorEvaluator.compile( expression, true );
    }
}
