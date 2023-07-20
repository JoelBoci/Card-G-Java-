import java.util.ArrayList;

public class Board {
    private ArrayList<Card> playerCards;
    private Player player;

    Board() {
        playerCards = new ArrayList<>();

        // Player will be assigned with setPlayer method
        player = null;
    }

    // Add cards to players hand
    public void addCard(Card c) {
        playerCards.add(c);
    }

    // Remove card from players hand (search for Card)
    public void removeCard(Card c) {
        playerCards.remove(c);
    }

    // Remove the card at the given index
    public void removeCardAtIndex(int index) {
        if(index >= 0 && index < playerCards.size()) {
            playerCards.remove(index);
        }
    }

    // Return the card at the given index
    public Card getCard(int index) {
        return playerCards.get(index);
    }

    // Setting the current player to the variable
    public void setPlayer(Player currentPlayer) {
        this.player = currentPlayer;
    }

    // Return the current player set
    public Player getPlayer() {
        return player;
    }

    // Print the players hand
    public ArrayList<Card> getPlayerCards() {
        return playerCards;
    }
}
