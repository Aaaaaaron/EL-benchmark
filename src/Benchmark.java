import strategy.EvaluatorStrategy;
import strategy.ExpressionEvaluate;
import strategy.factory.EvaluatorFactory;
import util.AviatorUtil;
import util.FelUtil;
import util.IKExpression;

import java.util.Map;
import java.util.Set;

/**
 * Created by AH on 2016/11/18.
 */
public class Benchmark {

    private static Set< String > variableContextFields;

    public static void main ( String[] args ) {
        variableContextFields = getContextField( MockData.getVariableContextMap() );
        int times = 1000 * 10000;
        benchmarkFelUseUtils( times );
        benchmarkFelUseStrategy( times );
        benchmarkAviUseUtils( times );
        benchmarkAviUseStrategy( times );
        benchmarkIKexpUseUtils( times );
        System.out.println();
    }

    private static void benchmarkFelUseUtils ( int times ) {
        long start = System.currentTimeMillis();
        for ( int i = 0 ; i < times ; i++ ) {
            FelUtil.evaluation( MockData.getFelExp(), MockData.getVariableContextMap() );
        }
        System.out.println( "test fel(use utils): " + ( System.currentTimeMillis() - start ) );
    }

    private static void benchmarkAviUseUtils ( int times ) {
        long start = System.currentTimeMillis();
        for ( int i = 0 ; i < times ; i++ ) {
            AviatorUtil.evaluation( MockData.getAviatorExp(), MockData.getVariableContextMap() );
        }
        System.out.println( "test aviator(use utils): " + ( System.currentTimeMillis() - start ) );
    }

    private static void benchmarkIKexpUseUtils ( int times ) {
        long start = System.currentTimeMillis();
        for ( int i = 0 ; i < times ; i++ ) {
            IKExpression.evaluation( MockData.getIKExp(), MockData.getVariableContextMap() );
        }
        System.out.println( "test ike(use utils): " + ( System.currentTimeMillis() - start ) );
    }

    private static void benchmarkFelUseStrategy ( int times ) {
        EvaluatorStrategy evaluator = EvaluatorFactory.createEvaluator( "fel" );
        ExpressionEvaluate expressionEvaluate = new ExpressionEvaluate( evaluator );
        long start = System.currentTimeMillis();
        for ( int i = 0 ; i < times ; i++ ) {
            expressionEvaluate.evaluation( MockData.getFelExp(), MockData.getVariableContextMap() );
        }
        System.out.println( "test fel(use strategy): " + ( System.currentTimeMillis() - start ) );
    }

    private static void benchmarkAviUseStrategy ( int times ) {
        AviatorUtil.regAviatorUtilMethod();
        EvaluatorStrategy evaluator = EvaluatorFactory.createEvaluator( "aviator" );
        ExpressionEvaluate expressionEvaluate = new ExpressionEvaluate( evaluator );
        long start = System.currentTimeMillis();
        for ( int i = 0 ; i < times ; i++ ) {
            expressionEvaluate.evaluation( MockData.getAviatorExp(), MockData.getVariableContextMap() );
        }
        System.out.println( "test aviator(use strategy): " + ( System.currentTimeMillis() - start ) );
    }

    private static Set< String > getContextField ( Map< String, Object > variableContext ) {
        return variableContext.keySet();
    }
}