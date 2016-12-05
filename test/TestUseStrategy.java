import com.googlecode.aviator.AviatorEvaluator;
import com.greenpineyu.fel.Expression;
import com.greenpineyu.fel.FelEngine;
import org.testng.Assert;
import strategy.EvaluatorStrategy;
import strategy.ExpressionEvaluate;
import strategy.factory.EvaluatorFactory;
import util.AviatorUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AH on 2016/12/2.
 */
public class TestUseStrategy {
    @org.testng.annotations.Test
    public void testFel2 () {
        FelEngine engine = FelEngine.instance;
        String s = "a + 5";

        Map< String, Object > context = new HashMap<>();
        context.put( "a", 5 );
        Expression expression = engine.compile( s,context );
        System.out.println( expression.eval( context ) );
    }

    @org.testng.annotations.Test
    public void testAviator2 () {
        String s = "a > 5";
        com.googlecode.aviator.Expression expression = AviatorEvaluator.compile( s, true );
        Map< String, Object > context = new HashMap<>();
        context.put( "a", 6000000000000000000L );
        System.out.println( expression.execute( context ) );
    }

    @org.testng.annotations.Test
    public void testFel () {
        EvaluatorStrategy evaluator = EvaluatorFactory.createEvaluator( "fel" );
        ExpressionEvaluate expressionEvaluate = new ExpressionEvaluate( evaluator );
        Assert.assertEquals( expressionEvaluate.evaluation( MockData.getFelExp(), MockData.getVariableContextMap() ), true );
        Assert.assertEquals( expressionEvaluate.evaluation( MockData.getFelExp(), MockData.getWrongVariableContextMap() ), false );
    }

    @org.testng.annotations.Test
    public void testAviator () {
        AviatorUtil.regAviatorUtilMethod();
        EvaluatorStrategy evaluator = EvaluatorFactory.createEvaluator( "aviator" );
        ExpressionEvaluate expressionEvaluate = new ExpressionEvaluate( evaluator );
        Assert.assertEquals( expressionEvaluate.evaluation( MockData.getAviatorExp(), MockData.getVariableContextMap() ), true );
        Assert.assertEquals( expressionEvaluate.evaluation( MockData.getAviatorExp(), MockData.getWrongVariableContextMap() ), false );
    }
}

