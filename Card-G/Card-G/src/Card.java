public abstract class Card {

    private String name;
    private int value;

    Card(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getType() {
        return name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    // Abstract method - 3 classes extend and each have different function
    public abstract void function(Player player);

    @Override
    public String toString() {
        return name + " " + value;
    }
}
