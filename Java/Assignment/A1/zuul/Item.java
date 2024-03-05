import java.util.ArrayList;
/**
 * This class is the item class of the "World of Zuul" application. 
 * 
 * This Creates item for the game.
 *
 * @author Zachary Gallant 101272210
 * @version Febuary 2, 2024
 */
public class Item
{
    /**This stores the description of the item*/
    private String itemDescription;
    /**This stores the weight of the item*/
    private double itemWeight;

    /**
     * Constructor for objects of class Item
     * Create a item with a described weight and description.
     * 
     * @param description The description of the item
     * @param weight the weight of the item in kg
     */
    public Item(String description, double weight)
    {
        itemDescription = description;
        itemWeight = weight;
    }
    /**
     *return a string of the item decription and weight of the item for example:
     *"a Chair that weighs 5.0kg".
     *
     *@return item of the item.
     */
    public String getItemString() {

        return "a " + itemDescription + " that weighs " + itemWeight + "kg.";
    }

}
