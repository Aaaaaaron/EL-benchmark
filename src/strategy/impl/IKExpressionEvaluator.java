package strategy.impl;

import org.wltea.expression.ExpressionEvaluator;
import org.wltea.expression.PreparedExpression;
import org.wltea.expression.datameta.BaseDataMeta;
import org.wltea.expression.datameta.Variable;
import strategy.EvaluatorStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;

/**
 * Created by AH on 2016/12/5.
 */
public class IKExpressionEvaluator implements EvaluatorStrategy {
    private static List<Variable> variables = new ArrayList<>();
    private final static ConcurrentHashMap<String, FutureTask<PreparedExpression>> cachedExpressions = new ConcurrentHashMap<>();

    @Override
    public boolean evaluation(String expression, Map<String, Object> context) {
        PreparedExpression preparedExpression = getCompileExpression(expression, context);
        setContextFieldsValue(context);
        Object result = preparedExpression.execute();
        return result.toString().equals("true");
    }

    public static PreparedExpression getCompileExpression(String expression, Map<String, Object> context) {
        FutureTask<PreparedExpression> task = cachedExpressions.get(expression);
        if (task != null)
            return getCachedCompiledExpression(expression, task);

        task = new FutureTask<PreparedExpression>(new Callable<PreparedExpression>() {
            @Override
            public PreparedExpression call() throws Exception {
                return innerCompile(expression, context);
            }
        });

        FutureTask<PreparedExpression> existedTask = cachedExpressions.putIfAbsent(expression, task);
        if (existedTask == null) {
            existedTask = task;
            existedTask.run();
        }
        return getCachedCompiledExpression(expression, existedTask);

    }

    private static PreparedExpression getCachedCompiledExpression(final String expression,
            final FutureTask<PreparedExpression> task) {
        try {
            return task.get();
        } catch (Exception e) {
            cachedExpressions.remove(expression);
            throw new RuntimeException("Compile expression failure:" + expression, e);
        }
    }

    private static PreparedExpression innerCompile(final String expression, Map<String, Object> context) {
        setContextFieldsType(context);
        return ExpressionEvaluator.preparedCompile(expression, variables);
    }

    private static void setContextFieldsType(Map<String, Object> context) {
        for (String field : context.keySet()) {
            Variable var = new Variable(field);
            if (context.get(field) instanceof Number) {
                var.setDataType(BaseDataMeta.DataType.DATATYPE_INT);
            } else {
                var.setDataType(BaseDataMeta.DataType.DATATYPE_STRING);
            }
            variables.add(var);
        }
    }

    private static void setContextFieldsValue(Map<String, Object> context) {
        for (Variable variable : variables) {
            variable.setVariableValue(context.get(variable.getVariableName()));
        }
    }

}
