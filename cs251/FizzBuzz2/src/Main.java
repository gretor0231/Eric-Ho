public class Main {

    public static void main(String[] args) {
        /**
         * Program takes two numeric arguments and tells the user their sum.
         * @param args The command line arguments.
         */
                 if(args.length != 3) {
                // wrong number of arguments, so complain about it.
                System.err.println("Expected 3 arguments, but got " + args.length);
            } else {
                // Arguments are Strings, so use static method in Integer
                // class to convert to numbers.

                int firstVal = Integer.parseInt(args[0]);
                int secondVal = Integer.parseInt(args[1]);
                int thirdVal = Integer.parseInt(args[2]);

                for(int i = 1; i <= firstVal; i++) {
                    if ((i % secondVal == 0) && (i % thirdVal == 0)) {

                        System.out.println("FizzBuzz");

                    } else if (i % secondVal == 0) {
                        System.out.println("Fizz");
                    } else if (i % thirdVal == 0) {
                        System.out.println("Buzz");
                    } else {
                        System.out.println(i);
                    }
                }

            }
        }
    }

