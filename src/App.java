/**
 * Class to manage the Application and its components
 *
 * @author Johan Rodriguez
 * @version 1.0
 */
public class App {
    private static Player player1 = null;
    private static Player player2 = null;
    private static Player player3 = null;
    private static Player player4 = null;

    /**
     * Constructor, Main method to start the application
     */
    public App() {
        new Menu();
    }

    /**
     * Get the player1 Object
     *
     * @return Player
     */
    public static Player getPlayer1() {
        return player1;
    }

    /**
     * Set the player2 Object
     *
     * @param player2
     */
    public static Player getPlayer2() {
        return player2;
    }

    /**
     * Get the player3 Object
     *
     * @return player3
     */
    public static Player getPlayer3() {
        return player3;
    }

    /**
     * Get the player4 Object
     *
     * @return player4
     */
    public static Player getPlayer4() {
        return player4;
    }
}
