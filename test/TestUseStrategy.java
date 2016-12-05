import org.testng.Assert;
import org.testng.AssertJUnit;
import org.wltea.expression.PreparedExpression;
import strategy.ExpressionEvaluate;
import strategy.impl.FelEvaluate;
import util.IKExpression;

import java.util.Map;
import java.util.Set;

/**
 * Created by AH on 2016/12/2.
 */
public class TestUseStrategy {
    @org.testng.annotations.Test
    public void testFel () {
        ExpressionEvaluate expressionEvaluate = new ExpressionEvaluate( new FelEvaluate() );
        Assert.assertEquals( expressionEvaluate.evaluation( MockData.getFelExp(), MockData.getVariableContextMap() ), true );
    }

    @org.testng.annotations.Test
    public void testIKExpression () {
        Set< String > variableContextFields = getContextFields( MockData.getVariableContextMap() );
        PreparedExpression expression = IKExpression.compile( MockData.getIKExp(), variableContextFields );
        AssertJUnit.assertEquals( IKExpression.evaluation( expression, MockData.getVariableContextMap() ), true );
    }


    private Set< String > getContextFields ( Map< String, Object > context ) {
        return context.keySet();
    }
}

