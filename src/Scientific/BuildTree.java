package Scientific;

import Operators.Operator;
import Operators.OperatorFactory;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class BuildTree {
    private static OperatorFactory opFact = OperatorFactory.getInstance();

    public static Node createTree(List<String> tokens)
    {
        int MDindex = -1;
        int expIndex = -1;

        //iterate through the tokens list
        for (int i = tokens.size()-1; i >= 0; i--)
        {
            String token = tokens.get(i);

            //if we find addition or subtraction we need to split the tree immediately
            if (token.equals("-") || token.equals("+")) {
                return createBinaryNode(tokens, i);
            }

            //keep track of if there's multiplication or division
            if ((token.equals("*") || token.equals("/")) && MDindex == -1) {
                MDindex = i;
            }

            //keep track of if there's exponentiation
            if (token.equals("^") && expIndex == -1) {
                expIndex = i;
            }
        }

        //there must not have been an addition or subtraction, so next priority is multiplication/division
        if (MDindex != -1) {
            return createBinaryNode(tokens, MDindex);
        }

        //there must not have been an addition/subtraction/multiplication/division, so next priority is exponentiation
        if (expIndex != -1) {
            return createBinaryNode(tokens, expIndex);
        }

        //at this point assume that we have no operations between different "units"
        //tokens should look something like either ["5"], ["\sin", "5"], ["\sin", "(2+10*25-(3+4))"], etc.
        String token = tokens.get(0);

        //test for escaped expressions
        if (token.charAt(0) == '\\') {
            //create unary node with the given operator
            tokens.remove(0);
            Node child = createTree(tokens);
            return new UnaryOpNode(child, opFact.getOperator(token));
        }

        //test if it's a number
        try {
            int result = Integer.parseInt(token);
            return new NumberNode(result);
        } catch (NumberFormatException notInt) {
            try {
                double result = Double.parseDouble(token);
                return new NumberNode(result);
            } catch (NumberFormatException notNumber) {
            }
        }

        //test for parantheses
        if (token.charAt(0) == '(') {
            String subExpression = token.substring(1, token.length()-1);

            List<String> subTokens = Tokenizer.parse(subExpression);
            return createTree(subTokens);
        }

        return null;
    }

    /**
     * Splits an expression tree represented by the given list of strings into two children [0...pivot-1] and [pivot+1...]
     * @param tokens
     * @param pivot the index to "pivot" around.
     * @return a Node where the left tree is the sublist of tokens from [0 ... pivot-1] and the right tree is the sublist
     * of tokens from [pivot+1 ... size] (e.g. createBinaryNode([0,1,2,3,4], 3) would result in {[0,1,2], [4]}
     * @throws IndexOutOfBoundsException if pivot < 0 or pivot >= tokens.size
     */
    private static Node createBinaryNode(List<String> tokens, int pivot)
    {
        Node leftTree = createTree(sublist(tokens, 0, pivot));
        Node rightTree = createTree(sublist(tokens, pivot+1, tokens.size()));

        return new BinaryOpNode(leftTree, rightTree, opFact.getOperator(tokens.get(pivot)));
    }

    /**
     * return the "substring" of tokens from startIndex to endIndex
     * @param tokens
     * @param startIndex
     * @param endIndex
     * @throws IllegalArgumentException if startIndex < endIndex or either index is invalid
     * @return a list containing the contents of tokens from startIndex (inclusive) to endIndex (inclusive). If tokens
     * is empty returns an empty list
     */
    private static List<String> sublist(List<String> tokens, int startIndex, int endIndex)
    {

        if (startIndex > endIndex || startIndex < 0 || endIndex > tokens.size()) {
            throw new IllegalArgumentException();
        }

        List<String> subList = new ArrayList<>(endIndex-startIndex);

        for (int i = startIndex; i < endIndex; i++)
        {
            subList.add(tokens.get(i));
        }

        return subList;
    }
}
