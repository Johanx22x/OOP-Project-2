package frame;

import java.util.ArrayList;

import interfaces.iJuego;
import interfaces.iRegistro;
import frame.Menu;

/**
 * Class that represents the Game Center
 *
 * @author Johan Rodriguez
 * @version 1.0
 */
public class GameCenter implements interfaces.iCentroJuego {

    /**
     * Game center constructor
     */
    public GameCenter() {
        new Menu();
    }

    /**
     * Method that return the register of the game center 
     *
     * @return ArrayList<iRegistro> register of the game center
     */
    public ArrayList<iRegistro> getRegistros(iJuego tipoJuego) {
        return null;
    }

    /**
     * Method that returns the list of games
     *
     * @return ArrayList<iJuego> List of games
     */
    public ArrayList<iJuego> getJuegosDisponibles() {
        return null;
    }
}
