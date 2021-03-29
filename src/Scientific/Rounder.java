package Scientific;

public class Rounder {
    private static final double EPSILON = 1E-10;

    /**
     * Rounds to the (digits)th decimal place
     * @param num
     * @param digits
     * @return the number rounded to the (digits)th decimal place, with rounding up in case of ties (0.5 -> 1)
     */
    public static Number round(Number num, int digits)
    {
        if (num instanceof Integer) {
            return num;
        }

        double temp = num.doubleValue();

        for (int i = 0; i < digits; i++)
        {
            temp *= 10;
        }

        if (temp % 1 >= 0.5) {
            temp += 1;
        }

        for (int i = 0; i < digits; i++)
        {
            temp /= 10;
        }

        double roundInt = Math.rint(temp);
        double diff = temp-roundInt;
        if (Math.abs(temp-roundInt) < EPSILON) {
            return (int) roundInt;
        }

        return temp;
    }
}
