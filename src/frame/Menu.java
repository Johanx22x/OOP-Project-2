package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import juego1.TicTacToe;

/**
 * Class to manage the menu Frame for the application.
 *
 * @author Johan Rodriguez
 * @version 1.0
 */
public class Menu extends JFrame implements ActionListener {
    private final JLabel lblWelcome = new JLabel("Welcome!");
    private final JButton btnExit = new JButton("Exit");
    private final JButton btnTicTacToe = new JButton("Tic Tac Toe");
    private final JButton btnScore = new JButton("Score");

    /**
     * Constructor for objects of class Menu
     *
     * @param username the username of the player
     */
    public Menu(String username) {
        // Set frame properties
        super("Main Menu");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        // Add an image to the background
        final ImageIcon img = new ImageIcon("img/background.jpg");
        final JLabel background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, 0, 500, 500);
        add(background);

        // Add the welcome label
        lblWelcome.setBounds(175, 50, 200, 25);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 30));
        lblWelcome.setForeground(Color.BLACK);
        background.add(lblWelcome);

        // Add the username label and center it
        final JLabel lblUsername = new JLabel(username);
        lblUsername.setBounds(0, 100, 500, 25);
        lblUsername.setFont(new Font("Arial", Font.BOLD, 25));
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setHorizontalAlignment(JLabel.CENTER);
        background.add(lblUsername);

        // Add the Tic Tac Toe button
        btnTicTacToe.setBounds(175, 150, 150, 50);
        btnTicTacToe.setFont(new Font("Arial", Font.BOLD, 20));
        btnTicTacToe.setForeground(Color.BLACK);
        btnTicTacToe.addActionListener(this);
        background.add(btnTicTacToe);

        // Add the score button
        btnScore.setBounds(175, 325, 150, 50);
        btnScore.setFont(new Font("Arial", Font.BOLD, 20));
        btnScore.setForeground(Color.BLACK);
        btnScore.addActionListener(this);
        background.add(btnScore);

        // Add the exit button
        btnExit.setBounds(200, 400, 100, 50);
        btnExit.setFont(new Font("Arial", Font.BOLD, 20));
        btnExit.setForeground(Color.BLACK);
        btnExit.addActionListener(this);
        background.add(btnExit);

        setVisible(true);
    }

    /**
     * Method to handle the events of the buttons
     *
     * @param e The event
     */
    public void actionPerformed(final ActionEvent e) {
        final String command = e.getActionCommand();

        if (command.equals("Exit")) {
            // Exit the application
            System.exit(0);
        } else if (command.equals("Tic Tac Toe")) {
            // Open the Tic Tac Toe game
            TicTacToe juego1 = new TicTacToe();
            juego1.iniciarPartida(null);
        } else if (command.equals("Score")) {
            // Open the score frame
            new Score();
        }
    }
}
