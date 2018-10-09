import java.io.Serializable;

public class Equipment  implements Serializable{
    private final double attack;
    private final double defence;
    private final double price;
    private final String Name;

    Equipment(String Name, double attack, double defence, double price) {
        this.Name = Name;
        this.attack = attack;
        this.defence = defence;
        this.price = price;
    }


    public String toString(){
        return "\nEquipment Information\n" +
                "Name: " + getName() + "\n" +
                "Attack: " + getAttack() +  "\n" +
                "Defence: " + getDefence() +  "\n" +
                "Price: " + getPrice() +  "\n";
    }

    public String getName() {
        return Name;
    }

    public double getAttack() {
        return attack;
    }

    public double getDefence() {
        return defence;
    }

    public double getPrice() {
        return price;
    }


}


