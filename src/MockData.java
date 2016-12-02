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
                "&& e1_catTechnique == \"/TrafficAnomaly/NetWorkLayer\" " +
                "&& e1_catObject == \"/Host/Application/Service\" " +
                "&& e1_destAddress != null " +
                "&& $TimeHourRange(e1_startTime,0,13)";
    }

    public static String getFelExp () {
        return "( e1_deviceCat == '/Application' " +
                "|| e1_deviceCat == '/IDS/Network' " +
                "|| e1_deviceCat == '/Firewall' ) " +
                "&& e1_catBehavior == '/Authentication/Add' " +
                "&& e1_catTechnique == '/TrafficAnomaly/NetWorkLayer' " +
                "&& e1_catObject == '/Host/Application/Service' " +
                "&& e1_destAddress != null " +
                "&& $('fun.ExpressionUtil').timeHourRange(e1_startTime,0,13)";
        //return "e1_deviceCat == '/Application'";
    }

    public static Map< String, String > getVariableContextMap () {
        Map< String, String > testContext = new HashMap<>();
        testContext.put( "e1_deviceCat", "/Application" );
        testContext.put( "e1_catBehavior", "/Authentication/Add" );
        testContext.put( "e1_catTechnique", "/TrafficAnomaly/NetWorkLayer" );
        testContext.put( "e1_catObject", "/Host/Application/Service" );
        testContext.put( "e1_destAddress", "1.1.1.1" );
        testContext.put( "e1_startTime", "2016-03-02 12:57:52" );
        return testContext;
    }

    public static String getFelExp2 () {
        return "( e1_deviceCat123 == '/Application123'" +
                "|| e1_deviceCat123 == '/IDS/Network123'" +
                "|| e1_deviceCat123 == '/Firewall' 123)" +
                "&& e1_catBehavior123 == '/Authentication/Add123'" +
                "&& e1_catTechnique123 == '/TrafficAnomaly/NetWorkLayer123'" +
                "&& e1_catObject123 == '/Host/Application/Service123'" +
                "&& e1_destAddress123 == 1.1.1.1 " +
                "&& $('fun.ExpressionUtil').timeHourRange(e1_startTime123,0,13)";
        //return "e1_deviceCat == '/Application'";
    }

    public static Map< String, String > getContextMap2 () {
        Map< String, String > testContext = new HashMap<>();
        testContext.put( "e1_deviceCat123", "/Application123" );
        testContext.put( "e1_catBehavior123", "/Authentication/Add123" );
        testContext.put( "e1_catTechnique123", "/TrafficAnomaly/NetWorkLayer123" );
        testContext.put( "e1_catObject123", "/Host/Application/Service123" );
        testContext.put( "e1_destAddress123", "1.1.1.1" );
        testContext.put( "e1_startTime123", "2016-03-02 12:57:52" );
        return testContext;
    }
}
