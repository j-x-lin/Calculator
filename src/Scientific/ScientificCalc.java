package Scientific;

import Operators.IdentityOp;
import Operators.OperatorFactory;

import java.util.List;

public class ScientificCalc {
    Evaluator eval = Evaluator.getInstance();
    OperatorFactory opFact = OperatorFactory.getInstance();

    public Number calculate(String expression)
    {
        expression = Normalizer.normalize(expression);
        List<String> tokens = Tokenizer.parse(expression);
        Node expressionTree = BuildTree.createTree(tokens);
        UnaryOpNode root = new UnaryOpNode(expressionTree, opFact.getOperator(""));
        return eval.visit(root);
    }
}
