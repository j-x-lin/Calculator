package Operators;

import java.util.List;

public class DivOp implements Operator {
    private static final DivOp instance = new DivOp();

    private DivOp() { }

    public static DivOp getInstance() {
        return instance;
    }

    @Override
    public Number calculate(Number[] args) {
        assert args.length == 2;
        return args[0].doubleValue()/args[1].doubleValue();
    }
}
