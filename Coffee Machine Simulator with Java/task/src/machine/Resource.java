package machine;


public class Resource {
    private int water;
    private int milk;
    private int beans;
    private int cup;
    private double balance;

    public Resource(int water, int milk, int beans, int cup, double balance) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cup = cup;
        this.balance = balance;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water += water;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk += milk;
    }

    public int getBeans() {
        return beans;
    }

    public void setBeans(int beans) {
        this.beans += beans;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance += balance;
    }

    public int getCup() {
        return cup;
    }

    public void setCup(int cup) {
        this.cup += cup;
    }


    public String checkResource(CoffeeTypes type) {

        var status = "";

        if (getWater() < type.water()) {
            status = "water";
        } else if (getMilk() < type.milk()) {
            status = "milk";
        } else if (getBeans() < type.beans()) {
            status = "beans";
        } else {
            status = "enough";
        }
        return status;
    }


    public void ops(String method, int value) {
        switch (method) {
            case "water" -> setWater(value);
            case "milk" -> setMilk(value);
            case "beans" -> setBeans(value);
            case "cup" ->  setCup(value);
        }

    }

    public int getOps(String method) {
        return switch(method) {
            case "water" -> getWater();
            case "milk" -> getMilk();
            case "beans" -> getBeans();
            case "cup" ->  getCup();
            case "money" ->  (int)getBalance();
            default -> throw new IllegalStateException("Unexpected value: " + method);
        };
    }

    public void updateResource(CoffeeTypes type) {
        setWater(-type.water());
        setMilk(-type.milk());
        setBeans(-type.beans());
        setCup(-1);
        setBalance(type.cost());
    }
}
