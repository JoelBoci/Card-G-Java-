import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class VirtualPlayer extends Player {

    VirtualPlayer() {
        super();
        // Name is randomly chosen
        setName();
    }

    // Set the name of the virtual player, does not use the one from the Player class
    public void setName() {
        // Create a list of names to randomly choose from
        ArrayList<String> names = new ArrayList<>(Arrays.asList("John", "Luna", "Aiden", "Leo", "Specter", "Rose"));

        // Generate a new number (index)
        Random random = new Random();
        int rand = random.nextInt(names.size());

        // Assign the name at the index given to the name variable
        this.setName(names.get(rand));
    }

    // Method override from the Player class
    @Override
    public void printBoard() {
        System.out.println("\n------------------------------");
        System.out.println("        PLAYER 2 BOARD        ");
        System.out.println("------------------------------");
        System.out.println("Name >>> " + super.getName() + "\n");

        // Print hand
        System.out.println("Your deck: ");

        int count = 1;
        for(Card card : this.getBoard().getPlayerCards()) {
            System.out.println("    " + count + ") " + "XXXXXX"); // XXXXXX to hide opponents card, we should not be able to see them
            count++;
        }

        // Print power
        System.out.println("\nPower >>> " + super.getPower());
        System.out.println("------------------------------");
    }

    // Method override from the Player class
    @Override
    public void userPick(ArrayList<Card> deck) {
        Random random = new Random();

        for(int i = 0; i < 5; i++) {
            // Assign a new number (index) to cardIndex
            int cardIndex = random.nextInt(deck.size());

            // Add the card at the specified index from the deck to the virtual players hand
            super.getBoard().addCard(deck.get(cardIndex));

            // Remove the card from the deck so that it cannot be chosen again
            deck.remove(cardIndex);
        }
    }

    // Method override from the Player class
    @Override
    public void playCard() {
        // Don't do anything if the user has no cards to play (skip to opponent)
        if(super.getBoard().getPlayerCards().size() == 0) {
            System.out.println("No more cards left to play...\n\n");

        } else {
            Random random = new Random();
            // Will be used when checking what type of card is played
            Player opponent = super.getOpponent();

            System.out.println("\n" + super.getName() + "'s turn to play a card");

            // Generate a new value and assign it to the variable
            int cardIndex = random.nextInt(super.getBoard().getPlayerCards().size()) + 1;

            // Assign the card at the index specified to variable
            Card card = super.getBoard().getCard(cardIndex - 1); // index - 1 as we are following 1-20, not 0-19. If user picks 14, then we do -1 because that is the 14th card

            // Check the type of card played and call the function
            if (card instanceof PowerPlusPlus) {
                card.function(this);

            } else if (card instanceof PowerMinusMinus) {
                if (opponent != null) {
                    card.function(this);
                }

            } else if (card instanceof Steal) {
                if (opponent != null) {
                    card.function(this);
                }
            }

            // Print the card played
            System.out.println("Card played: " + card.toString());

            // Remove the card from the players hand
            super.getBoard().removeCardAtIndex(cardIndex - 1);
        }
    }
}
