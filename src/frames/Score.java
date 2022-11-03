package frames;

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
