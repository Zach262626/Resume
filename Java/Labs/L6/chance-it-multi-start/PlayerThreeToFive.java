import java.util.Random;

/**
 * PlayerThreeToFive models a player with the Chance It game strategy of
 * Randomly throwing the dice three to five times.
 *
 * @author Zachary Gallant
 * @version Lab 6 
 */
public class PlayerThreeToFive extends Player
{
    private int roll;
    /**
     * Constructor for objects of class PlayerThreeToFive
     *  
     * @param dice The pair of dice shared by all players.
     * @param name The player's name.
     */
    public PlayerThreeToFive(Dice dice,String name)
    {
        // initialise instance variables
        super(dice, name);
    }
    /**
     * Creates and Random int from three to five
     * 
     * @return a integer from three to five
     */
    private int RandomInt() {
        Random rand = new Random(); 
        return rand.nextInt(3) + 3;
    }

    /**
     * Encapsulates the strategy used by this player to determine when to 
     * stop rolling the dice during each turn.
     * 
     * All concrete subclasses must provide a strategy for the player
     * 
     * @return true when the player decides to end their current turn;
     *         otherwise returns false.
     */
    protected boolean stopRolling() {
        if (numRolls == 1) {
            roll = RandomInt();
        }
        return numRolls == roll;
    }
}
