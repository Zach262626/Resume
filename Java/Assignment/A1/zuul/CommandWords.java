/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * 
 * @author Lynn Marshall
 * @version October 21, 2012
 * 
 * @author Zachary Gallant 101272210
 * @version Febuary 2, 2024
 */

public class CommandWords
{
    // a array that holds all valid command words
    private static String[] validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords() {
        validCommands = new String[]{
            "go", "quit", "help", "look", "eat", "back", "stackBack"
        };

    }

    /**
     * Check whether a given String is a valid command word. 
     * 
     * @param aString The String to check
     * @return true if it is valid, false otherwise
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
             if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Gets an array of all the command words
     * 
     * @return a array of String of all the valid commands.
     */
    public String[] getCommandList() 
    {
        return validCommands;
    }
}
