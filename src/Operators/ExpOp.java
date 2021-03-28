package Operators;

import java.util.List;

public class ExpOp implements Operator {
    private static final ExpOp instance = new ExpOp();

    private ExpOp() { }

    public static ExpOp getInstance() {
        return instance;
    }
    @Override
    public Number calculate(Number[] args) {
        assert args.length == 2;
        double ans = Math.pow(args[0].doubleValue(), args[1].doubleValue());

        //even if both arguments are integers if the answer would overflow we need to return a double.
        if (args[0] instanceof Integer && args[1] instanceof Integer && ans < Integer.MAX_VALUE) {
            return (int) ans;
        }

        return ans;
    }
}
