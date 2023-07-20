import java.util.ArrayList;

public abstract class Player {
    private String name;
    private int wins;
    private int draws;
    private int losses;
    private int power;
    private Board board;

    // Mainly used for the P-- card
    private Player opponent;

    Player() {
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
        this.power = 0;

        // Board will be created and assigned to variable
        board = null;
    }

    // Getter and setter for name
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Increasing a statistic
    public void increaseWins() {
        this.wins++;
    }

    public void increaseDraws() {
        this.draws++;
    }

    public void increaseLosses() {
        this.losses++;
    }

    public void increasePower(int value) {
        this.power += value;
    }

    // Used for the P-- card, decreases opponents power
    public void decreaseOpponentPower(Player opponent, int value) {
        opponent.power -= value;
    }

    public int getPower() {
        return power;
    }

    public void printStats() {
        System.out.println("\tName: " + name);
        System.out.println("\tPower: " + power);
        System.out.println("\tWins: " + wins);
        System.out.println("\tDraws: " + draws);
        System.out.println("\tLosses: " + losses);
    }

    // Create a new board
    public void generateBoard(Board board) {
        this.board = board;
        board.setPlayer(this);
    }

    // Return the board
    public Board getBoard() {
        return board;
    }

    // Set and get the opponent of the match
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Player getOpponent() {
        return opponent;
    }

    // Abstract, will have 2 different implementations
    public abstract void printBoard();

    // Abstract, will have 2 different implementations. User / virtual player will pick their cards from the deck
    public abstract void userPick(ArrayList<Card> deck);

    // Abstract, will have 2 different implementations. User / virtual player will play the cards in their hand
    public abstract void playCard();

    // Observer design pattern methods
}
