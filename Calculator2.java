import java.util.*;

/**
 * Calculator2 exists to simulate calculator in pure text interface
 */

public class Calculator2 {

    private static Scanner sc = new Scanner(System.in);

    /**
     * main exists to start our calculator and get first inputs from user
     */
    public static void main(String[] args) {
        System.out.println();
        calculate(); 
    }

    /**
     * calculate exists to calculate results of math operations
     */
    public static void calculate() {
        double num1;
        double num2;
        OperatorType operatorType;
        num1 = loadNumber();
        while(true){
            operatorType= loadOperator();
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

    private static OperatorType loadOperator(){
        String input;
        char operatorLetter;
        while (true) {
            System.out.print("Enter an operator: ");
            input = sc.nextLine();
              if(input.length() ==0){continue;}

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

    private static double makeOperation(OperatorType operatorType, double num1, double num2) throws Exception{

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
                if(num2 == 0){
                    throw new Exception("You cant divide by zero, math cant handle that");
                }
                num1 /= num2;
                break;
        
            default:
                break;
        }


        return num1;
    }
    private enum OperatorType {
        PLUS, MINUS, MULTIPLY, DIVIDE, DELETE, PRINT_EXIT, EXIT;

    }
}
