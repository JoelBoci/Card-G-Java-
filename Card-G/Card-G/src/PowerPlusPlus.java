public class PowerPlusPlus extends Card {

    PowerPlusPlus(String name, int value) {
        super(name, value);
    }

    // Method override from the Card class, increases players power by the value of the card
    @Override
    public void function(Player player) {
        player.increasePower(super.getValue());
    }

}
