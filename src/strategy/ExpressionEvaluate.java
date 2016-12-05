package strategy;

import java.util.Map;

/**
 * Created by AH on 2016/12/5.
 */
//todo 这个命名怎么命名好呢
public class ExpressionEvaluate {
    private EvaluatorStrategy evaluator;

    public ExpressionEvaluate ( EvaluatorStrategy evaluator ) {
        this.evaluator = evaluator;
    }

    public boolean evaluation ( final String expression, final Map< String, Object > context ) {
        return evaluator.evaluation( expression, context );
    }

}
