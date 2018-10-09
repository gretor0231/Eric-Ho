
public class DoubleStack implements Stack<Double> {

    private double data[] = new double [100];
    private int top = 1;


    //check it is empty
    @Override
    public boolean isEmpty() {
        if(top == 1) {
            return true;
        }
        return false;
    }
    //save data
    @Override
    public void push(Double val) {
        data[top+1] = val;
        top++;
    }

    //take data and remove top
    @Override
    public Double pop() {
        double temp;
        temp= data[top];
        top--;
        return temp;
    }

    // looking for the top
    @Override
    public Double peek() {
        return data[top];
    }

}
