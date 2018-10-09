public class BankAccount {

    private int accountID;
    private double accountBalance = 0;

    public BankAccount(int accountID){
        this.accountID = accountID;
    }
    int getAccountID(){
        return this.accountID;
    }
    double getAccountBalance(){
        return  this.accountBalance;
    }
    void withdraw (double withdrasAmount) throws InsufficientFundsException {
        if((accountBalance - withdrasAmount) < 0){
            double shortfall;
            String message;
            shortfall =  withdrasAmount - accountBalance;
            message = "You need more money!!!!!!!";
            throw new InsufficientFundsException(shortfall, message);
        }else {
            this.accountBalance -= withdrasAmount;
        }
    }
    void deposit(double depositAmount){
        this.accountBalance += depositAmount;
    }



}
