import Scientific.ScientificCalc;

import java.util.*;
import java.io.*;

public class Main
{
    private static char mode = 's';

    private static HashMap<String, String> storeVals;
    private static Set<String> legalOps;
    //private static Graphing g = new Graphing();
    private static ScientificCalc s = new ScientificCalc();
    //private static Programmer p = new Programmer();
    private static List<String> log;

    public static void main(String[] args) throws Exception
    {
        //init();

        Scanner system = new Scanner(System.in);

        System.out.println("Welcome to a text-based graphing calculator emulator!");
        System.out.println("Type \":h\" at any time to learn more about various features.");
        System.out.println("Type \":s\" to switch to scientific mode (default), \":p\" to switch to programmer mode, " +
                "or \":g\" to switch to graphing mode");
        System.out.println("Type \":l\" for a condensed log of your past calculations, and type \":c\" to clear your history");
        System.out.println("Type \":h\" to learn about saving values in variables, and type \":r\" to reset all saved variables");
        System.out.print("Type in any expression (or enter \":q\" to exit): ");
        String text = system.nextLine().toLowerCase();

        while (!text.equals(":q"))
        {
            String output = "";

            System.out.println(processExpression(text));
//            try {
//                output = processExpression(text);
//                System.out.println(output);
//            } catch (Exception e)
//            {
//                output = "Invalid input";
//                System.out.println("Invalid input, please try again.");
//                System.out.println("Remember, you can type \":h\" at any time to learn more about various features.");
//
//                if (!output.equals("")) {
//                    log.add(text + " = " + output);
//
//                    //store it in the "previous answer" constant if not invalid
//                    if (!output.equals("Invalid input")) {
//                        storeVals.put("\\Ans", output);
//                    }
//                }
//            }

            System.out.print("Type in any expression (or enter \":q\" to exit): ");
            text = system.nextLine().toLowerCase();
        }

        System.out.println("Powering off...");
    }

    private static void init() throws FileNotFoundException
    {
        //g = new Graphing();
        s = new ScientificCalc();
        //p = new Programmer();

        legalOps = new HashSet<String>();
        storeVals = new HashMap<>();
        log = new ArrayList<>();

        File exps = new File("src/expressions");
        File cnst = new File("src/constants");

        Scanner expressions = new Scanner(exps);

        while (expressions.hasNext())
        {
            legalOps.add(expressions.nextLine());
        }

        Scanner constants = new Scanner(cnst);

        while (constants.hasNext())
        {
            String[] constant = constants.nextLine().split(" ");
            storeVals.put(constant[0], constant[1]);
        }
    }

    /** Processes the user's input
     * Assumes the input is either a command (e.g. ":s", ":c") or an expression to be evaluated
     * Returns the result of the calculation, "" if the input is a command, or "Invalid input" if the input is invalid */
    private static String processExpression(String text) throws Exception
    {
        String[] split = text.split(" ");
        String output = "";

        if (text.equals(":h")) {
            help();
        } else if (text.equals(":s")) {
            mode = 's';
        } else if (text.equals(":p")) {
            mode = 'p';
        } else if (text.equals(":g")) {
            mode = 'g';
        } else if (text.equals(":r")) {
            reset();
        } else if (text.equals(":l")) {
            printLog();
        } else if (text.equals(":c")) {
            clearLog();
        } else {
            if (text.contains(":")) {
                //throw new SyntaxException("Invalid command");
                throw new IllegalArgumentException();
            }

            output = calculate(text);
//            try {
//                output = calculate(text);
//            } catch (Exception e)
//            {
//                output = "Invalid input";
//            }
        }

        return output;
    }

    private static String calculate(String text)
    {
        return "" + s.calculate(text);
    }

    private static void clearLog()
    {
        log = new ArrayList<>();
    }

    private static void printLog()
    {
        System.out.println("History:");
        for (String s: log)
        {
            System.out.println("   " + s);
        }
    }

    private static void reset() throws FileNotFoundException {
        init();
    }

/*    private static List<String> parse(String text) throws SyntaxException
    {
        List<String> parsed = new ArrayList<String>();

        int index = 0;

        while (index < text.length())
        {
            char curr = text.charAt(index);
            String term = "";

            if (curr == '(' || curr == ')') {
                term = "" + curr;
            } else if (curr == '\\') {
                term = "\\";

            }

            parsed.add(term);
        }

        return null;
    }*/

    private static void help()
    {

    }
}
