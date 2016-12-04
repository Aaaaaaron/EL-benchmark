import com.greenpineyu.fel.Expression;

import java.util.Set;

/**
 * Created by Melody on 2016/12/3.
 */
public interface ExpressionEvaluate {
    public static Expression compile ( final String expression, final Set< String > contextFields );

}
