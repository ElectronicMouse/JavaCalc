import java.util.*;

/**
 * Calculator2 exists to simulate calculator in pure text interface
 */

public class Calculator2 {

    private static Scanner sc = new Scanner(System.in);

    /**
     * main exists to explain usage to user and start our calculator
     */
    public static void main(String[] args) {
        System.out.println("This is a simple calculator app, it has:");
        System.out.println("basic math operators (+,-,*,/)");
        System.out.println("control keys usable anytime:");
        System.out.println("s - print and exit");
        System.out.println("x - exit without printing");
        System.out.println("c - delete saved data and start anew\n");
        calculate();
    }

    /**
     * calculate exists to call calculations, controls and print results of math
     * operations
     * 
     * @param num1         number1 given by user
     * @param num2         number2 given by user
     * @param operatorType math operator or control operator given by user
     */
    public static void calculate() {
        double num1;
        double num2;
        OperatorType operatorType;
        num1 = loadNumber();
        while (true) {
            operatorType = loadOperator();
            if (operatorType == OperatorType.EXIT) {
                break;
            }
            if (operatorType == OperatorType.PRINT_EXIT) {
                System.out.println("Result is: " + num1);
                break;
            }
            if (operatorType == OperatorType.DELETE) {
                num1 = loadNumber();
                continue;
            }
            num2 = loadNumber();
            try {
                num1 = makeOperation(operatorType, num1, num2);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    /**
     * loadNumber exists to load user input, specifically numbers
     * 
     * @param input  taking user input as string
     * @param number if input is number, becomes that number
     */
    private static double loadNumber() {
        String input;
        double number;

        while (true) {
            System.out.print("Please input a number: ");
            input = sc.nextLine();
            try {
                number = Double.parseDouble(input);
                break;
            } catch (NumberFormatException e) {
                continue;
            }

        }
        return number;
    }

    /**
     * loadOperator exists to load user input, specifically math operators and
     * controls
     * 
     * @param input          taking user input as string
     * @param operatorLetter taking only first char of input
     */
    private static OperatorType loadOperator() {
        String input;
        char operatorLetter;
        while (true) {
            System.out.print("Enter an operator: ");
            input = sc.nextLine();
            if (input.length() == 0) {
                continue;
            }

            operatorLetter = input.charAt(0);
            switch (operatorLetter) {
                case '+':
                    return OperatorType.PLUS;
                case '-':
                    return OperatorType.MINUS;
                case '*':
                    return OperatorType.MULTIPLY;
                case '/':
                    return OperatorType.DIVIDE;
                case 's':
                    return OperatorType.PRINT_EXIT;
                case 'x':
                    return OperatorType.EXIT;
                case 'c':
                    return OperatorType.DELETE;

                default:
                    continue;
            }
        }

    }

    /**
     * makeOperation exists to calculate based on user input
     */
    private static double makeOperation(OperatorType operatorType, double num1, double num2) throws Exception {

        switch (operatorType) {
            case PLUS:
                num1 += num2;
                break;
            case MINUS:
                num1 -= num2;
                break;
            case MULTIPLY:
                num1 *= num2;
                break;
            case DIVIDE:
                if (num2 == 0) {
                    throw new Exception("You cant divide by zero, math cant handle that");
                }
                num1 /= num2;
                break;

            default:
                break;
        }

        return num1;
    }

    /**
     * OperatorType exists to define specific control words and operators
     */

    private enum OperatorType {
        PLUS, MINUS, MULTIPLY, DIVIDE, DELETE, PRINT_EXIT, EXIT;

    }
}
