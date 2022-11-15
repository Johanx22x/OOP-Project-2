package juego2;

import java.time.LocalTime;

import interfaces.iJuego;
import interfaces.iCentroJuego;
import interfaces.iJugador;

/**
 *
 * @author Karina
 */
public final class Memory implements iJuego {
    private String gameName = "Memory Game";
    private String gameDescription = "Memory Game Description"; // TODO: Add description
    private jMemory instance;
    private iJugador player;
    private iCentroJuego gameCenter;
    private LocalTime startTime;
    private LocalTime finishTime;

    /**
     * Inicializa un juego asociado a un jugador y centro o controlador de juegos.
     *
     * @param jugador jugador quien inicalizar
     * @param centroJuegos
     */
    public void iniciarPartida(iJugador jugador, iCentroJuego centroJuegos) {
        startTime = LocalTime.now();
        this.player = jugador;
        this.gameCenter = centroJuegos;
        instance = jMemory.getInstance(jugador, this);
        instance.reset();
        instance.setVisible(true);
    }

    /**
     * Cierra la partida en juego sin registrar puntaje para el usuario pero si almacena 
     * el registro de tiempo desde el inicio y hasta su finalización
     */
    public void terminarPartida() {
        finishTime = LocalTime.now();
        instance.dispose();
    }
    
    /**
     * Obtiene el nombre del juego
     * @return Nombre del juego
     */
    public String getNombre() {
        return gameName;
    }

    /**
     * Obtiene la descripción del juego
     * @return Descripción del juego
     */
    public String getDescripcion() {
        return gameDescription;
    }
}
