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
     * 
     * @param username Username of the user
     */
    public GameCenter(String username) {
        new Menu(username);
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
