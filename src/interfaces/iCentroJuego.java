package interfaces;

import java.util.ArrayList;

public interface iCentroJuego 
{
    /**
     * Recupera el registro histórico de partidas para todos los usuarios y sobre un mismo tipo de juego.
     * @param tipoJuego tipo de juego
     * @return  listado de registros de partidas realizadas.
     */
    public ArrayList<iRegistro> getRegistros (iJuego tipoJuego);

    /**
     * Listado de juegos disponibles en el centro de juegos
     * @return listado de juegos
     */
    public ArrayList<iJuego> getJuegosDisponibles();
}
