public class Engine {
    private char[][] board;
    private char currentPlayer;
    private boolean gameActive;

    // Constructor: Sets up a fresh game
    public Engine() {
        board = new char[3][3];
        resetGame();
    }

    // Fills the board with empty spaces and sets starting player to 'X'
    public void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' '; // Using a space for empty slots
            }
        }
        currentPlayer = 'X';
        gameActive = true;
    }

    /**
     * Attempts to place a mark on the board.
     * @param row The row index (0-2)
     * @param col The column index (0-2)
     * @return true if the move was successful, false if invalid or game over.
     */
    public boolean makeMove(int row, int col) {
        // Reject moves if the game is already over
        if (!gameActive) {
            return false;
        }

        // Validate boundary conditions
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            return false;
        }

        // Check if the slot is empty
        if (board[row][col] == ' ') {
            board[row][col] = currentPlayer;
            return true;
        }

        return false; // Slot already taken
    }

    // Switches the turn from X to O, or O to X
    public void switchPlayer() {
        if (gameActive) {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    /**
     * Checks if the current player has won the game.
     * If a win is found, it deactivates the game.
     */
    public boolean checkWin() {
        // 1. Check Rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                gameActive = false;
                return true;
            }
        }

        // 2. Check Columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
                gameActive = false;
                return true;
            }
        }

        // 3. Check Main Diagonal (top-left to bottom-right)
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            gameActive = false;
            return true;
        }

        // 4. Check Anti-Diagonal (top-right to bottom-left)
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            gameActive = false;
            return true;
        }

        return false;
    }

    /**
     * Checks if the board is completely full with no winner (a draw).
     */
    public boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // Found an empty space, so it's not a draw yet
                }
            }
        }
        gameActive = false; // Board is full, game is over
        return true;
    }

    // --- Getters for your partner's UI code ---

    public char[][] getBoard() {
        return board;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameActive() {
        return gameActive;
    }
}