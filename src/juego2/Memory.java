package juego2;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.HashMap;
import java.util.List;
import java.util.Comparator;

import interfaces.iJuego;
import interfaces.iCentroJuego;
import interfaces.iJugador;
import interfaces.iRegistro;

import register.Register;

/**
 * Class Memory
 * This class acts as a middleman between the game and the game center.
 * Provides the game with the necessary information to run.
 * It also provides the necessary information to the game center to save the game.
 *
 * @author Karina
 */
public final class Memory implements iJuego {
    private String gameTitle = "Memory Game";
    private String gameDescription = "<html>The objective of this game is to memorize the location of identical cards to form a pair<br> <html>"+
                                     "<html>and add 20 pts.If you turn over different cards, 10 points are added. The game ends when <br> <html>"+
                                     "<html>all pairs are found and the player with a score equal to 160 or the closest to this record wins.<html>"; // TODO: Add description
    private final JButton exitButton = new JButton("Exit");
    private jMemory instance;
    private iJugador player;
    private Register register;
    private iCentroJuego center;

    /**
     * Inicializa un juego asociado a un jugador y centro o controlador de juegos.
     *
     * @param jugador jugador quien inicalizar
     * @param centroJuegos
     */
    public void iniciarPartida(iJugador jugador, iCentroJuego centroJuegos) {
        this.player = jugador;
        this.center = centroJuegos;
        this.register = new Register(jugador);
        this.register.setGame(this);

        // Create a new frame showing the game title and description, an Start button and an Exit button 
        final JFrame frame = new JFrame("Memory Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);
        frame.setSize(500, 400);
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

                JFrame statsFrame = new JFrame("Personal Stats");
                statsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                statsFrame.setLayout(new FlowLayout());
                statsFrame.setResizable(false);
                statsFrame.setSize(500, 650);

                // Add a text area to show the stats 
                JTextArea stats = new JTextArea();
                stats.setPreferredSize(new Dimension(450, 450));
                stats.setEditable(false);

                HashMap<LocalDate, Integer> playedDays = new HashMap<LocalDate, Integer>();
                int totalGames = 0;
                int totalHours = 0;

                for (iRegistro register: centroJuegos.getRegistros(Memory.this)) {
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
                List<iRegistro> registers = centroJuegos.getRegistros(Memory.this);
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

        // Add the start button 
        final JButton startButton = new JButton("Start"); 
        startButton.setPreferredSize(new Dimension(100, 50));
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton.addActionListener(new ActionListener() { 
            public void actionPerformed(final ActionEvent e) { 
                register.setInicio(LocalDateTime.now());
                instance = jMemory.getInstance(jugador, Memory.this); // NOTE: The game uses the singleton pattern
                instance.reset();
                frame.dispose();
                instance.setVisible(true);
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
     * Cierra la partida en juego sin registrar puntaje para el usuario pero si almacena 
     * el registro de tiempo desde el inicio y hasta su finalización
     */
    public void terminarPartida() {
        register.setFinalizacion(LocalDateTime.now());

        instance.dispose();

        if (instance.getIsFinished()) {
            int score = instance.getScore();
            register.setFinished(true);
            register.setScore(score);
            player.registrarPuntaje(score, this);
        } else {
            register.setFinished(false);
            register.setScore(0);
            player.registrarPuntaje(0, this);
        }

        center.addRegistro(register);
    }
    
    /**
     * Obtiene el nombre del juego
     * @return Nombre del juego
     */
    public String getNombre() {
        return gameTitle;
    }

    /**
     * Obtiene la descripción del juego
     * @return Descripción del juego
     */
    public String getDescripcion() {
        return gameDescription;
    }
}
