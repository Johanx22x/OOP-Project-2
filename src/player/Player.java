package player;

import interfaces.iJugador;
import interfaces.iRegistro;
import interfaces.iJuego;

import java.util.ArrayList;

/**
 * Class Player - a player in a game. 
 *
 * @author Johan Rodriguez
 * @version 1.0
 */
public class Player implements iJugador {
    private String username;
    private ArrayList<iRegistro> records;

    /**
     * Constructor for objects of class Player
     */
    public Player(String username) {
        this.username = username;
    }

    /**
     * Get the username of the player
     *
     * @return The username of the player
     */
    public String getNombre() {
        return username;
    }

    /**
     * Registra el puntaje obtenido para una determinada partida
     *
     * @param puntuacion puntuación obtenida
     * @param juego instancia de juego de la partida
     */
    public void registrarPuntaje(int puntuacion, iJuego juego) {
        // TODO:
    }

    /**
     * Listado de registros de juego del usuario para un mismo tipo de juego.
     *
     * @param tipoJuego tipo del juego
     * @return  Registro histórico de partidas.
     */
    public ArrayList<iRegistro> estadisticas(iJuego tipoJuego) {
        // TODO:
        return null;
    }
}
