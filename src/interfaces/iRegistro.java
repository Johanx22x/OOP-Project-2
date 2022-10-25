package interfaces;

import java.time.LocalDateTime;

public interface iRegistro
{
    /**
     * Recupera la fecha/hora de inicio de la partida
     * @return fecha/Hora de inicio de la partida
     */
    public LocalDateTime getInicio();

    /**
     * Recupera la fecha/hora de finalización de la partida
     * @return fecha/hora de finalización del juego
     */
    public LocalDateTime getFinalizacion();

    /**
     * Asigna la fecha/hora de inicio de la partida
     * @param fechaHora fecha/hora de inicio de la partida
     */
    public void setInicio(LocalDateTime fechaHora);

    /**
     * Asigna la fecha/hora de finalización de la partida
     * @param fechaHora fecha/hora de finalización de la partida
     */
    public void setFinalizacion(LocalDateTime fechaHora);

    /**
     * Retorna el puntaje obtenido en la partida, null en caso de terminar la partida sin completar.
     * @return puntaje obtenido
     */
    public int getPuntaje();

    /**
     * Retorna el total de segundos transcurridos desde el inicio de la partida y la finalización
     * @return  total de segundos
     */
    public int getSegundosTotalesPartida();

    /**
     * Retorna el estado de finalización de la partida, True si terminó con éxito la partida y registra puntuación, false si finlaiza la partida sin terminar el juego.
     * @return estado de finalización de juego
     */
    public boolean getEstadoFinalizado();

    /**
     * Obtiene la instancia del jugador
     * @return Jugador
     */
    public iJugador getJugador();
}