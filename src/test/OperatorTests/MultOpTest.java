package test.OperatorTests;

import Operators.MultOp;
import org.junit.Test;
import static org.junit.Assert.*;

public class MultOpTest {
    MultOp mult = MultOp.getInstance();

    @Test
    public void testIntegerBasic()
    {
        assertEquals(4, mult.calculate(new Number[]{2, 2}));
        assertEquals(6, mult.calculate(new Number[]{3, 2}));
        assertEquals(6, mult.calculate(new Number[]{2, 3}));
        assertEquals(0, mult.calculate(new Number[]{0, 5}));
        assertEquals(0, mult.calculate(new Number[]{5, 0}));
        assertEquals(0, mult.calculate(new Number[]{0, 0}));

        assertEquals(-6, mult.calculate(new Number[]{-3, 2}));
        assertEquals(-6, mult.calculate(new Number[]{2, -3}));
        assertEquals(0, mult.calculate(new Number[]{0, -5}));
        assertEquals(0, mult.calculate(new Number[]{-5, 0}));

        assertEquals(4, mult.calculate(new Number[]{-2, -2}));
        assertEquals(6, mult.calculate(new Number[]{-3, -2}));
        assertEquals(6, mult.calculate(new Number[]{-2, -3}));
    }

    @Test
    public void testDoubleBasic()
    {
        assertEquals(4.0, mult.calculate(new Number[]{2.0, 2.0}));
        assertEquals(6.0, mult.calculate(new Number[]{3.0, 2.0}));
        assertEquals(6.0, mult.calculate(new Number[]{2.0, 3.0}));
        assertEquals(.0, mult.calculate(new Number[]{0.0, 5.0}));
        assertEquals(.0, mult.calculate(new Number[]{5., 0.0}));
        assertEquals(.0, mult.calculate(new Number[]{.0, .0}));

        assertEquals(-6.0, mult.calculate(new Number[]{-3.0, 2.0}));
        assertEquals(-6.0, mult.calculate(new Number[]{2.0, -3.0}));
        assertEquals(0.0, mult.calculate(new Number[]{0.0, -5.0}));
        assertEquals(0.0, mult.calculate(new Number[]{-5.0, .0}));

        assertEquals(4.0, mult.calculate(new Number[]{-2.0, -2.0}));
        assertEquals(6.0, mult.calculate(new Number[]{-3.0, -2.0}));
        assertEquals(6.0, mult.calculate(new Number[]{-2.0, -3.0}));


        assertEquals(2.25, mult.calculate(new Number[]{1.5, 1.5}));
        assertEquals(7.7, mult.calculate(new Number[]{3.5, 2.2}));
        assertEquals(7.7, mult.calculate(new Number[]{2.2, 3.5}));
        assertEquals(0.0, mult.calculate(new Number[]{0.0, 105.637}));
        assertEquals(0.0, mult.calculate(new Number[]{105.637, 0.0}));

        assertEquals(-94.0, mult.calculate(new Number[]{-37.6, 2.5}));
        assertEquals(-94.0, mult.calculate(new Number[]{2.5, -37.6}));
        assertEquals(0.0, mult.calculate(new Number[]{0, -12345.678}));
        assertEquals(0.0, mult.calculate(new Number[]{-12345.678, 0}));

        assertEquals(8.0, mult.calculate(new Number[]{-2.5, -3.2}));
        assertEquals(8.0, mult.calculate(new Number[]{-3.2, -2.5}));
        assertEquals(11.275, mult.calculate(new Number[]{-2.75, -4.1}));
    }
}
