package strategy;

import java.util.Map;

/**
 * Created by AH on 2016/12/5.
 */
public class ExpressionEvaluate {
    private EvaluatorStrategy evaluator;

    public ExpressionEvaluate(EvaluatorStrategy evaluator) {
        this.evaluator = evaluator;
    }

    public boolean evaluation(final String expression, final Map<String, Object> context) {
        return evaluator.evaluation(expression, context);
    }

}
