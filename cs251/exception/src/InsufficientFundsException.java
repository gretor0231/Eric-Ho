public class InsufficientFundsException extends Exception {

    private double shortfall;


    public InsufficientFundsException(double shortfall, String message) {
        super(message);
        this.shortfall = shortfall;
    }
    double getShortfallAmount(){
        return this.shortfall;

    }

}
