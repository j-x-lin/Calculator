package Scientific;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    /**
     * returns a list of the "parts" of the expression, without splitting up parantheses
     * e.g. "1+(2*3)/4" would become the list ["1", "+", "(2*3)", "/", "4"]
     * Assumes input is valid (e.g. 1*2+3, not *2+1-3), if not may crash in unexpected places
     * WARNING: SO FAR ONLY ABLE TO HANDLE 4 OPERATIONS WITH POSITIVE INTEGERS,
     *          DO NOT PUT COMPLEX OPERATORS/USER-STORED VARIABLES
     * @param text
     * @return
     */
    public static List<String> parse(String text)
    {
        if (text.length() == 0) {
            return new ArrayList<>();
        }

        List<String> parsed = new ArrayList<>();

        int startIndex = 0;
        int endIndex = 0;

        int currIndex = 0;

        while (currIndex < text.length())
        {
            String nextTerm = parseNext(text, currIndex);
            parsed.add(nextTerm);
            currIndex += nextTerm.length();
        }

        return parsed;
    }

    /**
     *
     * @param text the String to parse
     * @param index the start index of the current segment
     * @return a String containing the next "segment" of the given text, starting on index.
     */
    private static String parseNext(String text, int index)
    {
        char first = text.charAt(index);

        int endIndex = index+1;

        if (endIndex == text.length()) {
            return text.substring(index);
        }

        if (first == '\\') { //"complex" operator (e.g. \sqrt, \sin, etc.)
            while (endIndex != text.length() && Character.isAlphabetic(text.charAt(endIndex)))
            {
                endIndex++;
            }
        } else if (first == '(') { //parantheses, don't split it yet
            int extraOpening = 1;

            //just keep the expression within the parantheses as an atomic unit for now, split it later
            while (extraOpening != 0)
            {
                if (text.charAt(endIndex) == ')') {
                    extraOpening--;
                } else if (text.charAt(endIndex) == '(') {
                    extraOpening++;
                }

                endIndex++;
            }
        } else if (possibleNum(text, index)) { //number (may not be well-formatted)
            char curr = text.charAt(endIndex);
            while (endIndex != text.length() && (Character.isDigit(curr) || curr == '.'))
            {
                endIndex++;

                if (endIndex != text.length()) {
                    curr = text.charAt(endIndex);
                }
            }
        } else { //"simple" operator (aka 4 operations plus exponents, etc.)
            //do nothing, since it's assumed that it is a "simple" operator, which only takes up 1 character
        }

        return text.substring(index, endIndex);
    }

    /**
     * Returns whether the current segment could represent a number, assuming we have at most 2 '-'s in a row
     * Right now does not check for stored constants yet (such as \pi, \e, user-stored variables, etc.)
     * @param text the String
     * @param index the index to start at
     * @return whether the current segment could be part of a number (i.e. 0-9, '.', '-', etc.
     */
    private static boolean possibleNum(String text, int index)
    {
        char curr = text.charAt(index);

        if (curr == '.' || Character.isDigit(curr)) {
            return true;
        } else if (curr == '-') {
            if (index == 0) {
                return true;
            }

            char prev = text.charAt(index-1);

            //if the previous segment represents a number or an expression then this '-' is an operator
            if (prev == ')' || prev == '.' || Character.isDigit(prev)) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}