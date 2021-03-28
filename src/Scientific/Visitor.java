package Scientific;

public interface Visitor {
    public Number visit(BinaryOpNode node);
    public Number visit(UnaryOpNode node);
    public Number visit(NumberNode node);
}