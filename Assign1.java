import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.regex.Pattern;

// CS3100 Assignment 1
// Created By: Spencer Hall (A02248808)

public class Assign1 {

    public static void main(String[] args) {
        Assign1 runner = new Assign1();

        if (args.length < 1) {
            runner.printHelpScreen();
        } else if (args.length < 2) {
            System.out.println("Error: Invalid number of arguments.");
        } else {
            runner.executeOperations(args);
        }

    }

    /**
     * Identifies each of the operations requested and calls the correct method for each of them.
     * @param args The list of arguments and operands.
     */
    private void executeOperations(String[] args) {
        for (int i=0; i < args.length; i+=2) {
            String operation = args[i];
            String strOperand = args[i+1];
            Pattern isDigit = Pattern.compile("\\d+");

            if (isDigit.matcher(strOperand).matches()) {
                try {
                    Integer intOperand = Integer.parseInt(strOperand);
                    switch (operation) {
                        case "-fib":
                            executeFib(intOperand);
                            break;
    
                        case "-fac":
                            executeFac(intOperand);
                            break;

                        case "-e":
                            executeEFunc(intOperand);
                            break;

                        default:
                            printInvalidArgumentError(operation);
                    }
                } catch (IllegalArgumentException nonNumericArgument) {
                    System.out.println("Error: Largest possible value that can be evaluated is " + Integer.MAX_VALUE);
                }
            } else {
                System.out.println("Error: Argument " + strOperand + " is not a numeric value");
            }
        }
    }

    /**
     * Checks Range, calls the fibonacci method, and prints the result to the user
     * @param intOperand The value to determine the fibonacci of.
     */
    private void executeFib(Integer intOperand) {
        if (intOperand >= 0 && intOperand <= 40) {
            System.out.println("Fibonacci of " + intOperand + " is " + fib(intOperand));
        } else {
            System.out.println("Fibonacci valid range is [0, 40]");
        }

    }

    /**
     * Checks Range, Calls the factorial method, and prints the result to the user.
     * @param intOperand The value to determine the factorial of.
     */
    private void executeFac(Integer intOperand) {
        if (intOperand >= 0 && intOperand <= Integer.MAX_VALUE) {
            System.out.println("Factorial of " + intOperand + " is " + fac(BigInteger.valueOf(intOperand)).toString());
        } else {
            System.out.println("Factorial valid range is [0, 2147483647]");
        }
    }

    /**
     * Checks Range, Calls the e method, and prints the result to the user.
     * @param intOperand The number of iterations of Taylor Series to use to estimate e.
     */
    private void executeEFunc(Integer intOperand) {
        if (intOperand >= 1 && intOperand <= Integer.MAX_VALUE) {
            System.out.println("Value of e using " + intOperand + " iterations is " + efunc(intOperand).toString());
        } else {
            System.out.println("Valid e iterations range is [1, 2147483647]");
        }
    }

    /**
     * Calculates the fibonacci value of a given operand.
     * @param operand The operand to be evaluated.
     * @return
     */
    private Integer fib(Integer operand) {
        if (operand < 2) {
            return 1;
        } else {
            return fib(operand - 1) + fib(operand - 2);
        }
    }

    /**
     * Calculates the factorial value of a given operand,
     * computed using BigIntegers
     * @param operand The value to get the factorial of.
     * @return The factorial of operand
     */
    private BigInteger fac(BigInteger operand) {
        if (operand.compareTo(BigInteger.ONE) <= 0) {
            return BigInteger.ONE;
        } else {
            return operand.multiply(fac(operand.subtract(BigInteger.ONE)));
        }
    }

    /**
     * Estimates the value of e using Taylor Series with operand degrees of accuracy.
     * @param operand The number of iterations to use for the taylor series.
     * @return An estimated value of e
     */
    private BigDecimal efunc(Integer operand) {
        if (operand < 1) {
            return BigDecimal.ONE;
        } else {
            BigDecimal facOperand = new BigDecimal(fac(BigInteger.valueOf(operand))); // The factorial of operand in BigDecimal format.
            return BigDecimal.ONE.divide(facOperand, 16, RoundingMode.HALF_EVEN).add(efunc(operand - 1));
        }
    }

    /**
     * Prints a main help screen to the user.
     */
    private void printHelpScreen() {
        System.out.println("--- Assign 1 Help ---");
        System.out.println("\t-fib [n] : Compute the Fibonacci of [n]; valid range [0, 40]");
        System.out.println("\t-fac [n] : Compute the factorial of [n]; valid range, [0, 2147483647]");
        System.out.println("\t-e [n] : Compute the value of 'e' using [n] iterations; valid range [1, 2147483647]");
    }

    /**
     * Prints an error for an invalid argument.
     * @param argument The argument that is invalid.
     */
    private void printInvalidArgumentError(String argument) {
        System.out.println("Unknown program option: " + argument);
    }
}