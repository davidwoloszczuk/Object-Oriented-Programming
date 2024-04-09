/**  David Woloszczuk-Mrugala
 *   CPSC 24500
 *   02/23/24
 *   This is my version of Rock Paper Scissors
 */

package a3;

import java.util.Random;
import java.util.Scanner;

public class Assignment3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        displayRules();

        boolean playAgain = true;
        while (playAgain) {
            int computerChoice = getComputerChoice(random);
            int userChoice = getUserChoice(scanner);

            displayChoices(computerChoice, userChoice);

            int result = determineWinner(computerChoice, userChoice);
            displayResult(result);

            playAgain = askPlayAgain(scanner);
        }

        scanner.close();
    }

    public static void displayRules() {
        System.out.println("Welcome to Rock, Paper, Scissors!");
        System.out.println("Rules:");
        System.out.println("- Rock beats Scissors");
        System.out.println("- Scissors beats Paper");
        System.out.println("- Paper beats Rock");
        System.out.println("- If both players make the same choice, the game is tied");
        System.out.println();
    }

    public static int getComputerChoice(Random random) {
        return random.nextInt(3) + 1;
    }

    public static int getUserChoice(Scanner scanner) {
        int userChoice;
        while (true) {
            System.out.print("Enter your choice (1 for Rock, 2 for Paper, 3 for Scissors): ");
            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                if (userChoice >= 1 && userChoice <= 3) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); 
            }
        }
        return userChoice;
    }

    public static void displayChoices(int computerChoice, int userChoice) {
        System.out.println("Computer's choice: " + getChoiceName(computerChoice));
        System.out.println("Your choice: " + getChoiceName(userChoice));
        System.out.println();
    }

    public static int determineWinner(int computerChoice, int userChoice) {
        if (computerChoice == userChoice) {
            return 0;
        } else if (
            (computerChoice == 1 && userChoice == 3) ||
            (computerChoice == 2 && userChoice == 1) ||
            (computerChoice == 3 && userChoice == 2)
        ) {
            return -1;
        } else {
            return 1;
        }
    }

    public static void displayResult(int result) {
        if (result == 0) {
            System.out.println("It's a tie!");
        } else if (result == 1) {
            System.out.println("You win!");
        } else {
            System.out.println("Computer wins!");
        }
        System.out.println();
    }

    public static boolean askPlayAgain(Scanner scanner) {
        System.out.print("Do you want to play again? (Y/N): ");
        String playAgain = scanner.next();
        return playAgain.equalsIgnoreCase("Y");
    }

    public static String getChoiceName(int choice) {
        switch (choice) {
            case 1:
                return "Rock";
            case 2:
                return "Paper";
            case 3:
                return "Scissors";
            default:
                return "";
        }
    }
}
