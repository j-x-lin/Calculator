package Operators;

import java.util.List;

/**
 * Adds two numbers together
 * Currently follows the Java arithmetic conventions for overflow and NaN/Infinity
 */
public class AddOp implements Operator {
    private static final AddOp instance = new AddOp();

    private AddOp() { }

    public static AddOp getInstance() {
        return instance;
    }

    @Override
    public Number calculate(Number[] args) {
        assert args.length == 2;
        double ans = args[0].doubleValue()+args[1].doubleValue();

        if (args[0] instanceof Integer && args[1] instanceof Integer) {
            return (int) ans;
        }

        return ans;
    }
}
