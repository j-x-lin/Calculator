package Scientific;

public class NumberNode implements Node {
    public Number value;

    public NumberNode(Number val)
    {
        this.value = val;
    }

    public Number accept(Visitor v)
    {
        return v.visit(this);
    }
}
