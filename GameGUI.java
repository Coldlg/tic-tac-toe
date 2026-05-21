import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI extends JFrame {
    private Engine game;
    private JButton[][] buttons;
    private JLabel statusLabel;

    public GameGUI() {
        game = new Engine();
        buttons = new JButton[3][3];

        // 1. Setup window properties
        setTitle("Tic-Tac-Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centers window on screen
        setLayout(new BorderLayout());

        // 2. Create the top status label
        statusLabel = new JLabel("Player X's Turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(statusLabel, BorderLayout.NORTH);

        // 3. Create the 3x3 grid layout for the buttons
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3, 5, 5)); // 3x3 grid with 5px gaps
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Populate the grid with buttons mapped to row/col coordinates
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton(" ");
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 40));
                buttons[row][col].setFocusPainted(false);

                // Variables inside lambda must be effectively final
                final int r = row;
                final int c = col;

                // Add click listener to each button
                buttons[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleMove(r, c);
                    }
                });

                boardPanel.add(buttons[row][col]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        // 4. Create a bottom Reset Button
        JButton resetButton = new JButton("Reset Game");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 14));
        resetButton.addActionListener(e -> resetGUI());
        add(resetButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Connects UI clicks directly into your Engine's methods.
     */
    private void handleMove(int row, int col) {
        char playerWhoMoved = game.getCurrentPlayer();

        // Pass the row/col straight to your Engine
        if (game.makeMove(row, col)) {
            // Update the clicked button text
            buttons[row][col].setText(String.valueOf(playerWhoMoved));

            // Check if that move won the game
            if (game.checkWin()) {
                statusLabel.setText("🎉 Player " + playerWhoMoved + " Wins!");
                disableBoard();
                return;
            }

            // Check if that move drew the game
            if (game.isDraw()) {
                statusLabel.setText("🤝 It's a Draw!");
                return;
            }

            // Move safely made, swap turns
            game.switchPlayer();
            statusLabel.setText("Player " + game.getCurrentPlayer() + "'s Turn");
        }
    }

    /**
     * Locks down the board elements when someone wins
     */
    private void disableBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    /**
     * Resets both your engine backend and the buttons frontend
     */
    private void resetGUI() {
        game.resetGame(); // Resets your internal engine arrays & states
        statusLabel.setText("Player X's Turn");
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(" ");
                buttons[i][j].setEnabled(true);
            }
        }
    }

    // Launch the Graphical Application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameGUI());
    }
}   