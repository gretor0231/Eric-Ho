public class IceCream extends Dessert{

    private double cost;


    public IceCream(String name, double cost) {
        super(name);
        this.cost = cost;
    }


    @Override
    public double getPrice() {
        return this.cost;
    }
}
