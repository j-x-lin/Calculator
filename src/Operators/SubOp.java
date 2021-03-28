package Operators;

import java.util.List;

public class SubOp implements Operator {
    private static final SubOp instance = new SubOp();

    private SubOp() { }

    public static SubOp getInstance() { return instance; }

    @Override
    public Number calculate(Number[] args) {
        assert args.length == 2;
        double ans = args[0].doubleValue()-args[1].doubleValue();

        if (args[0] instanceof Integer && args[1] instanceof Integer) {
            return (int) ans;
        }

        return ans;
    }
}
