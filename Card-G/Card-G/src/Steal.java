import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Steal extends Card {

    Steal(String name, int value, boolean type) {
        super(name, value);
    }

    @Override
    public void function(Player player) {
        // Get the opponent of the player passed in
        Player opponent = player.getOpponent();

        // If player is a RealPlayer (the user)
        if (player instanceof RealPlayer) {
            Scanner scanner = new Scanner(System.in);

            // If the opponent has no cards, then we move on
            if (opponent.getBoard().getPlayerCards().isEmpty()) {
                System.out.println("Opponent has no more cards to steal.");
                return;
            }

            // Print out the opponents deck
            System.out.println("Steal Card - Choose a card to steal from opponent:");
            for (int i = 0; i < opponent.getBoard().getPlayerCards().size(); i++) {
                System.out.println("\t" + (i + 1) + ") " + "XXXXXX"); // Hidden, not allowed to see the cards
            }

            System.out.print("Choose a card to steal: ");
            int cardIndex = -1;

            do {
                try {
                    cardIndex = scanner.nextInt();
                    if (cardIndex < 1 || cardIndex >= opponent.getBoard().getPlayerCards().size()) {
                        throw new IndexOutOfBoundsException("Invalid card index"); // Throw error message if out of bounds
                    }

                    // If the user enters a value that is out of bounds
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: " + e.getMessage());
                    scanner.nextLine();

                    // If the user enters anything other than a number
                } catch (InputMismatchException e) {
                    System.out.println("Error: Invalid input");
                    scanner.nextLine();
                }
            } while (cardIndex < 1 || cardIndex > opponent.getBoard().getPlayerCards().size());

            // Assign the card chosen to a variable
            Card stolenCard = opponent.getBoard().getPlayerCards().get(cardIndex - 1); // index - 1 as we are following 1-20, not 0-19. If user picks 14, then we do -1 because that is the 14th card

            // Add the card to the users hand
            player.getBoard().addCard(stolenCard);

            // Remove the card from the opponents hand
            opponent.getBoard().removeCard(stolenCard);

            // Print the stolen card
            System.out.println("\nYou have stolen " + stolenCard.toString());

            // If player is a virtual player (the computer)
        } else if (player instanceof VirtualPlayer) {

            // If the opponent has no cards, then we move on
            if (opponent.getBoard().getPlayerCards().isEmpty()) {
                System.out.println("Opponent has no more cards to steal.");
                return;
            }

            Random random = new Random();
            // Assign new number (index) to variable
            int cardIndex = random.nextInt(opponent.getBoard().getPlayerCards().size());

            // Assign card at index to variable
            Card stolenCard = opponent.getBoard().getPlayerCards().get(cardIndex);

            // Add the card to the virtual players hand
            player.getBoard().addCard(stolenCard);

            // Remove it from the opponents hand (the user)
            opponent.getBoard().removeCard(stolenCard);

            // Print the stolen card
            System.out.println("\nVirtual Player has stolen " + stolenCard.toString());
        }
    }
}