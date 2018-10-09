/** Base class for Dessert types. */
public abstract class Dessert {

    /** Name of the dessert item. */
    protected final String name;

    /**
     * Constructs a new dessert item.
     * @param name Name of the dessert.
     */
    public Dessert(String name) {
        this.name = name;
    }

    /**
     * Get name of the dessert.
     * @return dessert name
     */
    public final String getName() {
        return name;
    }

    /**
     * Get the price of the dessert.
     * @return Dessert price
     */
    public abstract double getPrice();
}
    
