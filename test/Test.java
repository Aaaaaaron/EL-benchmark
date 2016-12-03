import com.greenpineyu.fel.Expression;
import org.testng.AssertJUnit;
import org.wltea.expression.PreparedExpression;
import util.FelUtil;
import util.IKExpression;

import java.util.Map;
import java.util.Set;

/**
 * Created by AH on 2016/12/2.
 */
public class Test {
    @org.testng.annotations.Test
    public void testFel () {
        Set< String > variableContextFields = getContextFields( MockData.getVariableContextMap() );
        Expression expression = FelUtil.compile( MockData.getFelExp(), variableContextFields );
        AssertJUnit.assertEquals( FelUtil.evaluation( expression, MockData.getVariableContextMap() ), true );
    }

    @org.testng.annotations.Test
    public void testIKExpression () {
        Set< String > variableContextFields = getContextFields( MockData.getVariableContextMap() );
        PreparedExpression expression = IKExpression.compile( MockData.getIKExp(), variableContextFields );
        AssertJUnit.assertEquals( IKExpression.evaluation( expression, MockData.getVariableContextMap() ), true );
    }

    //测试fel编译过e1,然后编译e2 再执行e1的表达式看看e1的编译结果还在不在
    @org.testng.annotations.Test
    public void testFelWithIfReCompile () {
        Set< String > variableContextFields = getContextFields( MockData.getVariableContextMap() );
        Expression expression = FelUtil.compile( MockData.getFelExp(), variableContextFields );
        boolean result1 = FelUtil.evaluation( expression, MockData.getVariableContextMap() );
        System.out.println( "e1: " + result1 );

        Set< String > variableContextFields2 = getContextFields( MockData.getContextMap2() );
        Expression expression2 = FelUtil.compile( MockData.getFelExp2(), variableContextFields2 );
        boolean result2 = FelUtil.evaluation( expression2, MockData.getContextMap2() );
        System.out.println( "e2: " + result2 );

        //////////////////////////////////////////////////////////////////////////////////////////
        //Expression expression = FelUtil.compile( MockData.getFelExp(), variableContextFields );
        long start = System.currentTimeMillis();
        for ( int i = 0 ; i < 1000*10000 ; i++ ) {
            FelUtil.evaluation( expression, MockData.getVariableContextMap() );
        }
        System.out.println( "test fel: " + ( System.currentTimeMillis() - start ) );

    }

    private Set< String > getContextFields ( Map< String, String > context ) {
        return context.keySet();
    }
}

