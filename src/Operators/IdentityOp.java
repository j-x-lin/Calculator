package Operators;

import java.util.List;

public class IdentityOp implements Operator {
    private static final IdentityOp instance = new IdentityOp();

    private IdentityOp() { }

    public static IdentityOp getInstance()
    {
        return instance;
    }

    @Override
    public Number calculate(Number[] args) {
        assert args.length == 1;
        return args[0];
    }
}
