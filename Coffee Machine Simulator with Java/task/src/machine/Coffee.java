package machine;

public record Coffee(int water, int milk, int beans) {

    private final static Coffee UNIT = new Coffee(200,50,15);

    public static Coffee unitCup() {
        return UNIT;
    }


}
