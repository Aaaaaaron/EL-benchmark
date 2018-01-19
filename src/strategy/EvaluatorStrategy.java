package strategy;

import java.util.Map;

/**
 * Created by Melody on 2016/12/3.
 */
public interface EvaluatorStrategy {
    boolean evaluation(final String expression, final Map<String, Object> context);
}
