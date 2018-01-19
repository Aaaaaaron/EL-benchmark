import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import util.AviatorUtil;
import util.FelUtil;
import util.IKExpression;

/**
 * Created by AH on 2016/12/2.
 */
public class TestUseUtil {
    @Test
    public void testFel() {
        Assertions.assertEquals(true, FelUtil.evaluation(MockData.getFelExp(), MockData.getVariableContextMap()));
        Assertions.assertEquals(false, FelUtil.evaluation(MockData.getFelExp(), MockData.getWrongVariableContextMap()));
    }

    @Test
    public void testIKExpression() {
        Assertions.assertEquals(true, IKExpression.evaluation(MockData.getIKExp(), MockData.getVariableContextMap()));
        Assertions.assertEquals(false,
                IKExpression.evaluation(MockData.getIKExp(), MockData.getWrongVariableContextMap()));
    }

    @Test
    public void testAviator() {
        Assertions.assertEquals(true,
                AviatorUtil.evaluation(MockData.getAviatorExp(), MockData.getVariableContextMap()));
        Assertions.assertEquals(false,
                AviatorUtil.evaluation(MockData.getAviatorExp(), MockData.getWrongVariableContextMap()));
    }

    //测试编译缓存
}
