import com.greenpineyu.fel.Expression;
import com.greenpineyu.fel.FelEngine;
import org.testng.Assert;
import strategy.EvaluatorStrategy;
import strategy.ExpressionEvaluate;
import strategy.factory.EvaluatorFactory;
import util.AviatorUtil;

/**
 * Created by AH on 2016/12/2.
 */
public class TestUseStrategy {
    @org.testng.annotations.Test
    public void testFel2 () {
        FelEngine engine = FelEngine.instance;
        Expression expression = engine.compile( MockData.getFelExp(), MockData.getVariableContextMap() );
        System.out.println( expression.eval( MockData.getVariableContextMap() ) );
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

