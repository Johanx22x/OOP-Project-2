package frame;

import java.util.ArrayList;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import juego1.TicTacToe;

import interfaces.iJuego;
import interfaces.iRegistro;
import interfaces.iJugador;
import interfaces.iCentroJuego;

/**
 * Class that represents the Game Center
 *
 * @author Johan Rodriguez
 * @version 1.0
 */
public class GameCenter extends JFrame implements iCentroJuego, ActionListener {
    private ArrayList<iJuego> games = new ArrayList<iJuego>();
    private iJugador player;

    /**
     * Game center constructor
     * 
     * @param username Username of the user
     */
    public GameCenter(iJugador player) {
        // Set frame properties
        super("Game Center");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        // Set the player
        this.player = player;

        // Add the games
        games.add(new TicTacToe());

        // Add an image to the background
        final ImageIcon img = new ImageIcon("img/background.jpg");
        final JLabel background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, 0, 500, 500);
        add(background);

        // Add the welcome label
        JLabel lblWelcome = new JLabel("Welcome!");
        lblWelcome.setBounds(175, 50, 200, 25);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 30));
        lblWelcome.setForeground(Color.BLACK);
        background.add(lblWelcome);

        // Add the username label and center it
        final JLabel lblUsername = new JLabel(player.getNombre());
        lblUsername.setBounds(0, 100, 500, 25);
        lblUsername.setFont(new Font("Arial", Font.BOLD, 25));
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setHorizontalAlignment(JLabel.CENTER);
        background.add(lblUsername);

        // For each game add a button 
        for (int i = 0; i < games.size(); i++) {
            final JButton btnGame = new JButton(games.get(i).getNombre());
            btnGame.setBounds(175, 150 + (i * 50), 150, 50);
            btnGame.setFont(new Font("Arial", Font.BOLD, 20));
            btnGame.setForeground(Color.BLACK);
            btnGame.addActionListener(this);
            background.add(btnGame);
        }

        // Add the score button
        JButton btnScore = new JButton("Score");
        btnScore.setBounds(175, 325, 150, 50);
        btnScore.setFont(new Font("Arial", Font.BOLD, 20));
        btnScore.setForeground(Color.BLACK);
        btnScore.addActionListener(this);
        background.add(btnScore);

        // Add the exit button
        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(200, 400, 100, 50);
        btnExit.setFont(new Font("Arial", Font.BOLD, 20));
        btnExit.setForeground(Color.BLACK);
        btnExit.addActionListener(this);
        background.add(btnExit);

        setVisible(true);
    }

    /**
     * Method that return the register of the game center 
     *
     * @return ArrayList<iRegistro> register of the game center
     */
    public ArrayList<iRegistro> getRegistros(iJuego tipoJuego) {
        // TODO:
        return null;
    }

    /**
     * Method that returns the list of games
     *
     * @return ArrayList<iJuego> List of games
     */
    public ArrayList<iJuego> getJuegosDisponibles() {
        return games;
    }

    /**
     * Method to handle the events of the buttons
     *
     * @param e The event
     */
    public void actionPerformed(final ActionEvent e) {
        final String command = e.getActionCommand();

        // If the command is a game name
        for (int i = 0; i < games.size(); i++) {
            if (command.equals(games.get(i).getNombre())) {
                games.get(i).iniciarPartida(player);
            }
        }

        if (command.equals("Exit")) {
            // Exit the application
            System.exit(0);
        } else if (command.equals("Score")) {
            // Open the score frame
            new Score();
        }
    }
}
