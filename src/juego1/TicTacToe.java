package juego1;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Comparator;
import java.util.HashMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import interfaces.iJuego;
import interfaces.iJugador;
import interfaces.iRegistro;
import interfaces.iCentroJuego;

import register.Register;

/**
 * TicTacToe game main class, here we create the game board and the game logic.
 *
 * @author Johan Rodriguez
 * @version 1.0
 */
public class TicTacToe extends JFrame implements ActionListener, iJuego {
    private final JFrame game = new JFrame("Tic Tac Toe");
    private final String gameTitle = "Tic Tac Toe";
    private final String gameDescription = "<html>In this game you will play against the computer,<br> you will have to put 3 X's or O's in a row to win.<html>";
    private final JButton exitButton = new JButton("Exit");
    private final JButton[][] buttons = new JButton[3][3];
    private final int[][] board = new int[3][3];
    private int turn = 0;
    private int playerTurn = 1;
    private int playerScore = 0;
    private Register register;
    private iCentroJuego center;
    private iJugador player;

    /**
     * Method to start the game.
     */
    public void game() {
        register.setInicio(LocalDateTime.now());
        game.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                terminarPartida();
            }
        });
        game.setLayout(new FlowLayout());
        game.setResizable(false);
        game.setSize(500, 550);
        game.setVisible(true);

        // Add a grid with the buttons
        final JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                // Set the dimensions of the buttons 
                buttons[i][j].setPreferredSize(new Dimension(150, 150));
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 50));
                buttons[i][j].addActionListener(this);
                grid.add(buttons[i][j]);
            }
        }
        game.add(grid);

        // Add the exit button
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.dispose();
            }
        });

        game.add(exitButton);

        // Check the first turn
        if (playerTurn == 1) {
            JOptionPane.showMessageDialog(null, "You start first!");
        } else {
            JOptionPane.showMessageDialog(null, "The computer starts first!");
            computerTurn();
        }
    }

    /**
     * Method to show to the user the game name, description and the rules.
     * The user can choose to play or not, and the symbol he wants to use.
     */
    public void iniciarPartida(final iJugador jugador, iCentroJuego centroJuegos) {
        register = new Register(jugador);
        register.setGame(this);

        player = jugador;
        center = centroJuegos;

        // Create a new frame showing the game title and description, an Start button and an Exit button 
        final JFrame frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);
        frame.setSize(500, 500);
        frame.setVisible(true);

        // Add the game title 
        final JLabel title = new JLabel(gameTitle); 
        title.setFont(new Font("Arial", Font.BOLD, 40)); 
        frame.add(title); 

        // Add blank space here 
        final JLabel blankSpace = new JLabel(" ");
        blankSpace.setPreferredSize(new Dimension(450, 25));
        frame.add(blankSpace);

        // Create a button to display personal stats
        final JButton statsButton = new JButton("Personal Stats");
        statsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a new frame to show the stats
                JFrame statsFrame = new JFrame("Personal Stats");
                statsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                statsFrame.setLayout(new FlowLayout());
                statsFrame.setResizable(false);
                statsFrame.setSize(500, 650);

                // Add a text area to show the stats 
                JTextArea stats = new JTextArea();
                stats.setPreferredSize(new Dimension(450, 450));
                stats.setEditable(false);

                // Data variables
                HashMap<LocalDate, Integer> playedDays = new HashMap<LocalDate, Integer>();
                int totalGames = 0;
                int totalHours = 0;

                for (iRegistro register: centroJuegos.getRegistros(TicTacToe.this)) {
                    if (register.getJugador().getNombre().equals(jugador.getNombre())) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                        stats.append("Start time: " + register.getInicio().format(formatter) + "\n" +
                                "End time: " + register.getFinalizacion().format(formatter) + "\n" +
                                "Score: " + register.getPuntaje() + "\n" +
                                "Total time: " + register.getSegundosTotalesPartida() + " seconds" + "\n" +
                                "Finished: " + register.getEstadoFinalizado() + "\n\n");
                        totalGames++;
                        totalHours += register.getSegundosTotalesPartida();

                        if (playedDays.containsKey(register.getInicio().toLocalDate())) {
                            playedDays.put(register.getInicio().toLocalDate(), playedDays.get(register.getInicio().toLocalDate()) + 1);
                        } else {
                            playedDays.put(register.getInicio().toLocalDate(), 1);
                        }
                    }
                }

                // Show the average time played per day according to the total HashMap
                int totalDays = 0;
                for (LocalDate date: playedDays.keySet()) {
                    totalDays++;
                }
                JLabel averageTime;
                if (totalDays != 0) {
                    averageTime = new JLabel("Average time played per day: " + totalHours / totalDays + " seconds");
                } else {
                    averageTime = new JLabel("Average time played per day: 0 seconds");
                }
                averageTime.setPreferredSize(new Dimension(450, 25));
                statsFrame.add(averageTime);

                JLabel totalGamesLabel = new JLabel("Total games: " + totalGames);
                totalGamesLabel.setPreferredSize(new Dimension(450, 25));
                statsFrame.add(totalGamesLabel);

                // Show total hours using hh:mm:ss format 
                int hours = totalHours / 3600;
                int minutes = (totalHours % 3600) / 60;
                int seconds = totalHours % 60;
                JLabel totalHoursLabel = new JLabel("Total hours: " + String.format("%02d:%02d:%02d", hours, minutes, seconds));
                totalHoursLabel.setPreferredSize(new Dimension(450, 25));
                statsFrame.add(totalHoursLabel);

                statsFrame.add(stats);

                JButton exitButton = new JButton("Back");
                exitButton.setPreferredSize(new Dimension(450, 50));
                exitButton.setFont(new Font("Arial", Font.BOLD, 20));
                exitButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        statsFrame.dispose();
                    }
                });
                statsFrame.add(exitButton);

                statsFrame.setVisible(true);
            }
        });
        statsButton.setPreferredSize(new Dimension(300, 50));
        statsButton.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.add(statsButton);

        // Add blank space here 
        final JLabel blankSpace2 = new JLabel(" ");
        blankSpace2.setPreferredSize(new Dimension(450, 25));
        frame.add(blankSpace2);
        
        // Create a button to display general stats
        final JButton generalStatsButton = new JButton("General Stats");
        generalStatsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame statsFrame = new JFrame("General Stats");
                statsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                statsFrame.setLayout(new FlowLayout());
                statsFrame.setResizable(false);
                statsFrame.setSize(500, 550);

                // Add a text area to show the stats 
                JTextArea stats = new JTextArea();
                stats.setPreferredSize(new Dimension(450, 450));
                stats.setEditable(false);

                // Show only the 10 best scores of centroJuegos.getRegistros(TicTacToe.this)
                List<iRegistro> registers = centroJuegos.getRegistros(TicTacToe.this);
                registers.sort(new Comparator<iRegistro>() {
                    public int compare(iRegistro r1, iRegistro r2) {
                        return r2.getPuntaje() - r1.getPuntaje();
                    }
                });
                for (int i = 0; i < 10; i++) {
                    if (i < registers.size()) {
                        iRegistro register = registers.get(i);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                        stats.append("Username: " + register.getJugador().getNombre() + "\n" +
                                "Score: " + register.getPuntaje() + "\n" +
                                "Date: " + register.getInicio().format(formatter) + "\n\n");
                    }
                }

                statsFrame.add(stats);

                JButton exitButton = new JButton("Back");
                exitButton.setPreferredSize(new Dimension(450, 50));
                exitButton.setFont(new Font("Arial", Font.BOLD, 20));
                exitButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        statsFrame.dispose();
                    }
                });
                statsFrame.add(exitButton);

                statsFrame.setVisible(true);
            }
        });
        generalStatsButton.setPreferredSize(new Dimension(300, 50));
        generalStatsButton.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.add(generalStatsButton);

        // Add blank space here 
        final JLabel blankSpace3 = new JLabel(" ");
        blankSpace3.setPreferredSize(new Dimension(450, 25));
        frame.add(blankSpace3);

        // Create a label to tell the user choose between X or O
        final JLabel choose = new JLabel("Choose X or O");
        choose.setFont(new Font("Arial", Font.PLAIN, 30));
        frame.add(choose);

        // Create a two bottons option selector for the X or O option
        final JPanel options = new JPanel();
        options.setPreferredSize(new Dimension(400, 100));
        options.setLayout(new FlowLayout());

        final JButton xButton = new JButton("X");
        xButton.setPreferredSize(new Dimension(150, 50));
        xButton.setFont(new Font("Arial", Font.BOLD, 30));
        xButton.setEnabled(false);
        options.add(xButton);

        final JButton oButton = new JButton("O");
        oButton.setPreferredSize(new Dimension(150, 50));
        oButton.setFont(new Font("Arial", Font.BOLD, 30));
        options.add(oButton);

        // Set the action listener for the X button
        xButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // If the user choose X, the turn is set to 1, and the game starts
                playerTurn = 1;
                oButton.setEnabled(true);
                xButton.setEnabled(false);
            }
        });

        // Set the action listener for the O button
        oButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // If the user choose O, the turn is set to 2, and the game starts
                playerTurn = 2;
                xButton.setEnabled(true);
                oButton.setEnabled(false);
            }
        });

        frame.add(options);

        // Add the start button 
        final JButton startButton = new JButton("Start"); 
        startButton.setPreferredSize(new Dimension(100, 50));
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton.addActionListener(new ActionListener() { 
            public void actionPerformed(final ActionEvent e) { 
                frame.dispose(); 
                game(); 
            } 
        });
        frame.add(startButton);

        // Add the exit button
        exitButton.setPreferredSize(new Dimension(100, 50));
        exitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();
            }
        });
        frame.add(exitButton);
    }

    /**
     * This method finishes the game and save the results
     *
     * @return {@link void}
     */
    public void terminarPartida() {
        register.setFinalizacion(LocalDateTime.now());
        register.setScore(playerScore);
        register.setFinished(true);

        center.addRegistro(register);
        player.registrarPuntaje(playerScore, this);

        game.dispose();
    }

    /**
     * This method returns the name of the game 
     *
     * @return {@link String} The name of the game
     */
    public String getNombre() {
        return gameTitle;
    }

    /**
     * This method returns the description of the game 
     *
     * @return {@link String} The description of the game
     */
    public String getDescripcion() {
        return gameDescription;
    }

    /**
     * Check if the player won the game.
     */
    public int checkWin() {
        // Check the rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                return board[i][0];
            }
        }
        // Check the columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                return board[0][i];
            }
        }
        // Check the diagonals
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return board[0][0];
        }
        if (board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return board[0][2];
        }
        return 0;
    }

    /**
     * This method plays the computer turn.
     * Chooses a random button and plays it.
     */
    public void computerTurn() {
        // Do this randomly 
        int x = (int) (Math.random() * 3);
        int y = (int) (Math.random() * 3);

        // If the button is not empty, try again 
        while (!buttons[x][y].getText().equals("")) {
            x = (int) (Math.random() * 3);
            y = (int) (Math.random() * 3);
        }

        // Set the button text to X if the turn is 2, and O if the turn is 1 
        if (playerTurn == 2) {
            buttons[x][y].setText("X");
            board[x][y] = 1;
        } else {
            buttons[x][y].setText("O");
            board[x][y] = 2;
        }

        buttons[x][y].setEnabled(false);
        turn++;

        // Check if there is a winner 
        int winner = checkWin();
        if (winner == playerTurn) {
            JOptionPane.showMessageDialog(game, "You won!");
            terminarPartida();
            return;
        } else if (winner != 0 && winner != playerTurn) {
            JOptionPane.showMessageDialog(game, "You lost!");
            terminarPartida();
            return;
        } else if (turn == 9) {
            JOptionPane.showMessageDialog(game, "It's a tie!");
            terminarPartida();
            return;
        }
    }

    /**
     * This method is called when a button is pressed.
     *
     * @param e The event that triggered the method.
     */
    public void actionPerformed(final ActionEvent e) {
        final String actionCommand = e.getActionCommand();

        // Exit the game 
        if (actionCommand.equals("Exit")) {
            register.setFinalizacion(LocalDateTime.now());
            game.dispose();
            return;
        }

        // Check if the button pressed is a button from the board 
        if (actionCommand.equals("")) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (e.getSource() == buttons[i][j]) {
                        // If the button is empty, set the button to the player's symbol 
                        if (buttons[i][j].getText().equals("")) {
                            if (playerTurn == 1) {
                                buttons[i][j].setText("X");
                                buttons[i][j].setEnabled(false);
                                board[i][j] = 1;
                            } else {
                                buttons[i][j].setText("O");
                                buttons[i][j].setEnabled(false);
                                board[i][j] = 2;
                            }
                            turn++;
                        }
                    }
                }
            }
        }

        // Check if there is a winner 
        int winner = checkWin();
        if (winner == playerTurn) {
            JOptionPane.showMessageDialog(game, "You won!");
            playerScore = 25;
            playerScore -= turn;
            terminarPartida();
            return;
        } else if (winner != 0 && winner != playerTurn) {
            JOptionPane.showMessageDialog(game, "You lost!");
            playerScore = 0;
            terminarPartida();
            return;
        } else if (turn == 9) {
            JOptionPane.showMessageDialog(game, "It's a tie!");
            playerScore = 7;
            terminarPartida();
            return;
        }

        // Play the computer turn
        computerTurn();
    }
}
