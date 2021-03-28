package test;

import Operators.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class OperatoryFactoryTest {
    OperatorFactory opFact;

    @Test
    public void testBasicOps() {
        assertTrue(opFact.getOperator("+") instanceof AddOp);
        assertTrue(opFact.getOperator("-") instanceof SubOp);
        assertTrue(opFact.getOperator("*") instanceof MultOp);
        assertTrue(opFact.getOperator("/") instanceof DivOp);
        assertTrue(opFact.getOperator("^") instanceof ExpOp);
    }

    @Test
    public void testUnaryOps() {
        assertTrue(opFact.getOperator("") instanceof IdentityOp);
        assertTrue(opFact.getOperator("\\sin") instanceof SinOp);
        assertTrue(opFact.getOperator("\\cos") instanceof CosOp);
        assertTrue(opFact.getOperator("\\sqrt") instanceof SqrtOp);
    }
}
