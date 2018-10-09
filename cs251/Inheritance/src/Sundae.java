
public class Sundae extends IceCream{

//    Dessert top;

    public Sundae(IceCream iceCream, Dessert topping) {
        super(iceCream.getName()+ " topped with " + topping.getName()
                , iceCream.getPrice()+topping.getPrice() );

    }

    // Copy constructor
//    public Sundae(Sundae sundaeCopyTarget) {
//        super(sundaeCopyTarget.name, sundaeCopyTarget.getPrice());
//        this.top = Dessert(sundaeCopyTarget.top);
//    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

}
