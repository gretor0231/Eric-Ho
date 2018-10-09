import java.util.*;


public class PostfixCalculator {

    private DoubleStack stack = new DoubleStack();
    private Map <String,Operator> operatorMap = new HashMap<>();


    public PostfixCalculator() {
        //fill Map with implemented operators and Map string val to op. val
        operatorMap.put("+", new Addition());
        operatorMap.put("add", new Addition());
        operatorMap.put("-", new Subtraction());
        operatorMap.put("sub", new Subtraction());
        operatorMap.put("*", new Multiplication());
        operatorMap.put("mult", new Multiplication());
        operatorMap.put("/", new Division());
        operatorMap.put("div", new Division());
        operatorMap.put("=", new Print());
        operatorMap.put("print", new Print());

    }
    public void storeOperand(double temp){
        stack.push(temp);
    }

    public void evaluateOperator(String s){
//        Double[] args;
        List l = new ArrayList<Double>();

        //rebCheck to see if string is in Map
        if (operatorMap.containsKey(s)) {
            //get the operator value based on the key
            Operator opp = operatorMap.get(s);
            //create array the same length as the args needed for the op.
//            args = new Double[opp.numArgs()];
            //get the numbers from the stack
            for (int x = 0; x < opp.numArgs() && !stack.isEmpty(); x++) {
                //save the numbers in an array and remove from stack
                l.add(stack.pop());
//                args[x] = stack.pop();
            }

//            l = Arrays.asList(args);
            //pass the numbers to the operator and evaluate, then push to the stack
            stack.push(opp.eval(l));

        } else {
            System.out.println("Operator not recognized");
        }

    }


    private class Addition implements Operator {
        @Override
        public int numArgs() {
            return 2;
        }

        @Override
        public double eval(List<Double> args) {
            return args.get(1) + args.get(0);
        }
    }

    private class Subtraction implements Operator {
        @Override
        public int numArgs() {
            return 2;
        }

        @Override
        public double eval(List<Double> args) {
            return args.get(1) - args.get(0);
        }
    }

    private class Multiplication implements Operator {
        @Override
        public int numArgs() {
            return 2;
        }

        @Override
        public double eval(List<Double> args) {
            return args.get(1) * args.get(0);
        }
    }

    private class Division implements Operator {
        @Override
        public int numArgs() {
            return 2;
        }

        @Override
        public double eval(List<Double> args) {
            return args.get(1) / args.get(0);
        }
    }

    private class Print implements Operator {
        @Override
        public int numArgs() {
            return 1;
        }

        @Override
        public double eval(List<Double> args) {
            System.out.println(args.get(0));
            return args.get(0);
        }
    }
}
