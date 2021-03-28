package Operators;

import java.util.HashMap;
import java.util.Map;

public class OperatorFactory {
    private static final OperatorFactory instance = new OperatorFactory();
    private Map<String, Operator> operators = new HashMap<>();

    private OperatorFactory() {
        operators.put("", IdentityOp.getInstance());
        operators.put("+", AddOp.getInstance());
        operators.put("-", SubOp.getInstance());
        operators.put("*", MultOp.getInstance());
        operators.put("/", DivOp.getInstance());
        operators.put("^", ExpOp.getInstance());
        operators.put("\\sin", SinOp.getInstance());
        operators.put("\\cos", CosOp.getInstance());
        operators.put("\\sqrt", SqrtOp.getInstance());
    }

    public static OperatorFactory getInstance() {
        return instance;
    }

    public Operator getOperator(String expression)
    {
        return operators.get(expression);
    }
}
