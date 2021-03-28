package test.OperatorTests;

import Operators.SubOp;
import Operators.OperatorFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SubOpTest {
    OperatorFactory opFact;
    SubOp sub = SubOp.getInstance();

    @Test
    public void testIntegerBasic()
    {
        assertEquals(3, sub.calculate(new Number[]{5, 2}));
        assertEquals(-3, sub.calculate(new Number[]{2, 5}));
        assertEquals(3, sub.calculate(new Number[]{3, 0}));
        assertEquals(-3, sub.calculate(new Number[]{0, 3}));
        assertEquals(13, sub.calculate(new Number[]{8, -5}));
        assertEquals(-13, sub.calculate(new Number[]{-5, 8}));
        assertEquals(0, sub.calculate(new Number[]{-12, -12}));
        assertEquals(0, sub.calculate(new Number[]{12, 12}));
        assertEquals(0, sub.calculate(new Number[]{0, 0}));
        assertEquals(1, sub.calculate(new Number[]{-1, -2}));
        assertEquals(-1, sub.calculate(new Number[]{-2, -1}));

        assertEquals(-30, sub.calculate(new Number[]{25, 55}));
        assertEquals(30, sub.calculate(new Number[]{55, 25}));
        assertEquals(80, sub.calculate(new Number[]{25, -55}));
        assertEquals(-80, sub.calculate(new Number[]{-55, 25}));

        assertEquals(-1000, sub.calculate(new Number[]{-999, 1}));
        assertEquals(-1000, sub.calculate(new Number[]{-999, 1}));
        assertEquals(-1000, sub.calculate(new Number[]{-10015, -9015}));
    }

    @Test
    public void testDoubleBasic()
    {
        assertEquals(3, sub.calculate(new Number[]{5.0, 2.0}));
        assertEquals(-3, sub.calculate(new Number[]{2.0, 5.0}));
        assertEquals(3, sub.calculate(new Number[]{3.0, .0}));
        assertEquals(-3, sub.calculate(new Number[]{.0, 3.0}));
        assertEquals(13, sub.calculate(new Number[]{8.0, -5.0}));
        assertEquals(-13, sub.calculate(new Number[]{-5.0, 8.0}));
        assertEquals(0, sub.calculate(new Number[]{-12.0, -12.0}));
        assertEquals(0, sub.calculate(new Number[]{12.0, 12.0}));
        assertEquals(0, sub.calculate(new Number[]{.0, .0}));
        assertEquals(1, sub.calculate(new Number[]{-1.0, -2.0}));
        assertEquals(-1, sub.calculate(new Number[]{-2.0, -1.0}));

        assertEquals(-30, sub.calculate(new Number[]{25.0, 55.0}));
        assertEquals(30, sub.calculate(new Number[]{55.0, 25.0}));
        assertEquals(80, sub.calculate(new Number[]{25.0, -55.0}));
        assertEquals(-80, sub.calculate(new Number[]{-55.0, 25.0}));

        assertEquals(-1000.0, sub.calculate(new Number[]{-999.0, 1.0}));
        assertEquals(-1000.0, sub.calculate(new Number[]{-999.0, 1.0}));
        assertEquals(-1000.0, sub.calculate(new Number[]{-10015.0, -9015.0}));

        //decimal point subtraction
        assertEquals(.5, sub.calculate(new Number[]{-.5, -1.0}));
        assertEquals(-.5, sub.calculate(new Number[]{.5, 1.0}));
        assertEquals(0, sub.calculate(new Number[]{-.5, -0.5}));
        assertEquals(0.88, sub.calculate(new Number[]{1.5, 0.62}));
        assertEquals(1.5625, sub.calculate(new Number[]{1.5, -0.0625}));
        assertEquals(99999.5, sub.calculate(new Number[]{100000, 0.5}));
    }

    @Test
    public void testMixedTypes() {
        assertEquals(-1.5, sub.calculate(new Number[]{-.5, -1}));
        assertEquals(.5, sub.calculate(new Number[]{-.5, 1.0}));
        assertEquals(0.5, sub.calculate(new Number[]{0, .5}));
        assertEquals(999.375, sub.calculate(new Number[]{1000, -.625}));
    }

    @Test
    public void testOverUnderFlow() {
        assertEquals(Double.MIN_VALUE, sub.calculate(new Number[]{Double.MIN_VALUE, 0}));
        assertEquals(0.0, sub.calculate(new Number[]{Double.MIN_VALUE, -Double.MIN_VALUE}));
        assertEquals(0.0, sub.calculate(new Number[]{Double.MIN_VALUE, -Double.MIN_VALUE}));
    }

    @Test
    public void testNaNInfinity() {
        assertEquals(Double.POSITIVE_INFINITY, sub.calculate(new Number[]{Double.MAX_VALUE, Double.MAX_VALUE}));
        assertEquals(Double.POSITIVE_INFINITY, sub.calculate(new Number[]{Double.POSITIVE_INFINITY, 1}));
        assertEquals(Double.NaN, sub.calculate(new Number[]{Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}));
    }
}
