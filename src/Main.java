import com.greenpineyu.fel.Expression;
import org.wltea.expression.PreparedExpression;
import util.FelUtil;
import util.IKExpression;

import java.util.Map;
import java.util.Set;

/**
 * Created by AH on 2016/11/18.
 */
public class Main {

    private static Set< String > variableContextFields;

    public static void main ( String[] args ) {
        variableContextFields = getContextField( MockData.getVariableContextMap() );
        //int times = 1000 * 10000;
        int times = 1000;
        benchmarkFel( times );
        benchmarkIKexp( times );

    }

    private static void benchmarkIKexp ( int times ) {
        PreparedExpression preCompileExp = IKExpression.compile( MockData.getIKExp(), variableContextFields );
        long start2 = System.currentTimeMillis();
        for ( int i = 0 ; i < times ; i++ ) {
            IKExpression.evaluation( preCompileExp, MockData.getVariableContextMap());
        }
        System.out.println( "test ike: " + ( System.currentTimeMillis() - start2 ) );
    }

    private static void benchmarkFel ( int times ) {
        Expression expression = FelUtil.compile( MockData.getFelExp(), variableContextFields );
        long start = System.currentTimeMillis();
        for ( int i = 0 ; i < times ; i++ ) {
            FelUtil.evaluation( expression, MockData.getVariableContextMap() );
        }
        System.out.println( "test fel: " + ( System.currentTimeMillis() - start ) );
    }

    private static Set< String > getContextField ( Map< String, String > variableContext ) {
        return variableContext.keySet();
    }
}