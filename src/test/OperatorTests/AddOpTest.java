package test.OperatorTests;

import Operators.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class AddOpTest {
    OperatorFactory opFact;
    AddOp add = AddOp.getInstance();

    @Test
    public void testAddOpIntegerBasic()
    {
        assertEquals(3, add.calculate(new Number[]{1, 2}));
        assertEquals(3, add.calculate(new Number[]{2, 1}));
        assertEquals(3, add.calculate(new Number[]{3, 0}));
        assertEquals(3, add.calculate(new Number[]{0, 3}));
        assertEquals(3, add.calculate(new Number[]{8, -5}));
        assertEquals(3, add.calculate(new Number[]{-5, 8}));
        assertEquals(-3, add.calculate(new Number[]{-8, 5}));
        assertEquals(-3, add.calculate(new Number[]{5, -8}));
        assertEquals(0, add.calculate(new Number[]{-12, 12}));
        assertEquals(0, add.calculate(new Number[]{12, -12}));
        assertEquals(0, add.calculate(new Number[]{0, 0}));
        assertEquals(-3, add.calculate(new Number[]{-1, -2}));
        assertEquals(-3, add.calculate(new Number[]{-2, -1}));
        assertEquals(20, add.calculate(new Number[]{9, 11}));
        assertEquals(-20, add.calculate(new Number[]{-9, -11}));
        assertEquals(100, add.calculate(new Number[]{36, 64}));
        assertEquals(-100, add.calculate(new Number[]{-36, -64}));
        assertEquals(-105, add.calculate(new Number[]{-99, -6}));
        assertEquals(105, add.calculate(new Number[]{99, 6}));
        assertEquals(105, add.calculate(new Number[]{-105, 210}));
    }

    @Test
    public void testAddOpDoubleBasic()
    {
        //same as integer test, just with doubles
        assertEquals(3.0, add.calculate(new Number[]{1.0, 2.0}));
        assertEquals(3.0, add.calculate(new Number[]{2.0, 1.0}));
        assertEquals(3.0, add.calculate(new Number[]{3.0, 0.0}));
        assertEquals(3.0, add.calculate(new Number[]{0.0, 3.0}));
        assertEquals(3.0, add.calculate(new Number[]{8.0, -5.0}));
        assertEquals(3.0, add.calculate(new Number[]{-5.0, 8.0}));
        assertEquals(-3.0, add.calculate(new Number[]{-8.0, 5.0}));
        assertEquals(-3.0, add.calculate(new Number[]{5.0, -8.0}));
        assertEquals(0.0, add.calculate(new Number[]{-12.0, 12.0}));
        assertEquals(0.0, add.calculate(new Number[]{12.0, -12.0}));
        assertEquals(0.0, add.calculate(new Number[]{0.0, 0.0}));
        assertEquals(-3.0, add.calculate(new Number[]{-1.0, -2.0}));
        assertEquals(-3.0, add.calculate(new Number[]{-2.0, -1.0}));
        assertEquals(20.0, add.calculate(new Number[]{9.0, 11.0}));
        assertEquals(-20.0, add.calculate(new Number[]{-9.0, -11.0}));
        assertEquals(100.0, add.calculate(new Number[]{36.0, 64.0}));
        assertEquals(-100.0, add.calculate(new Number[]{-36.0, -64.0}));
        assertEquals(-105.0, add.calculate(new Number[]{-99.0, -6.0}));
        assertEquals(105.0, add.calculate(new Number[]{99.0, 6.0}));
        assertEquals(105.0, add.calculate(new Number[]{-105.0, 210.0}));

        //decimal point addition
        assertEquals(-1.5, add.calculate(new Number[]{-.5, -1.0}));
        assertEquals(1.5, add.calculate(new Number[]{.5, 1.0}));
        assertEquals(-1.0, add.calculate(new Number[]{-.5, -0.5}));
        assertEquals(2.12, add.calculate(new Number[]{1.5, 0.62}));
        assertEquals(1.4375, add.calculate(new Number[]{1.5, -0.0625}));
        assertEquals(99999.5, add.calculate(new Number[]{100000, -0.5}));
    }

    @Test
    public void testAddOpMixedTypes() {
        assertEquals(-1.5, add.calculate(new Number[]{-.5, -1}));
        assertEquals(.5, add.calculate(new Number[]{-.5, 1.0}));
        assertEquals(0.5, add.calculate(new Number[]{0, .5}));
        assertEquals(999.375, add.calculate(new Number[]{1000, -.625}));
    }

    @Test
    public void testAddOpOverUnderFlow() {
        assertEquals(Double.MIN_VALUE, add.calculate(new Number[]{Double.MIN_VALUE, 0}));
        assertEquals(0.0, add.calculate(new Number[]{Double.MIN_VALUE, -Double.MIN_VALUE}));
        assertEquals(0.0, add.calculate(new Number[]{Double.MIN_VALUE, -Double.MIN_VALUE}));
    }

    @Test
    public void testAddOpNaNInfinity() {
        assertEquals(Double.POSITIVE_INFINITY, add.calculate(new Number[]{Double.MAX_VALUE, Double.MAX_VALUE}));
        assertEquals(Double.POSITIVE_INFINITY, add.calculate(new Number[]{Double.POSITIVE_INFINITY, 1}));
        assertEquals(Double.NaN, add.calculate(new Number[]{Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}));
    }
}
