package login;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.HashMap;

import player.Player;

import interfaces.iJugador;

/**
 * LoginFrame class, here the user can login or register.
 *
 * @author Johan Rodriguez
 * @version 1.0
 */
public class LoginFrame extends JFrame implements ActionListener {
    public static HashMap<String, String> users = new HashMap<String, String>();
    private String username = "";

    private JLabel userLabel;
    private JLabel passwordLabel;
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    private JButton registerButton;
    private JPanel panel;
    private Boolean loginSuccess = false;

    /**
     * Constructor of the class, here the frame is created.
     */
    public LoginFrame() {
        // Set the frame properties
        super("Login");
        setSize(450, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        userLabel = new JLabel("Username: ");
        userLabel.setPreferredSize(new Dimension(150, 40));
        userLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setPreferredSize(new Dimension(150, 40));
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        userField = new JTextField(10);
        userField.setPreferredSize(new Dimension(170, 40));
        userField.setFont(new Font("Arial", Font.PLAIN, 20));

        passwordField = new JPasswordField(10);
        passwordField.setPreferredSize(new Dimension(170, 40));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));

        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(150, 40));
        loginButton.setFont(new Font("Arial", Font.PLAIN, 20));

        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(150, 40));
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 20));

        registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(150, 40));
        registerButton.setFont(new Font("Arial", Font.PLAIN, 20));

        panel = new JPanel();

        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);
        registerButton.addActionListener(this);

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(cancelButton);
        panel.add(registerButton);

        add(panel);
        setVisible(true);
    }

    /**
     * Method that listens to the buttons of the login frame.
     *
     * @param e ActionEvent
     * @return void
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String user = userField.getText();
            String password = passwordField.getText();

            // Check if the user exists, into the HashMap
            if (!users.containsKey(user)) {
                JOptionPane.showMessageDialog(null, "Incorrect credentials", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } 

            // Check if the password is correct
            if (!users.get(user).equals(password)) {
                JOptionPane.showMessageDialog(null, "Incorrect credentials", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            loginSuccess = true;
            username = user;
            dispose();

        } else if (e.getSource() == cancelButton) {
            System.exit(0);
        } else if (e.getSource() == registerButton) {
            new RegisterFrame();
        }
    }

    /**
     * Method that returns the username of the user that logged in.
     *
     * @return String
     */
    public iJugador getUser() {
        return new Player(username);
    }

    /**
     * Method that returns the login success.
     *
     * @return Boolean
     */
    public Boolean getLoginSuccess() {
        return loginSuccess;
    }

    /**
     * Checks if the default file exists, if not, it creates it.
     * Also, creates the default admin user.
     *
     * @return nothing
     */
    private static void checkDefaultFile() {
        File file = new File("users.txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
                
                // Write default users 
                FileWriter writer = new FileWriter(file);
                writer.write("admin,admin\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Reads the users from the file and stores them in a HashMap.
     *
     * @return HashMap with the users
     */
    public static void readUsers() {
        HashMap<String, String> users = new HashMap<>();

        // Check the users file
        checkDefaultFile();

        try {
            File file = new File("users.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                users.put(data[0], data[1]);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        LoginFrame.users = users;
    }

    /**
     * Writes the users to the file.
     *
     * @param users HashMap with the users
     * @return nothing
     */
    public static void writeUsers(HashMap<String, String> users) {
        try {
            FileWriter fileWriter = new FileWriter("users.txt");
            for (String user : users.keySet()) {
                fileWriter.write(user + "," + users.get(user) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
