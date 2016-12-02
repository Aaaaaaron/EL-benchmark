package util;

import org.wltea.expression.ExpressionEvaluator;
import org.wltea.expression.PreparedExpression;
import org.wltea.expression.datameta.BaseDataMeta;
import org.wltea.expression.datameta.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by AH on 2016/12/1.
 */
public class IKExpression {
    private static List< Variable > variables = new ArrayList<>();

    public static PreparedExpression compile ( String expression, Set< String > contextFields ) {
        setContextFieldsType( contextFields );
        return ExpressionEvaluator.preparedCompile( expression, variables );
    }

    private static void setContextFieldsType ( Set< String > contextFields  ) {
        for ( String field : contextFields ) {
            Variable var = new Variable( field );
            var.setDataType( BaseDataMeta.DataType.DATATYPE_STRING );
            variables.add( var );
        }
    }

    public static boolean evaluation ( PreparedExpression preparedExpression, Map< String, String > context ) {
        setContextFieldsValue( context );
        Object result = preparedExpression.execute();
        return result.toString().equals( "true" );
    }

    private static void setContextFieldsValue ( Map< String, String > context ) {
        for ( Variable variable : variables ) {
            variable.setVariableValue( context.get( variable.getVariableName() ) );
        }
    }

}
