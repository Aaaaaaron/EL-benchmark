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

    //测试fel编译缓存
    @org.testng.annotations.Test
    public void testFelCompileCache () {
        Set< String > variableContextFields = getContextFields( MockData.getVariableContextMap() );
        Expression expression = FelUtil.compile( MockData.getFelExp(), variableContextFields );

        //////////////////////////////////////////////////////////////////////////////////////////
        long start = System.currentTimeMillis();
        Expression expression3 = FelUtil.compile( MockData.getFelExp(), variableContextFields,false );
        for ( int i = 0 ; i < 1 ; i++ ) {
            FelUtil.evaluation( expression3, MockData.getVariableContextMap() );
        }
        System.out.println( "test fel not cache: " + ( System.currentTimeMillis() - start ) );

        long start2 = System.currentTimeMillis();
        Expression expression4 = FelUtil.compile( MockData.getFelExp(), variableContextFields);
        for ( int i = 0 ; i < 1 ; i++ ) {
            FelUtil.evaluation( expression3, MockData.getVariableContextMap() );
        }
        System.out.println( "test fel cached: " + ( System.currentTimeMillis() - start2 ) );

    }

    private Set< String > getContextFields ( Map< String, String > context ) {
        return context.keySet();
    }
}

