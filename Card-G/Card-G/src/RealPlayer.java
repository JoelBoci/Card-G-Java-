import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RealPlayer extends Player {

    RealPlayer() {
        super();
    }

    // Method override from the Player class
    @Override
    public void printBoard() {
        System.out.println("\n------------------------------");
        System.out.println("        PLAYER 1 BOARD        ");
        System.out.println("------------------------------");
        System.out.println("Name >>> " + super.getName() + "\n");

        // Print hand
        System.out.println("Your deck: ");

        int count = 1;
        for(Card card : this.getBoard().getPlayerCards()) {
            System.out.println("    " + count + ") " + card.toString());
            count++;
        }

        // Print power
        System.out.println("\nPower >>> " + super.getPower());
        System.out.println("------------------------------");
    }

    // Method override from the Player class
    @Override
    public void userPick(ArrayList<Card> deck) {
        Scanner scanner = new Scanner(System.in);

        // Loop 5 times (allowed 5 cards in hand)
        for (int i = 0; i < 5; i++) {
            System.out.print("Select a card to add to your hand >>> ");

            int cardIndex = -1;

            do {
                try {
                    // Assign user input to 'cardIndex'
                    cardIndex = scanner.nextInt();
                    if (cardIndex < 1 || cardIndex >= deck.size()) {
                        throw new IndexOutOfBoundsException("Invalid card index"); // Throw error message if out of bounds
                    }

                    // Add card to the players hand
                    super.getBoard().addCard(deck.get(cardIndex - 1));

                    // Remove the card from the deck, so it cant be chosen again
                    deck.remove(cardIndex - 1); // index - 1 as we are following 1-20, not 0-19. If user picks 14, then we do -1 because that is the 14th card

                    // Throw error message if out of bounds
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: " + e.getMessage());
                    scanner.nextLine();

                    // Throw error message if user enters anything other than a number
                } catch (InputMismatchException e) {
                    System.out.println("Error: Invalid input");
                    scanner.nextLine();
                }
            } while (cardIndex < 1 || cardIndex > deck.size());
        }
    }

    // Method override from the Player class
    @Override
    public void playCard() {
        // Don't do anything if the user has no cards to play (skip to opponent)
        if(super.getBoard().getPlayerCards().size() == 0) {
            System.out.println("No more cards to play...\n\n");

        } else {
            Scanner scanner = new Scanner(System.in);
            // Will be used when checking what type of card is played
            Player opponent = super.getOpponent();

            int index = -1;

            do {
                try {
                    System.out.print("\nYour turn. Choose a card to play >>> ");
                    index = scanner.nextInt();

                    if (index < 1 || index > super.getBoard().getPlayerCards().size()) {
                        throw new IndexOutOfBoundsException("Invalid card index"); // Throw error message if out of bounds
                    }

                    // Throw error message if out of bounds
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: " + e.getMessage());
                    scanner.nextLine();

                    // Throw error message if user enters anything other than a number
                } catch (InputMismatchException e) {
                    System.out.println("Error: Invalid input");
                    scanner.nextLine();
                }

            } while (index < 1 || index > super.getBoard().getPlayerCards().size());

            // Assign the card at the index specified to variable
            Card card = super.getBoard().getCard(index - 1); // index - 1 again

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
            super.getBoard().removeCardAtIndex(index - 1);
        }
    }
}


