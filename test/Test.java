import com.greenpineyu.fel.Expression;
import org.testng.AssertJUnit;
import org.wltea.expression.PreparedExpression;
import util.FelUtil;
import util.IKExpression;

import java.util.Set;

/**
 * Created by AH on 2016/12/2.
 */
public class Test {
    @org.testng.annotations.Test
    public void testFel () {
        Set< String > variableContextFields = getContextFields();
        Expression expression = FelUtil.compile( MockData.getFelExp(), variableContextFields );
        AssertJUnit.assertEquals( FelUtil.evaluation( expression, MockData.getVariableContextMap() ), true );
    }

    @org.testng.annotations.Test
    public void testIKExpression () {
        Set< String > variableContextFields = getContextFields();
        PreparedExpression expression = IKExpression.compile( MockData.getIKExp(), variableContextFields );
        AssertJUnit.assertEquals( IKExpression.evaluation( expression, MockData.getVariableContextMap() ), true );
    }

    //测试fel编译过e1,然后编译e2 再执行e1的表达式看看e1的编译结果还在不在
    @org.testng.annotations.Test
    public void testFelWithIfReCompile () {
        Set< String > variableContextFields = getContextFields();
        Expression expression = FelUtil.compile( MockData.getFelExp(), variableContextFields );
        System.out.println( "e1的执行结果: " + FelUtil.evaluation( expression, MockData.getVariableContextMap() ) );
    }

    private Set< String > getContextFields () {
        return MockData.getVariableContextMap().keySet();
    }
}

