package Scientific;

import Operators.Operator;

public class BinaryOpNode implements Node {
    public Node left;
    public Node right;
    public Operator op;

    public BinaryOpNode(Node left, Node right, Operator op)
    {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    public Number accept(Visitor v)
    {
        return v.visit(this);
    }
}
