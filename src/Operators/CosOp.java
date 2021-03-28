package Operators;

import java.util.List;

public class CosOp implements Operator {
    private static final CosOp instance = new CosOp();

    private CosOp() { }

    public static CosOp getInstance() {
        return instance;
    }
    @Override
    public Number calculate(Number[] args) {
        assert args.length == 1;

        return Math.cos(args[0].doubleValue());
    }
}
