package strategy.factory;

import strategy.EvaluatorStrategy;
import strategy.impl.AviatorEvaluator;
import strategy.impl.FelEvaluator;

/**
 * Created by AH on 2016/12/5.
 */
public class EvaluatorFactory {
    public static EvaluatorStrategy createEvaluator(String type) {
        switch (type) {
        case "fel":
            return new FelEvaluator();
        case "aviator":
            return new AviatorEvaluator();
        default:
            return new FelEvaluator();
        }
    }
}