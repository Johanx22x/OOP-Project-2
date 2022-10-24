import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener {
    private JLabel lblWelcome = new JLabel("Welcome!");
    private JButton btnExit = new JButton("Exit");
    private JButton btnTicTacToe = new JButton("Tic Tac Toe");
    private JButton btnScore = new JButton("Score");

    public Menu() {
        super("Main Menu");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        // Add an image to the background
        ImageIcon img = new ImageIcon("img/background.jpg");
        JLabel background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, 0, 500, 500);
        add(background);

        // Add the welcome label
        lblWelcome.setBounds(175, 50, 150, 50);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 30));
        lblWelcome.setForeground(Color.BLACK);
        background.add(lblWelcome);

        // Add the exit button
        btnExit.setBounds(200, 400, 100, 50);
        btnExit.setFont(new Font("Arial", Font.BOLD, 20));
        btnExit.setForeground(Color.BLACK);
        btnExit.addActionListener(this);
        background.add(btnExit);

        // Add the Tic Tac Toe button
        btnTicTacToe.setBounds(175, 125, 150, 50);
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

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Exit")) {
            System.exit(0);
        } else if (command.equals("Tic Tac Toe")) {
            new TicTacToe();
        } else if (command.equals("Score")) {
            new Score();
        }
    }
}
