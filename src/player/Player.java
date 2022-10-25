package player;

/**
 * Class Player - a player in a game. 
 *
 * @author Johan Rodriguez
 * @version 1.0
 */
public class Player {
    private String name;
    private int score;

    /**
     * Constructor for objects of class Player
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    /**
     * Get the name of the player
     *
     * @return The name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the Player
     *
     * @param name The name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the score of the player
     *
     * @return The score of the player
     */
    public int getScore() {
        return score;
    }

    /**
     * Set points to the score of the player
     *
     * @param points The points to set in the score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Add points to the score of the player
     *
     * @param points The points to add to the score
     */
    public void addScore(int score) {
        this.score += score;
    }
}
