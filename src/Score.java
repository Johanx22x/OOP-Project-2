import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class is used to display the score of the game.
 *
 * @author Johan Rodriguez
 * @version 1.0
 */
public class Score extends JFrame implements ActionListener {
    JButton btnExit = new JButton("Exit");

    // Labels for 4 players 
    JLabel lblPlayer1 = new JLabel("Player 1");
    JLabel lblPlayer2 = new JLabel("Player 2");
    JLabel lblPlayer3 = new JLabel("Player 3");
    JLabel lblPlayer4 = new JLabel("Player 4");

    // Text fields for 4 players
    JTextField txtPlayer1 = new JTextField();
    JTextField txtPlayer2 = new JTextField();
    JTextField txtPlayer3 = new JTextField();
    JTextField txtPlayer4 = new JTextField();

    /**
     * Constructor for objects of class Score
     */
    public Score() {
        // Set frame properties
        super("Score");
        setSize(600, 370);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Add an image to the background
        ImageIcon image = new ImageIcon("img/podium.jpg");
        JLabel background = new JLabel("", image, JLabel.CENTER);
        background.setBounds(0, 0, 600, 370);
        add(background);

        // Add players Labels
        lblPlayer1.setBounds(200, 50, 100, 50);
        lblPlayer1.setFont(new Font("Arial", Font.BOLD, 20));
        lblPlayer1.setForeground(Color.BLACK);
        lblPlayer1.setBackground(Color.WHITE);
        background.add(lblPlayer1);

        lblPlayer2.setBounds(200, 110, 100, 50);
        lblPlayer2.setFont(new Font("Arial", Font.BOLD, 20));
        lblPlayer2.setForeground(Color.BLACK);
        lblPlayer2.setBackground(Color.WHITE);
        background.add(lblPlayer2);

        lblPlayer3.setBounds(200, 170, 100, 50);
        lblPlayer3.setFont(new Font("Arial", Font.BOLD, 20));
        lblPlayer3.setForeground(Color.BLACK);
        lblPlayer3.setBackground(Color.WHITE);
        background.add(lblPlayer3);
        
        lblPlayer4.setBounds(200, 230, 100, 50);
        lblPlayer4.setFont(new Font("Arial", Font.BOLD, 20));
        lblPlayer4.setForeground(Color.BLACK);
        background.add(lblPlayer4);

        // Add players text fields
        txtPlayer1.setBounds(300, 50, 100, 50);
        txtPlayer1.setFont(new Font("Arial", Font.BOLD, 20));
        txtPlayer1.setForeground(Color.BLACK);
        txtPlayer1.setEditable(false);
        txtPlayer1.setHorizontalAlignment(JTextField.CENTER);
        Player player1 = App.getPlayer1();
        if (player1 != null) {
            txtPlayer1.setText(String.valueOf(player1.getScore()));
        } else {
            txtPlayer1.setText("0");
        }
        background.add(txtPlayer1);

        txtPlayer2.setBounds(300, 110, 100, 50);
        txtPlayer2.setFont(new Font("Arial", Font.BOLD, 20));
        txtPlayer2.setForeground(Color.BLACK);
        txtPlayer2.setEditable(false);
        txtPlayer2.setHorizontalAlignment(JTextField.CENTER);
        Player player2 = App.getPlayer2();
        if (player2 != null) {
            txtPlayer2.setText(String.valueOf(player2.getScore()));
        } else {
            txtPlayer2.setText("0");
        }
        background.add(txtPlayer2);

        txtPlayer3.setBounds(300, 170, 100, 50);
        txtPlayer3.setFont(new Font("Arial", Font.BOLD, 20));
        txtPlayer3.setForeground(Color.BLACK);
        txtPlayer3.setEditable(false);
        txtPlayer3.setHorizontalAlignment(JTextField.CENTER);
        Player player3 = App.getPlayer3();
        if (player3 != null) {
            txtPlayer3.setText(String.valueOf(player3.getScore()));
        } else {
            txtPlayer3.setText("0");
        }
        background.add(txtPlayer3);

        txtPlayer4.setBounds(300, 230, 100, 50);
        txtPlayer4.setFont(new Font("Arial", Font.BOLD, 20));
        txtPlayer4.setForeground(Color.BLACK);
        txtPlayer4.setEditable(false);
        txtPlayer4.setHorizontalAlignment(JTextField.CENTER);
        Player player4 = App.getPlayer4();
        if (player4 != null) {
            txtPlayer4.setText(String.valueOf(player4.getScore()));
        } else {
            txtPlayer4.setText("0");
        }
        background.add(txtPlayer4);

        // Add the exit button
        btnExit.setBounds(250, 300, 100, 30);
        btnExit.setFont(new Font("Arial", Font.BOLD, 20));
        btnExit.setForeground(Color.BLACK);
        btnExit.addActionListener(this);
        background.add(btnExit);

        setVisible(true);
    }

    /**
     * This method is used to handle the events of the buttons.
     *
     * @param e The event that is triggered.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnExit) {
            this.dispose();
        }
    }
}
