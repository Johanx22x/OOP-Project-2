import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    public Score() {
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
        txtPlayer1.setText(App.player1.getScore());
        background.add(txtPlayer1);

        txtPlayer2.setBounds(300, 110, 100, 50);
        txtPlayer2.setFont(new Font("Arial", Font.BOLD, 20));
        txtPlayer2.setForeground(Color.BLACK);
        txtPlayer2.setEditable(false);
        txtPlayer2.setHorizontalAlignment(JTextField.CENTER);
        txtPlayer2.setText(App.player2.getScore());
        background.add(txtPlayer2);

        txtPlayer3.setBounds(300, 170, 100, 50);
        txtPlayer3.setFont(new Font("Arial", Font.BOLD, 20));
        txtPlayer3.setForeground(Color.BLACK);
        txtPlayer3.setEditable(false);
        txtPlayer3.setHorizontalAlignment(JTextField.CENTER);
        txtPlayer3.setText(App.player3.getScore());
        background.add(txtPlayer3);

        txtPlayer4.setBounds(300, 230, 100, 50);
        txtPlayer4.setFont(new Font("Arial", Font.BOLD, 20));
        txtPlayer4.setForeground(Color.BLACK);
        txtPlayer4.setEditable(false);
        txtPlayer4.setHorizontalAlignment(JTextField.CENTER);
        txtPlayer4.setText(App.player4.getScore());
        background.add(txtPlayer4);

        // Add the exit button
        btnExit.setBounds(250, 300, 100, 30);
        btnExit.setFont(new Font("Arial", Font.BOLD, 20));
        btnExit.setForeground(Color.BLACK);
        btnExit.addActionListener(this);
        background.add(btnExit);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnExit) {
            this.dispose();
        }
    }
}
