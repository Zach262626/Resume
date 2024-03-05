import java.util.ArrayList;
/** 
 * Train Model Class.
 * 
 * Zachary Gallant 101272210
 * @author D.L. Bailey, SCE
 * @version 1.00 January 28, 2007
 */
public class Train
{
    /** The cars in this train. */
    private ArrayList<Car> cars;
   
    /** 
     * Constructs an empty train; i.e., one that has no cars.
     * 
     */
    public Train()
    {
        cars = new ArrayList<Car>();    
    }
   
    /**
     * Adds the specified car to this train.
     */
    public void addCar(Car car)
    {
        cars.add(car);    
    }
    
    /**
     * Returns this trains's list of cars. This method is intended for 
     * testing purposes only, and should not be called by other objects,
     * as it may be removed from the final version of this class.
     * 
     * @return A list of the cars in the train
     */
    public ArrayList<Car> cars()
    {
        return cars;
    }    
      
    /**
     * Attempts to issue a ticket for a business class seat or an
     * economy class seat, as specified by the method's argument.
     * It will attempt to book a seat in the first car of the appropriate
     * type, but if a seat is not available it will attempt to book a seat
     * in the second car of the appropriate type, and so on. 
     * A request to issue a ticket in a business-class car will never result
     * in a seat being reserved in an economy-class car, and vice-versa. 
     * Returns true if successful, false otherwise.
     */
    public boolean issueTicket(boolean businessClassSeat)
    {
        for (int i = 0; i < cars.size(); ++i) {
            if (cars.get(i).isBusinessClass() == businessClassSeat){
                if (cars.get(i).bookNextSeat()) {
                    return true;
                }
            }
        }
        return false;
    }
   
    /**
     * Cancels the ticket for the specified seat in the specified car.
     * Returns true if successful, false otherwise.
     */
    public boolean cancelTicket(int carId, int seatNo)
    {
        for (int i = 0; i < cars.size(); ++i) {
            if (cars.get(i).id() == carId) {
                if (cars.get(i).cancelSeat(seatNo)) {
                    return true;
                }
            }
        }
        return false;
    }
}
