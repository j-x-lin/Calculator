package Scientific;

public class Normalizer {
    /**
     * Reformat the String
     */
    public static String normalize(String text)
    {
        text = removeWhitespace(text);
        text = checkParans(text);
        text = removeImplicitMult(text);
        text = removeManyNegatives(text);

        return text;
    }

    /**
     * Removes all double negatives, such as "1---3" or "1--------3", etc.
     * We know that a double negative is a positive, so "1---3" = "1-3" and "--1" = "1"
     * Assumes that there is no whitespace in the text
     * @param text the text to remove triple negatives
     * @returns a String containing no double negatives.
     */
    private static String removeManyNegatives(String text)
    {
        while (text.contains("---"))
        {
            text = text.replaceAll("---", "-");
        }

        if (text.length() >= 2 && text.substring(0,2).equals("--")) {
            text = text.substring(2);
        }

        return text;
    }

    /**
     * Removes all whitespace (" " and "\t") from the given input string
     * @param text the string
     * @return a string without any whitespace.
     */
    private static String removeWhitespace(String text)
    {
        String noSpaces = "";

        for (int i = 0; i < text.length(); i++)
        {
            char curr = text.charAt(i);
            if (!(curr == ' ' || curr == '\t')) {
                noSpaces += curr;
            }
        }

        return noSpaces;
    }

    /**
     * Returns an expression with all parantheses mismatches corrected as much as possible
     * For instance, "(1+2" will become "(1+2)", "1+2)" will become "(1+2)", ")1+2*3(" will become "1+2*3",
     * and "1)+2" becomes "(1)+2"
     * @param text the string to parse parantheses
     * @return a String containing the corrected expression (which may differ from what was actually intended)
     */
    private static String checkParans(String text)
    {
        int length = text.length();

        int frontIndex = 0;
        int backIndex = length-1;

        //if there's an unpaired closing parantheses at the front ")" just ignore it
        //invariant: text[0...frontIndex-1] = ')'
        while (!(frontIndex == length) && text.charAt(frontIndex) == ')')
        {
            frontIndex++;
        }

        assert text.charAt(frontIndex) != ')';

        //if there's an unpaired opening parans at the back "(" just ignore it
        //invariant: text[backIndex+1... length] = '('
        while (!(backIndex == -1) && text.charAt(backIndex) == '(')
        {
            backIndex--;
        }

        assert text.charAt(backIndex) != '(';

        //check if number of closing parantheses = number of opening parantheses
        int extraOpenParans = 0;

        for (int index = frontIndex; index <= backIndex; index++) {
            if (text.charAt(index) == '(') {
                extraOpenParans++;
            } else if (text.charAt(index) == ')') {
                extraOpenParans--;
            }
        }

        text = text.substring(frontIndex, backIndex+1);

        if (extraOpenParans > 0) {
            String closeParans = "";
            for (int i = 0; i < extraOpenParans; i++) {
                closeParans += ")";
            }

            text += closeParans;
        } else {
            String openParans = "";
            for (int i = 0; i < -extraOpenParans; i++) {
                openParans += "(";
            }

            text = openParans+text;
        }

        return text;
    }

    /**
     * Replaces all "implicit multiplication" instances with "explicit multiplication instances
     * "implicit multiplication" is where there isn't a "*" symbol, e.g. "2(1+3)", (10/2)5 or "(1+3)(2+4)"
     * "explicit multiplication" is where there is a "*" symbol, e.g. "2*(1+3)", "(10/2)*5", "(1+3)*(2+4)", or "5*4"
     * @param text
     * @return a String that has all instances of implicit multiplication replaced with explicit multiplication
     */
    private static String removeImplicitMult(String text)
    {
        //assumption: implicit multiplication occurs only when you have "...)(...", "number(", or ")number"
        for (int i = 0; i < text.length()-1; i++)
        {
            char curr = text.charAt(i);
            char next = text.charAt(i+1);

            if (curr == ')' && next == '(' ||
                    curr == ')' && Character.isDigit(next) ||
                    Character.isDigit(curr) && next == '(') {
                text = text.substring(0, i+1) + "*" + text.substring(i+1);
            }
        }

        System.out.println(text);

        return text;
    }
}
