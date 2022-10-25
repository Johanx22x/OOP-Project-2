package juego1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * TicTacToe game main class, here we create the game board and the game logic.
 *
 * @author Johan Rodriguez
 * @version 1.0
 */
public class TicTacToe extends JFrame implements ActionListener {
    private JButton exitButton = new JButton("Exit");
    private JButton[][] buttons = new JButton[3][3];
    private int[][] board = new int[3][3];
    private boolean gameOver = false;
    private int turn = 0;

    /**
     * Constructor, here is created the game board and the game logic.
     */
    public TicTacToe() {
        super("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(false);
        setSize(250, 190);
        setVisible(true);

        // Add a grid with the buttons
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                // Set the dimensions of the buttons 
                buttons[i][j].setPreferredSize(new Dimension(50, 50));
                buttons[i][j].addActionListener(this);
                grid.add(buttons[i][j]);
            }
        }
        add(grid);

        // Add the exit button
        exitButton.addActionListener(this);
        add(exitButton);
    }

    /**
     * This method is called when a button is pressed.
     *
     * @param e The event that triggered the method.
     */
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        // If the exit button is pressed, close the game
        if (actionCommand.equals("Exit")) {
            this.dispose();
        }

        // Check if a button was pressed
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (e.getSource() == buttons[i][j]) {
                    if (turn % 2 == 0) {
                        buttons[i][j].setText("X");
                        board[i][j] = 1;
                    } else {
                        buttons[i][j].setText("O");
                        board[i][j] = 2;
                    }
                    buttons[i][j].setEnabled(false);
                    turn++;
                    checkWin();
                }
            }
        }

        // Check if the game is over
        if (gameOver) {
            this.dispose();
        }

    }

    /**
     * Check if there is a winner.
     */
    public void checkWin() {
        // Check for a horizontal win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if (board[i][0] == 1) {
                    JOptionPane.showMessageDialog(null, "X wins!");
                    gameOver = true;
                } else if (board[i][0] == 2) {
                    JOptionPane.showMessageDialog(null, "O wins!");
                    gameOver = true;
                }
            }
        }

        // Check for a vertical win
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                if (board[0][i] == 1) {
                    JOptionPane.showMessageDialog(null, "X wins!");
                    gameOver = true;
                } else if (board[0][i] == 2) {
                    JOptionPane.showMessageDialog(null, "O wins!");
                    gameOver = true;
                }
            }
        }

        // Check for a diagonal win
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 1) {
                JOptionPane.showMessageDialog(null, "X wins!");
                gameOver = true;
            } else if (board[0][0] == 2) {
                JOptionPane.showMessageDialog(null, "O wins!");
                gameOver = true;
            }
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == 1) {
                JOptionPane.showMessageDialog(null, "X wins!");
                gameOver = true;
            } else if (board[0][2] == 2) {
                JOptionPane.showMessageDialog(null, "O wins!");
                gameOver = true;
            }
        }

        // Check for a tie
        if (turn == 9 && !gameOver) {
            JOptionPane.showMessageDialog(null, "Tie!");
        }
    }
}
