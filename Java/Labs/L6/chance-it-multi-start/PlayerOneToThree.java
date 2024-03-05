import java.util.Random;
/**
 * PlayerOneToThree models a player with the Chance It game strategy of
 * Randomly throwing the dice one to three times.
 *
 * @author Zachary Gallant
 * @version Lab 6 
 */
public class PlayerOneToThree extends Player
{
    private int roll;
    /**
     * Constructor for objects of class PlayerOneToThree
     * 
     *  
     * @param dice The pair of dice shared by all players.
     * @param name The player's name.
     */
    public PlayerOneToThree(Dice dice, String name)
    {
        // initialise instance variables
        super(dice,name);
    }
    /**
     * Creates and Random int from one to three
     * 
     * @return a integer from one to three
     */
    private int RandomInt() {
        Random rand = new Random(); 
        return rand.nextInt(3)+1;
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
