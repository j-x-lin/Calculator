package Operators;

import java.util.List;

public class SqrtOp implements Operator {
    private static final SqrtOp instance = new SqrtOp();

    private SqrtOp() { }

    public static SqrtOp getInstance() {
        return instance;
    }
    @Override
    public Number calculate(Number[] args) {
        assert args.length == 1;

        return Math.sqrt(args[0].doubleValue());
    }
}
