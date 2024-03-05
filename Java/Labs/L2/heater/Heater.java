/**
 * A Heater models a simple space-heater. The operations provided by a Heater
 * object are:
 * 1. Increase and decrease the temperature setting by a set amount.
 * 2. Return the current temperature setting.
 * 3. Change the set amount by which the temperature is increased and lowered.
 * 
 * @author L.S. Marshall, SCE, Carleton University
 * (incomplete implementation for SYSC 2004 Lab 2)
 * @author Zachary Gallant
 * @version 1.03 September 18th, 2022
 */
public class Heater
{
    /** The temperature setting that the heater should maintain. */
    private int temperature;
    
    /** The temperature setting for a newly created heater. */
    private static final int INITIAL_TEMPERATURE = 15;
    
    /** 
     * The amount by which the temperature setting is raised/lowered when
     * warmer() and cooler() are invoked.
     */
     private int increment;
    
    /** 
     * The default amount by which the temperature setting is 
     * increased when warmer() is invoked and decreased when cooler()
     * is invoked.
     */
    private static final int DEFAULT_INCREMENT = 5;
    /**This is the maximum and mininum of the heater temperature */
    private int max;
    private int min;
    /**This is the initial maximum and mininum of the heater temperature */
    private static final int MAX = 100;
    private static final int MIN = 0;
    
    /**
     * Constructs a new Heater with an initial temperature setting of 15
     * degrees, and which increments and decrements the temperature
     * setting in increments of 5 degrees.
     */
    public Heater()
    {
        temperature = INITIAL_TEMPERATURE;
        increment = DEFAULT_INCREMENT;
        max = MAX;
        min = MIN;
    }
 
    /**
     * This initializes the min and max with the parameter maxTemp and minTemp.
     */    
    public Heater(int minTemp, int maxTemp)
    {
        temperature = INITIAL_TEMPERATURE;
        increment = DEFAULT_INCREMENT;
        max = maxTemp;
        min = minTemp;
    }

    /**
     * This method returns the current temperature of the heater.
     */    
    public int temperature()
    {
        return temperature;
    }
    
    /**
     * Makes the temperature of the heater warmer by whatever the current increment is.
     */
    public void warmer()
    {
        int new_temp = temperature + increment;
        if (new_temp<=max) {
            temperature = new_temp; 
        }
    }

    /**
     * Makes the temperature of the heater colder by whatever the current increment is.
     */    
    public void cooler()
    { 
        int new_temp = temperature - increment;
        if (new_temp>=min) {
            temperature = new_temp;
        }
    }
    
    
    /**
     * This method changes the current increment for changing the weather of the heater.
     */    
    public void setIncrement(int newIncrement)
    { 
        if (newIncrement > 0) {
           increment = newIncrement; 
        }
    }
}
