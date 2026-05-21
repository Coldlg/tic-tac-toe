import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Engine game = new Engine();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic-Tac-Toe!");
        printBoard(game.getBoard());

        // Main game loop
        while (game.isGameActive()) {
            char currentPlayer = game.getCurrentPlayer();
            System.out.println("\nPlayer " + currentPlayer + "'s turn.");
            
            // Get valid coordinates from the user
            int row = getValidInput(scanner, "Enter row (0-2): ");
            int col = getValidInput(scanner, "Enter column (0-2): ");

            // Attempt to make the move
            boolean moveSuccessful = game.makeMove(row, col);

            if (moveSuccessful) {
                printBoard(game.getBoard());

                // Check if this move won the game
                if (game.checkWin()) {
                    System.out.println("\n🎉 Player " + currentPlayer + " wins! Congratulations!");
                    break;
                }

                // Check if this move resulted in a draw
                if (game.isDraw()) {
                    System.out.println("\n🤝 It's a draw! Good game!");
                    break;
                }

                // If no win or draw, pass the turn to the next player
                game.switchPlayer();
            } else {
                System.out.println("❌ Invalid move! That spot is either taken or out of bounds. Try again.");
            }
        }

        scanner.close();
        System.out.println("Thanks for playing!");
    }

    /**
     * Helper method to print the 3x3 board cleanly in the console.
     */
    private static void printBoard(char[][] board) {
        System.out.println("\n  0   1   2 "); // Column labels
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " "); // Row label
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) {
                System.out.println("  ---------");
            }
        }
    }

    /**
     * Helper method to ensure the user actually enters an integer
     * preventing the program from crashing if they type text by accident.
     */
    private static int getValidInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Please enter a valid number (0, 1, or 2).");
                scanner.next(); // Clear the invalid token
            }
        }
    }
}