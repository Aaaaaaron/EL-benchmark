import java.util.HashMap;
import java.util.Map;

/**
 * Created by AH on 2016/12/2.
 */
public class MockData {
    public static String getIKExp () {
        return "( e1_deviceCat == \"/Application\" " +
                "|| e1_deviceCat == \"/IDS/Network\" " +
                "|| e1_deviceCat == \"/Firewall\" ) " +
                "&& e1_catBehavior == \"/Authentication/Add\" " +
                "&& e1_severity >= 4 " +
                "&& e1_catTechnique == \"/TrafficAnomaly/NetWorkLayer\" " +
                "&& e1_catObject == \"/Host/Application/Service\" " +
                "&& e1_destAddress != null " +
                "&& $TimeHourRange(e1_startTime,0,13)";
    }

    public static String getAviatorExp () {
        return "( e1_deviceCat == '/Application' " +
                "|| e1_deviceCat == '/IDS/Network' " +
                "|| e1_deviceCat == '/Firewall' ) " +
                "&& e1_catBehavior == '/Authentication/Add' " +
                "&& e1_severity >= 4 " +
                "&& e1_catTechnique == '/TrafficAnomaly/NetWorkLayer' " +
                "&& e1_catObject == '/Host/Application/Service' " +
                "&& e1_destAddress != nil " +
                "&& timeHourRange(e1_startTime,0,13)";
    }

    public static String getFelExp () {
        return "( e1_deviceCat == '/Application' " +
                "|| e1_deviceCat == '/IDS/Network' " +
                "|| e1_deviceCat == '/Firewall' ) " +
                "&& e1_catBehavior == '/Authentication/Add' " +
                "&& e1_severity >= 4 " +
                "&& e1_catTechnique == '/TrafficAnomaly/NetWorkLayer' " +
                "&& e1_catObject == '/Host/Application/Service' " +
                "&& e1_destAddress != null " +
                "&& $('fun.ExpressionUtil').timeHourRange(e1_startTime,0,13)";
        //return "e1_deviceCat == '/Application'";
    }

    public static String getSimpleExp () {
        return "e1_catBehavior == '/Authentication/Add' ";
    }

    public static Map< String, Object > getSimpleContextMap () {
        Map< String, Object > testContext = new HashMap<>();
        testContext.put( "e1_catBehavior", "/Authentication/Add" );
        return testContext;
    }

    public static Map< String, Object > getVariableContextMap () {
        Map< String, Object > testContext = new HashMap<>();
        testContext.put( "e1_deviceCat", "/Application" );
        testContext.put( "e1_catBehavior", "/Authentication/Add" );
        testContext.put( "e1_severity", 5 );
        testContext.put( "e1_catTechnique", "/TrafficAnomaly/NetWorkLayer" );
        testContext.put( "e1_catObject", "/Host/Application/Service" );
        testContext.put( "e1_destAddress", "1.1.1.1" );
        testContext.put( "e1_startTime", "2016-03-02 12:57:52" );
        return testContext;
    }

    public static Map< String, Object > getWrongVariableContextMap () {
        Map< String, Object > testContext = new HashMap<>();
        testContext.put( "e1_deviceCat", "/Application" );
        testContext.put( "e1_catBehavior", "/Authentication/Add" );
        testContext.put( "e1_severity", 1 );
        //testContext.put( "e1_catTechnique", "/TrafficAnomaly/NetWorkLayer" );
        //testContext.put( "e1_catObject", "/Host/Application/Service" );
        //testContext.put( "e1_destAddress", "1.1.1.1" );
        testContext.put( "e1_startTime1123", "2016-03-02 12:57:52" );
        return testContext;
    }
}
