package Scientific;

import Operators.Operator;

public class UnaryOpNode implements Node {
    public Node child;
    public Operator op;

    public UnaryOpNode(Node child, Operator operator)
    {
        this.child = child;
        this.op = operator;
    }

    public Number accept(Visitor v)
    {
        return v.visit(this);
    }
}
