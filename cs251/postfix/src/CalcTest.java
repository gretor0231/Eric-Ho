import java.util.Scanner;

/** Evaluates postfix expressions from standard input. */
public class CalcTest {

    public static void main(String[] args) {

        float a;
//        Test test = new Test();
        a = Test.todegree(3);

        System.out.println(a);

        // Create a scanner from standard input
        Scanner scanner = new Scanner(System.in);

        // Make a new calculator
        PostfixCalculator calc = new PostfixCalculator();

        // keep reading tokens until we run out.
        while(scanner.hasNext()) {
            if(scanner.hasNextDouble()) {
                // Next item is a double, so must be an operand
                calc.storeOperand(scanner.nextDouble());
//                System.out.println(scanner.nextDouble());
            } else {
                // otherwise, we have an operator
                calc.evaluateOperator(scanner.next());
//                System.out.println(scanner.next());
            }
        }
    }
}
