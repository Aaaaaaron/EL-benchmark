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
    public void testFelUseOriApi () {
        FelEngine engine = FelEngine.instance;
        String s =
                "( e1_deviceCat == '/Application' " +
                        "|| e1_deviceCat == '/IDS/Network' " +
                        "|| e1_deviceCat == '/Firewall' ) " +
                        "&& e1_catBehavior == '/Authentication/Add' " +
                        "&& e1_catTechnique == '/TrafficAnomaly/NetWorkLayer' " +
                        "&& e1_catObject == '/Host/Application/Service' " +
                        "&& e1_destAddress != null " +
                        "&& $('fun.ExpressionUtil').timeHourRange(e1_startTime,0,13)";

        Expression expression = engine.compile( s );
        Map< String, Object > testContext = new HashMap<>();
        testContext.put( "e1_deviceCat", "/Application" );
        testContext.put( "123124e1_startTime", "2016-03-02 12:57:52" );

        System.out.println( expression.eval( testContext ) );
    }

    @org.testng.annotations.Test
    public void testAviatorWithMutableContext () {
        String s = "a + 5";
        com.googlecode.aviator.Expression expression = AviatorEvaluator.compile( s, true );
        Map< String, Object > context = new HashMap<>();
        context.put( "a", "5" );
        Assert.assertEquals( expression.execute( context ), "55" );

        Map< String, Object > context2 = new HashMap<>();
        context2.put( "a", 5 );
        Assert.assertEquals( expression.execute( context2 ), 10L );
    }

    @org.testng.annotations.Test
    public void testFelWithMutableContext () {
        FelEngine engine = FelEngine.instance;
        String s = "a + 5";
        Expression expression = engine.compile( s );
        Map< String, Object > context = new HashMap<>();
        context.put( "a", "5" );
        Assert.assertEquals( expression.eval( context ), "55" );

        Map< String, Object > context2 = new HashMap<>();
        context2.put( "a", 5L );
        Assert.assertEquals( expression.eval( context2 ), 10 );
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

