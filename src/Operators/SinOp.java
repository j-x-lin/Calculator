package Operators;

import java.util.List;

public class SinOp implements Operator {
    private static final SinOp instance = new SinOp();

    private SinOp() { }

    public static SinOp getInstance() {
        return instance;
    }
    @Override
    public Number calculate(Number[] args) {
        assert args.length == 1;

        return Math.sin(args[0].doubleValue());
    }
}
