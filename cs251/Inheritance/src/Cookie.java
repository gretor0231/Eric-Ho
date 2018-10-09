public class Cookie extends Dessert{

    private double count;
    private double price;

    public Cookie(String name, int count, double price) {
        super(name);
        this.price = price;
        this.count = count;
    }

    public int getItemCount() {
        return (int) this.count;
    }

    public double getPricePerDozen() {
        return this.price;
    }

    @Override
    public double getPrice() {
        return (getItemCount() * getPricePerDozen())/12;
    }

}
