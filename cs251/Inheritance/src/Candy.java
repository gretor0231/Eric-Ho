public class Candy extends Dessert{

    private double price;
    private double weight;

    public Candy(String name, double weight, double price) {
        super(name);
        this.price = price;
        this.weight = weight;
    }

    public double getPricePerPound (){
        return this.price;
    }

    public double getWeightInPounds (){
        return this.weight;
    }
    @Override
    public double getPrice () {
        return getPricePerPound() * getWeightInPounds();
    }

}
