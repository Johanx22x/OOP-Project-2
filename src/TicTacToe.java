import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton exitButton = new JButton("Exit");
    private JButton[][] buttons = new JButton[3][3];

    public TicTacToe() {
        super("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(300, 300);
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

        exitButton.addActionListener(this);
        add(exitButton);
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if (actionCommand.equals("Exit")) {
            this.dispose();
        }
    }
}
