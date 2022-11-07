package register;

import interfaces.iRegistro;
import interfaces.iJuego;
import interfaces.iJugador;
import player.Player;

import java.time.LocalDateTime;
import java.time.Duration;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

/**
 * Class that implements the register interface
 * 
 * @author <a href="https://github.com/Johanx22x">Johan Rodriguez</a>
 * @version 1.0
 */
public class Register implements iRegistro {
    private iJuego game;
    private LocalDateTime start;
    private LocalDateTime end;
    private iJugador player;
    private int score;
    private boolean finished = false;

    /**
     * Constructor of the class
     * 
     * @param player
     * @param score
     */
    public Register(iJugador player) {
        this.player = player;
    }

    /**
     * Method that returns the game
     * 
     * @return game
     */
    public iJuego getTipoJuego() {
        return game;
    }

    /**
     * Method that sets the score
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Method that sets the game
     * 
     * @param game
     */
    public void setGame(iJuego game) {
        this.game = game;
    }

    /**
     * Recupera la fecha/hora de inicio de la partida
     *
     * @return fecha/Hora de inicio de la partida
     */
    public LocalDateTime getInicio() {
        return this.start;
    }

    /**
     * Recupera la fecha/hora de finalización de la partida
     *
     * @return fecha/hora de finalización del juego
     */
    public LocalDateTime getFinalizacion() {
        return this.end;
    }

    /**
     * Asigna la fecha/hora de inicio de la partida
     *
     * @param fechaHora fecha/hora de inicio de la partida
     */
    public void setInicio(LocalDateTime fechaHora) {
        this.start = fechaHora;
    }

    /**
     * Asigna la fecha/hora de finalización de la partida
     *
     * @param fechaHora fecha/hora de finalización de la partida
     */
    public void setFinalizacion(LocalDateTime fechaHora) {
        this.end = fechaHora;
    }

    /**
     * Retorna el puntaje obtenido en la partida, null en caso de terminar la partida sin completar.
     *
     * @return puntaje obtenido
     */
    public int getPuntaje() {
        return this.score;
    }

    /**
     * Retorna el total de segundos transcurridos desde el inicio de la partida y la finalización
     *
     * @return  total de segundos
     */
    public int getSegundosTotalesPartida() {
        return (int) Duration.between(this.start, this.end).getSeconds();
    }

    /**
     * Retorna el estado de finalización de la partida, True si terminó con éxito la partida y registra puntuación, false si finlaiza la partida sin terminar el juego.
     *
     * @return estado de finalización de juego
     */
    public boolean getEstadoFinalizado() {
        return this.finished;
    }

    /**
     * Obtiene la instancia del jugador
     *
     * @return Jugador
     */
    public iJugador getJugador() {
        return this.player;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    /**
     * Checks if the default file exists, if not, it creates it.
     * Also, creates the default admin user.
     *
     * @return nothing
     */
    private static void checkDefaultFile() {
        File file = new File("records.txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Write the records in a file
     *
     * @param @link{ArrayList} of @link{iRegistro} - records
     * @return @link{void}
     */
    public static void writeRecords(ArrayList<iRegistro> records) {
        try {
            FileWriter file = new FileWriter("records.txt");
            BufferedWriter buffer = new BufferedWriter(file);
            for (iRegistro record : records) {
                buffer.write(record.getTipoJuego().getNombre() + "," + record.getJugador() + "," + record.getInicio() + ","
                        + record.getFinalizacion() + "," + record.getPuntaje() + "," + record.getEstadoFinalizado());
                buffer.newLine();
            }
            buffer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Read the records from a file
     *
     * @return @{@link ArrayList} of @{@link iRegistro}
     */
    public static ArrayList<iRegistro> readRecords() {
        checkDefaultFile();

        ArrayList<iRegistro> records = new ArrayList<iRegistro>();
        try {
            File file = new File("records.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] record = data.split(",");

                Register newRecord = new Register(new Player(record[1]));
                newRecord.setScore(Integer.parseInt(record[4]));
                // newRecord.setGame(new Game(record[0])); // TODO: Change this
                newRecord.setInicio(LocalDateTime.parse(record[2]));
                newRecord.setFinalizacion(LocalDateTime.parse(record[3]));
                newRecord.setFinished(Boolean.parseBoolean(record[5]));

                records.add(newRecord);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return records;
    }
}
