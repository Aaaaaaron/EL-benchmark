package strategy.impl;

import fun.TimeHourRange;
import strategy.EvaluatorStrategy;

import java.util.Map;

/**
 * Created by AH on 2016/12/5.
 */
public class AviatorEvaluator implements EvaluatorStrategy {
    static {
        com.googlecode.aviator.AviatorEvaluator.addFunction( new TimeHourRange() );
    }

    @Override
    public boolean evaluation ( String expression, Map< String, Object > context ) {
        return ( boolean ) com.googlecode.aviator.AviatorEvaluator.compile( expression, true ).execute( context );
    }
}
