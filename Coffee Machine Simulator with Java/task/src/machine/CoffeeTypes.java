package machine;

public enum CoffeeTypes {
    ESPRESSO(250,0,16,4),
    LATTE(350,75,20,7),
    CAPPUCCINO(200,100,12,6);

    private final int water;
    private final int milk;
    private final int beans;
    private final double cost;

    public int water() {
        return water;
    }

    public int milk() {
        return milk;
    }

    public int beans() {
        return beans;
    }

    public double cost() {
        return cost;
    }

    CoffeeTypes(int water, int milk, int beans, double cost) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cost = cost;
    }

}
