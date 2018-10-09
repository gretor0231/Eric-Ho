import java.io.Serializable;

public class Fitherinfo  implements Serializable{
    private final double attack;
    private final double defence;
    private final String citizenship;
    private final double balance;
    private final String firstName;

    Fitherinfo(String firstName, double attack, double defence, String citizenship,double balance) {
        this.firstName = firstName;
        this.attack = attack;
        this.defence = defence;
        this.citizenship = citizenship;
        this.balance = balance;
    }
    

    public String toString(){
        return "\nFighter Information\n" +
                "First Name: " + getFirstName() + "\n" + 
                "Attack: " + getAttack() +  "\n" +
                "Defence: " + getDefence() +  "\n" +
                "Citizenship: " + getcitizenship() +  "\n" +
                "Balance: " + getBalance() +  "\n";
    }
    
    public String getFirstName() {
        return firstName;
    }

    public double getAttack() {
        return attack;
    }

    public double getDefence() {
        return defence;
    }

    public double getBalance() {
        return balance;
    }

    public String getcitizenship() {
        return citizenship;
    }

}
