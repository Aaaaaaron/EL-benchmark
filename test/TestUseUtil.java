import com.greenpineyu.fel.Expression;
import org.testng.AssertJUnit;
import org.wltea.expression.PreparedExpression;
import util.AviatorUtil;
import util.FelUtil;
import util.IKExpression;

import java.util.Map;
import java.util.Set;

/**
 * Created by AH on 2016/12/2.
 */
public class TestUseUtil {
    @org.testng.annotations.Test
    public void testFel () {
        Expression expression = FelUtil.compile( MockData.getFelExp(), MockData.getVariableContextMap() );
        AssertJUnit.assertEquals( FelUtil.evaluation( expression, MockData.getVariableContextMap() ), true );
        AssertJUnit.assertEquals( FelUtil.evaluation( expression, MockData.getWrongVariableContextMap() ), false );
    }

    @org.testng.annotations.Test
    public void testIKExpression () {
        Set< String > variableContextFields = getContextFields( MockData.getVariableContextMap() );
        PreparedExpression expression = IKExpression.compile( MockData.getIKExp(), variableContextFields );
        AssertJUnit.assertEquals( IKExpression.evaluation( expression, MockData.getVariableContextMap() ), true );
        AssertJUnit.assertEquals( IKExpression.evaluation( expression, MockData.getWrongVariableContextMap() ), false);
    }

    @org.testng.annotations.Test
    public void testAviator () {
        AviatorUtil.regAviatorUtilMethod();
        com.googlecode.aviator.Expression expression = AviatorUtil.compile( MockData.getAviatorExp() );
        AssertJUnit.assertEquals( AviatorUtil.evaluation( expression, MockData.getVariableContextMap() ), true );
        AssertJUnit.assertEquals( AviatorUtil.evaluation( expression, MockData.getWrongVariableContextMap() ), false );
    }

    //测试fel编译缓存
    @org.testng.annotations.Test
    public void testFelCompileCache () {
        Set< String > variableContextFields = getContextFields( MockData.getVariableContextMap() );
        Expression expression = FelUtil.compile( MockData.getFelExp(), MockData.getVariableContextMap() );

        //////////////////////////////////////////////////////////////////////////////////////////
        long start = System.currentTimeMillis();
        Expression expression3 = FelUtil.compile( MockData.getFelExp(), MockData.getVariableContextMap(), false );
        for ( int i = 0 ; i < 1 ; i++ ) {
            FelUtil.evaluation( expression3, MockData.getVariableContextMap() );
        }
        System.out.println( "test fel not cache: " + ( System.currentTimeMillis() - start ) );

        long start2 = System.currentTimeMillis();
        Expression expression4 = FelUtil.compile( MockData.getFelExp(), MockData.getVariableContextMap() );
        for ( int i = 0 ; i < 1 ; i++ ) {
            FelUtil.evaluation( expression3, MockData.getVariableContextMap() );
        }
        System.out.println( "test fel cached: " + ( System.currentTimeMillis() - start2 ) );

    }

    private Set< String > getContextFields ( Map< String, Object > context ) {
        return context.keySet();
    }
}

