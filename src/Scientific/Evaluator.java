package Scientific;

public class Evaluator implements Visitor {
    private static final Evaluator eval = new Evaluator();

    private Evaluator() { }

    public static Evaluator getInstance()
    {
        return eval;
    }

    @Override
    public Number visit(BinaryOpNode node) {
        Number leftResult = node.left.accept(this);
        Number rightResult = node.right.accept(this);
        return node.op.calculate(new Number[]{leftResult, rightResult});
    }

    @Override
    public Number visit(UnaryOpNode node) {
        Number childResult = node.child.accept(this);
        return node.op.calculate(new Number[]{childResult});
    }

    @Override
    public Number visit(NumberNode node) {
        return node.value;
    }
}
