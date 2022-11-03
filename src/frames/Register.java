package frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Class to manage the register frame, here the user can register a new account
 *
 * @author Johan Rodriguez
 * @version 1.0
 */
public class Register extends JFrame implements ActionListener {
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel passwordLabel2;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField passwordField2;
    private JButton registerButton;
    private JButton cancelButton;

    /**
     * Constructor of the class
     */
    public Register() {
        // Set the frame characteristics
        super("Register");
        setSize(450, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        usernameLabel = new JLabel("Username: ");
        usernameLabel.setPreferredSize(new Dimension(185, 40));
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        usernameField = new JTextField(10);
        usernameField.setPreferredSize(new Dimension(170, 40));
        usernameField.setFont(new Font("Arial", Font.PLAIN, 20));

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setPreferredSize(new Dimension(185, 40));
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        passwordField = new JPasswordField(10);
        passwordField.setPreferredSize(new Dimension(170, 40));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));

        passwordLabel2 = new JLabel("Confirm Password: ");
        passwordLabel2.setPreferredSize(new Dimension(185, 40));
        passwordLabel2.setFont(new Font("Arial", Font.PLAIN, 20));

        passwordField2 = new JPasswordField(10);
        passwordField2.setPreferredSize(new Dimension(170, 40));
        passwordField2.setFont(new Font("Arial", Font.PLAIN, 20));

        registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(150, 40));
        registerButton.setFont(new Font("Arial", Font.PLAIN, 20));

        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(150, 40));
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 20));

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(passwordLabel2);
        add(passwordField2);
        add(registerButton);
        add(cancelButton);

        registerButton.addActionListener(this);
        cancelButton.addActionListener(this);

        setVisible(true);
    }

    /**
     * Method to manage the actions of the buttons
     *
     * @param e ActionEvent
     */
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == registerButton) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String password2 = passwordField2.getText();

            if (username.equals("") || password.equals("") || password2.equals("")) {
                // If any field is empty
                JOptionPane.showMessageDialog(null, "Please fill in all fields");
            } else if (Login.users.containsKey(username)) { // Check if the username already exists
                JOptionPane.showMessageDialog(null, "The username already exists", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!password.equals(password2)) {
                // If the passwords are not the same
                JOptionPane.showMessageDialog(null, "Passwords do not match");
            } else {
                // Add the new user to the Login HashMap
                Login.users.put(username, password);
                Login.writeUsers(Login.users);
                JOptionPane.showMessageDialog(null, "Registration successful");
                dispose();
            }
        } else if (event.getSource() == cancelButton) {
            // Close the frame
            dispose();
        }
    }
}
