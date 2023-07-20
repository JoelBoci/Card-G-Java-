import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

    // Will be used for Singleton design pattern
    private static Deck instance = null;
    private ArrayList<Card> deck;

    // Private constructor - use of Singleton design patter (we only want one deck)
    private Deck() {
        deck = new ArrayList<>();
    }

    // Create a new instance of a deck - only done once
    public static Deck getInstance() {
        // If there is no instance, then create a new Deck() and assign it to instance
        if(instance == null) {
            instance = new Deck();
        }

        // If there is an instance, we return it (cannot generate multiple)
        return instance;
    }

    // Generating a deck of cards
    public void generateDeck() {
        // Create 8 P++ cards
        for(int i = 1; i <= 8; i++) {
            deck.add(new PowerPlusPlus("P++ ", i));
        }

        // Create 8 P-- cards
        for(int i = 1; i <= 8; i++) {
            deck.add(new PowerMinusMinus("P-- ", i));
        }

        // Create 4 Steal cards
        for(int i = 1; i <= 4; i++) {
            deck.add(new Steal("S ", 0, false));
        }
    }

    // Print the deck in normal format
    public void printDeck() {
        int count = 1;
        for(Card card : deck) {
            System.out.println(count + ") " + card.toString());
            count++;
        }
    }

    // Prints the deck but does not display the type or value (upside down card, not allowed to see)
    public void printHiddenDeck() {
        int count = 1;
        for(Card card : deck) {
            System.out.println(count + ") " + "XXXXXX");
            count++;
        }
    }

    // Shuffling the deck of cards
    public void shuffle() {
        long seed = System.nanoTime();
        Collections.shuffle(deck, new Random(seed));
    }

    // Returns the deck
    public ArrayList<Card> getDeck() {
        return deck;
    }
}
