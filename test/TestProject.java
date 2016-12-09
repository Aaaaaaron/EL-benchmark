import com.googlecode.aviator.AviatorEvaluator;
import com.greenpineyu.fel.Expression;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.context.ArrayCtxImpl;
import com.greenpineyu.fel.context.FelContext;
import com.greenpineyu.fel.function.CommonFunction;
import com.greenpineyu.fel.function.Function;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import strategy.EvaluatorStrategy;
import strategy.ExpressionEvaluate;
import strategy.factory.EvaluatorFactory;
import util.AviatorUtil;
import util.FelUtil;
import util.IKExpression;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by AH on 2016/12/5.
 */
public class TestProject {
    private Map< String, String > getMap () {
        Map< String, String > map = new HashMap<>();
        map.put( "a", "a" );
        map.put( "b", "c" );
        map.put( "severity", "1" );
        return map;
    }

    @Test
    void test () {
        Map< String, String > map = getMap();
        Map< String, Object > map1 = new HashMap<>( map );
        if ( map1.containsKey( "severity" ) ) {
            map1.put( "severity", 5 );
        }
        map.clear();
        System.out.println( "a" );
    }

    @Test
    public void testR () {
        String s =
                "( e1_deviceCat == \"/Application\" " +
                        "|| e1_deviceCat == \"/IDS/Network\" " +
                        "|| e1_deviceCat == \"/Firewall\" ) " +
                        "&& e1_catBehavior == \"/Authentication/Add\" " +
                        "&& e1_severity >= 4 " +
                        "&& e1_catTechnique == \"/TrafficAnomaly/NetWorkLayer\" " +
                        "&& e1_catObject == \"/Host/Application/Service\" " +
                        "&& e1_destAddress != null " +
                        "&& $timeHourRange(e1_startTime,0,13)";
        String s2 = s.replace( "$", "$('fun.ExpressionUtil')." );
        //System.out.println( s2 );
        String s3 = s.replaceAll( "\\be\\d_", "" );
        System.out.println( s3 );
    }

    @Test
    public void testFelWithType () {
        String s =
                "( e1_deviceCat == \"/Application\" " +
                        "|| e1_deviceCat == \"/IDS/Network\" " +
                        "|| e1_deviceCat == \"/Firewall\" ) " +
                        "&& e1_catBehavior == \"/Authentication/Add\" " +
                        "&& e1_severity >= 4 " +
                        "&& e1_catTechnique == \"/TrafficAnomaly/NetWorkLayer\" " +
                        "&& e1_catObject == \"/Host/Application/Service\" " +
                        "&& e1_destAddress != null " +
                        "&& $('fun.ExpressionUtil').timeHourRange(e1_startTime,0,13)";
        FelEngine felEngine = FelEngine.instance;
        Expression expression = felEngine.compile( s );
        FelContext felContext = new ArrayCtxImpl();
        felContext.set( "e1_deviceCat", "/Application" );
        felContext.set( "e1_catBehavior", "/Authentication/Add" );
        felContext.set( "e1_severity", 5 );
        felContext.set( "e1_catTechnique", "/TrafficAnomaly/NetWorkLayer" );
        felContext.set( "e1_catObject", "/Host/Application/Service" );
        felContext.set( "e1_destAddress", "1.1.1.1" );
        //felContext.set( "e1_startTime", "2016-03-02 12:57:52" );
        felContext.set( "e1_startTime", null );
        System.out.println( expression.eval( felContext ) );
    }

    @Test
    public void testAviMethod () {
        String s = "( e1_deviceCat == '/Application' " +
                "|| e1_deviceCat == '/IDS/Network' " +
                "|| e1_deviceCat == '/Firewall' ) " +
                "&& e1_catBehavior == '/Authentication/Add' " +
                "&& e1_severity >= 4 " +
                "&& e1_catTechnique == '/TrafficAnomaly/NetWorkLayer' " +
                "&& e1_catObject == '/Host/Application/Service' " +
                "&& e1_destAddress != nil " +
                "&& timeHourRange(e1_startTime,0,13)";

        Map< String, Object > testContext = new HashMap<>();
        testContext.put( "e1_deviceCat", "/Application" );
        testContext.put( "e1_catBehavior", "/Authentication/Add" );
        testContext.put( "e1_severity", Integer.valueOf( "5" ) );
        testContext.put( "e1_catTechnique", "/TrafficAnomaly/NetWorkLayer" );
        testContext.put( "e1_catObject", "/Host/Application/Service" );
        testContext.put( "e1_destAddress", "1.1.1.1" );
        testContext.put( "e1_startTime", "nil" );
        AviatorUtil.regAviatorUtilMethod();
        EvaluatorStrategy evaluator = EvaluatorFactory.createEvaluator( "aviator" );
        ExpressionEvaluate expressionEvaluate = new ExpressionEvaluate( evaluator );
        System.out.println( expressionEvaluate.evaluation( s, testContext ) );
    }

    @Test
    public void test03 () {
        EvaluatorStrategy evaluator = EvaluatorFactory.createEvaluator( "fel" );
        ExpressionEvaluate expressionEvaluate = new ExpressionEvaluate( evaluator );
        Assertions.assertEquals( expressionEvaluate.evaluation( MockData.getFelExp(), MockData.getVariableContextMap() ), true );
        Assertions.assertEquals( expressionEvaluate.evaluation( MockData.getFelExp(), MockData.getWrongVariableContextMap() ), false );
    }

    @Test
    public void test04 () {
        String s = "src != null";
        FelEngine engine = FelEngine.instance;
        Expression expression = engine.compile( s );
        Map< String, Object > context = new HashMap<>();
        context.put( "src", null );
        System.out.println( expression.eval( context ) );
    }

    @Test
    public void test05 () {
        String s = "src != nil";
        com.googlecode.aviator.Expression expression = AviatorEvaluator.compile( s, true );
        Map< String, Object > context = new HashMap<>();
        context.put( "src", 1 );
        System.out.println( expression.execute( context ) );
    }

    @Test
    public void test08 () {
        String s = "a > 3";
        com.googlecode.aviator.Expression expression = AviatorEvaluator.compile( s, true );
        Map< String, Object > context = new HashMap<>();
        //context.put( "src", 1 );
        System.out.println( expression.execute( context ) );
    }

    @Test
    public void test07 () {
        AviatorUtil.regAviatorUtilMethod();
        String s = "( e1_deviceCat == '/Application' " +
                "|| e1_deviceCat == '/IDS/Network' " +
                "|| e1_deviceCat == '/Firewall' ) " +
                "&& e1_catBehavior == '/Authentication/Add' " +
                "&& e1_severity >= 4 " +
                "&& e1_catTechnique == '/TrafficAnomaly/NetWorkLayer' " +
                "&& e1_catObject == '/Host/Application/Service' " +
                "&& e1_destAddress != nil " +
                "&& timeHourRange(e1_startTime,0,13)";
        com.googlecode.aviator.Expression expression = AviatorEvaluator.compile( s, true );
        Map< String, Object > context = new HashMap<>();
        //context.put( "src", 1 );
        System.out.println( expression.execute( context ) );
    }

    @Test
    public void test06 () {
        String s = "( e1_deviceCat == '/Application' " +
                "|| e1_deviceCat == '/IDS/Network' " +
                "|| e1_deviceCat == '/Firewall' ) " +
                "&& e1_catBehavior == '/Authentication/Add' " +
                "&& e1_severity >= 4 " +
                "&& e1_catTechnique == '/TrafficAnomaly/NetWorkLayer' " +
                "&& e1_catObject == '/Host/Application/Service' " +
                "&& e1_destAddress != nil " +
                "&& $('fun.ExpressionUtil').timeHourRange(e1_startTime,0,13)";
        FelEngine engine = FelEngine.instance;
        Expression expression = engine.compile( s );
        Map< String, Object > testContext = new HashMap<>();
        testContext.put( "e1_deviceCat", "/Application" );
        testContext.put( "e1_catBehavior", "/Authentication/Add" );
        testContext.put( "e1_severity", Integer.valueOf( "5" ) );
        testContext.put( "e1_catTechnique", "/TrafficAnomaly/NetWorkLayer" );
        testContext.put( "e1_catObject", "/Host/Application/Service" );
        String nil = "null";
        testContext.put( "e1_destAddress", nil );
        //testContext.put( "e1_startTime", "2016-03-02 12:57:52" );
        System.out.println( expression.eval( testContext ) );
    }

    @Test
    public void testFelWithMethod () {
        String s = "( e1_deviceCat == '/Application' " +
                "|| e1_deviceCat == '/IDS/Network' " +
                "|| e1_deviceCat == '/Firewall' ) " +
                "&& e1_catBehavior == '/Authentication/Add' " +
                "&& e1_severity >= 4 " +
                "&& e1_catTechnique == '/TrafficAnomaly/NetWorkLayer' " +
                "&& e1_catObject == '/Host/Application/Service' " +
                "&& e1_destAddress != nil " +
                "&& TimeHourRange(e1_startTime,0,13)";
        Function fun = new CommonFunction() {
            @Override
            public Object call ( Object[] arguments ) {
                if ( arguments != null && arguments.length > 0 )
                    return timeHourRange( ( String ) arguments[ 0 ], ( int ) arguments[ 1 ], ( int ) arguments[ 2 ] );
                else
                    return false;
            }

            private  boolean timeHourRange ( String time, int startHour, int endHour ) {
                int timeValue = -1;
                timeValue = Integer.parseInt( time.substring( 11, 13 ) );
                return timeValue != -1 && startHour <= endHour && timeValue >= startHour && timeValue <= endHour;
            }

            @Override
            public String getName () {
                return "TimeHourRange";
            }
        };

        FelEngine engine = FelEngine.instance;
        engine.addFun( fun );
        Expression expression = engine.compile( s );
        Map< String, Object > testContext = new HashMap<>();
        testContext.put( "e1_deviceCat", "/Application" );
        testContext.put( "e1_catBehavior", "/Authentication/Add" );
        testContext.put( "e1_severity", Integer.valueOf( "5" ) );
        testContext.put( "e1_catTechnique", "/TrafficAnomaly/NetWorkLayer" );
        testContext.put( "e1_catObject", "/Host/Application/Service" );
        testContext.put( "e1_destAddress", "3333" );
        //testContext.put( "e1_startTime", "2016-03-02 12:57:52" );
        System.out.println( expression.eval( testContext ) );
    }

    @Test
    public void test09 () {
        long start = System.currentTimeMillis();
        for ( int i = 0 ; i < 1000*10000; i++ ) {
            Map< String, Object > a = new HashMap<>();
            a.put( String.valueOf( new Random() ), new Random().ints() );
        }
        System.out.println( "test fel(use utils): " + ( System.currentTimeMillis() - start ) );
    }

    @Test
    public void test10 () {
        String s = "e1_startTime != nil && $('fun.ExpressionUtil').timeHourRange(e1_startTime,0,13)";
        Map< String, Object > context = new HashMap<>();
        //context.put( "e1_startTime", "2016-03-02 12:57:52" );
        System.out.println( FelUtil.evaluation( s, context ) );
        System.out.println( AviatorUtil.evaluation( s.replace( "$('fun.ExpressionUtil').", "" ), context ) );

    }

    @Test
    public void test12 () {
        String s = " $('fun.ExpressionUtil').timeHourRange(e1_startTime,0,13)";
        Map< String, Object > context = new HashMap<>();
        //context.put( "e1_startTime", "2016-03-02 12:57:52" );
        System.out.println( AviatorUtil.evaluation( s.replace( "$('fun.ExpressionUtil').", "" ), context ) );

    }

    @Test
    public void testIke() {
        String s = "a > 4 && $timeHourRange(e1_startTime,0,13)";
        //String s = " $timeHourRange(e1_startTime,0,13)";
        Map< String, Object > context = new HashMap<>();
        context.put( "e1_startTime", "2016-03-02 12:57:52" );
        context.put( "a", 5 );
        System.out.println( IKExpression.evaluation( s, context ) );
    }

}
