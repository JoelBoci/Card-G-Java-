import java.util.Scanner;

public class Match {
    Match() {

    }

    public static void clearConsole() {
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }
    }

    public boolean isValidName(String name) {
        if (name.length() < 2) {
            System.out.println("Name must be longer!\n");
            return false;
        }

        if (name.length() > 10) {
            System.out.println("Name must be shorter!\n");
            return false;
        }

        char[] letters = name.toCharArray();

        for (char c : letters) {
            if (!Character.isLetter(c)) {
                System.out.println("Name cannot contain digits or special characters!\n");
                return false;
            }
        }

        return true;
    }

    public void startMatch(Player player1, Player player2) {
        System.out.println("   _____              _          _____ ");
        System.out.println("  / ____|            | |        / ____|");
        System.out.println(" | |     __ _ _ __ __| | ______ | |  __ ");
        System.out.println(" | |    / _` | '__/ _` | ______ | | |_ |");
        System.out.println(" | |___| (_| | | | (_| |        | |__| |");
        System.out.println("  \\_____\\__,_|_|  \\__,_|         \\_____|");

        System.out.println("\t  Made by Joel Boci - u2155356");

        String name = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\nPlease enter your name >>> ");
            name = scanner.nextLine();

            if (isValidName(name)) {
                break;
            } else {
                System.out.println("Invalid name, please enter your name again!\n");
            }
        }

        // Set the name and print both players names
        player1.setName(name);
        System.out.println("\nPlayer 1: " + player1.getName());
        System.out.println("Your opponent will be: " + player2.getName() + "\n");

        player1.setOpponent(player2);
        player2.setOpponent(player1);

        int choice = 0;
        do {
            System.out.print("Press 1 to start the game | 2 to quit >>> ");
            choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("\nLet's play! We will first show you the rules of the game...\n");
                rules();
                System.out.println("\nNow that you know the rules, lets generate your board and a deck of cards...");

                // Creating the boards
                Board board1 = new Board();
                Board board2 = new Board();
                player1.generateBoard(board1);
                player2.generateBoard(board2);

                Deck deck = Deck.getInstance();
                deck.generateDeck();

                deck.shuffle();
                deck.printHiddenDeck();

                System.out.println();

                // Ask user to pick their cards
                player1.userPick(deck.getDeck());

                // Virtual players pick
                player2.userPick(deck.getDeck());

                char input = ' ';
                System.out.print("\nPress any button to start the match >>> ");
                input = scanner.next().charAt(0);

                clearConsole();
                player1.printBoard();
                player2.printBoard();

                while(!player1.getBoard().getPlayerCards().isEmpty() || !player2.getBoard().getPlayerCards().isEmpty()) {
                    player1.playCard();
                    player1.printBoard();
                    player2.printBoard();

                    System.out.print("\nPress any button to continue >>> ");
                    input = scanner.next().charAt(0);

                    player2.playCard();
                    player1.printBoard();
                    player2.printBoard();

                    System.out.print("\nPress any button to start the match >>> ");
                    input = scanner.next().charAt(0);

                    clearConsole();
                    player1.printBoard();
                    player2.printBoard();
                }

                System.out.println("\n\n");
                winner(player1, player2);
            }

        } while (choice < 1 || choice > 2);
    }

    public void rules() {
        System.out.println("|------------------------------------------------------------------------------|");
        System.out.println("|---------------------------- THE RULES OF THE GAME ---------------------------|");
        System.out.println("|  The player will have to enter their name and they will play against an AI   |");
        System.out.println("|   Once this is done, you will be able to choose 5 cards from a deck of 20    |");
        System.out.println("|------------------------------------------------------------------------------|");
        System.out.println("|------------------------ The cards and their abilities -----------------------|");
        System.out.println("|------------------------------------------------------------------------------|");
        System.out.println("|----------------------------------- Power++ ----------------------------------|");
        System.out.println("|        The Power++ card will add the value of the card to your power         |");
        System.out.println("|                      E.g., P++ 3 = 3 points for you                          |");
        System.out.println("|------------------------------------------------------------------------------|");
        System.out.println("|----------------------------------- Power-- ----------------------------------|");
        System.out.println("|  The Power-- card will deduce the opponents power by the value of the card   |");
        System.out.println("|                  However, the power value cannot be below 0                  |");
        System.out.println("|  E.g., If opponents points are 3 and you choose P-- 5, the power will be 0   |");
        System.out.println("|------------------------------------------------------------------------------|");
        System.out.println("|----------------------------------- Steal ------------------------------------|");
        System.out.println("|     The steal card allows you to steal one card from the opponents hand      |");
        System.out.println("|       If the opponent has no cards, the card is transformed to a P++ 1       |");
        System.out.println("|------------------------------------------------------------------------------|");
        System.out.println("|           The computer will go first. It's up to you to beat them            |");
        System.out.println("|                           Play your cards wisely...                          |");
        System.out.println("|------------------------------------------------------------------------------|");
    }

    public void winner(Player p1, Player p2) {
        if (p1.getPower() > p2.getPower()) {
            System.out.println("\n" + p1.getName() + " is the winner with " + p1.getPower() + " power!");
            System.out.println(p2.getName() + " had " + p2.getPower() + " power!");

            p1.increaseWins();
            p2.increaseLosses();

        } else if (p2.getPower() > p1.getPower()) {
            System.out.println("\n" + p2.getName() + " is the winner with " + p2.getPower() + " power!");
            System.out.println(p1.getName() + " had " + p1.getPower() + " power!");

            p2.increaseWins();
            p1.increaseLosses();

        } else {
            System.out.println("\nIt's a tie!");
            System.out.println(p1.getName() + " and " + p2.getName() + " both had " + p1.getPower());

            p1.increaseDraws();
            p2.increaseDraws();
        }

        System.out.println("\nPlayer 1: ");
        p1.printStats();

        System.out.println("\nPlayer 2: ");
        p2.printStats();
    }
}
