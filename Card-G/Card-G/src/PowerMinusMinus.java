public class PowerMinusMinus extends Card {

    PowerMinusMinus(String name, int value) {
        super(name, value);
    }

    // Method override from the Card class
    @Override
    public void function(Player player) {
        // Assign opponent of player passed in parameter
        Player opponentPlayer = player.getOpponent();

        // Decrease their power
        if (opponentPlayer != null) {
            player.decreaseOpponentPower(opponentPlayer, this.getValue());
        }
    }

}
