import java.util.Stack;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
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

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
    private Stack<Room> stackedRooms;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        stackedRooms = new Stack<Room>();
    }

    /**
     * Create all the rooms and item in the and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        
        //creates and add Items to room
        outside.addItem("tree", 500);
        outside.addItem("bush", 15);
        
        theatre.addItem("prop", 5);
        
        pub.addItem("beer bottle", 1);
        
        lab.addItem("computer", 10);
        lab.addItem("wooden chair", 40);
        
        office.addItem("metal desk", 60);
        office.addItem("metal chair", 50);
        
        
        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    /**
     * Looks Arround current room and prints a long description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     *     Items: 
     *         a wooden chair that weights 5.0kg
     *         a orange that weights 0.2kg
     * 
     * @param command The command to be processed
     */
    private void look(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("look what?");    
            return;            
        }
        System.out.println(currentRoom.getLongDescription());
    }
    /**
     * This makes the user eat and prints out that you are no longer hungry.
     * 
     * @param command The command to be processed
     */
    private void eat(Command command) {
       if (command.hasSecondWord()) {
           System.out.println("eat what?");    
           return;            
       } 
       System.out.println("you have eaten and are no longer hungry.");
    }
    /**
     * Goes back to the previous room.
     * 
     * @param command The command to be processed
     */
    private void back(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("back what?");    
            return;
        } 
        Room tempRoom = currentRoom;
        if (previousRoom == null) {
            System.out.println("you are at the beginning of the game, you cannot go back");  
        }else {
            currentRoom = previousRoom;
            previousRoom = tempRoom;
            System.out.print(currentRoom.getLongDescription());       
        }
    }
    /**
     * Goes back to the rooms you entered in order until your back from where
     * you started.
     * 
     * @param command The command to be processed
     */
    private void stackBack(Command command) {
        if (command.hasSecondWord()) {
                System.out.println("stackBack what?");    
                return;
            }
        if (stackedRooms.empty()) {
            System.out.println("you are at the beginning of the game, you cannot go back");       
        }else{
            currentRoom = stackedRooms.pop();
            System.out.print(currentRoom.getLongDescription());  
        }   
    }
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.print(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param command The command to be processed
     * @return true If the command ends the game, false otherwise
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            look(command);
        }
        else if (commandWord.equals("eat")) {
            eat(command);
        }
        else if (commandWord.equals("back")) {
            back(command);
        }
        else if (commandWord.equals("stackBack")) {
            stackBack(command);
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print a cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        for (String command : parser.getCommands()) {
            System.out.print(command + " ");
        }
        System.out.println();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * 
     * @param command The command to be processed
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            previousRoom = currentRoom;
            stackedRooms.push(currentRoom);
            currentRoom = nextRoom;
            System.out.print(currentRoom.getLongDescription());
        }
    }
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * 
     * @param command The command to be processed
     * @return true, if this command quits the game, false otherwise
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
