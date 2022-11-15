package frames;

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
import juego2.Memory;
import juego3.HiddenNumberGameUI;

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
    private ArrayList<iRegistro> records = new ArrayList<iRegistro>();
    private iJugador player;

    /**
     * Game center constructor
     * 
     * @param username Username of the user
     * @param ArrayList<iRegistro> records Records of the games
     */
    public GameCenter(iJugador player, ArrayList<iRegistro> records) {
        // Set frame properties
        super("Game Center");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        // Set the player
        this.player = player;

        // Set the records 
        this.records = records;

        // Add the games
        games.add(new TicTacToe());
        games.add(new Memory());
        games.add(new HiddenNumberGameUI());

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
            btnGame.setBounds(125, 150 + (i * 75), 250, 50);
            btnGame.setFont(new Font("Arial", Font.BOLD, 20));
            btnGame.setForeground(Color.BLACK);
            btnGame.addActionListener(this);
            background.add(btnGame);
        }

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
        ArrayList<iRegistro> gameRecords = new ArrayList<iRegistro>();

        for (iRegistro record : records) {
            if (record.getTipoJuego().getNombre().equals(tipoJuego.getNombre())) {
                gameRecords.add(record);
            }
        }

        return gameRecords;
    }

    /**
     * Method that add a new record to the game center 
     *
     * @param record New record to add
     */
    public void addRegistro(iRegistro record) {
        records.add(record);
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

        if (command.equals("Tic Tac Toe")) {
            TicTacToe game = new TicTacToe();
            game.iniciarPartida(player, this);
        } else if (command.equals("Memory Game")) {
            Memory game = new Memory();
            game.iniciarPartida(player, this);
        } else if (command.equals("Hidden Number")) {
            HiddenNumberGameUI game = new HiddenNumberGameUI();
            game.iniciarPartida(player, this);
        } else if (command.equals("Exit")) {
            // Exit the application
            System.exit(0);
        }
    }
}
