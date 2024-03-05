/**
 * 
 *2.  number() returns 5, price() returns 25.0 and isBooked() return false
 *
 *3a. the method book() changes the value of variable book meaning 
 *    the seat is booked and the method isBooked return the variable book.
 *    
 *3b. book() returns false since this seat is already booked and isBooked 
 *    is still true since the seat is booked
 *    
 *4a. the method cancelBooking() cancelled the booking so now isBooked() will 
 *    return false
 *    
 *4b. since there is no booking on this seat cancelBooking() will not change 
 *    the value and isBooked will still return false
 *    
 *    
 *car in a train
 *    
*/
public class Car
{
    /** This car's identifier. */
    private int id;
   
    /**
     * true == this car is a business-class car,
     * false == this car is an economy-class car.
     */
    private boolean businessClass;
    
    /** The cost of a business class seat, in dollars. */
    public static final double BUSINESS_SEAT_COST = 125.0;
    
    /** The cost of an economy class seat, in dollars. */
    public static final double ECONOMY_SEAT_COST = 50.0;    
  
    /** The number of seats in a business class car. */
    public static final int BUSINESS_SEATS = 30;   
    
    /** The number of seats in an economy class car. */
    public static final int ECONOMY_SEATS = 40;   
   
    /** The list of this car's seats. */
    private Seat[] seats;
   
    /**
     * Constructs a new Car object with the specified id.
     * If parameter isBusinessClass is true, the car is a business-class
     * car. If parameter isBusinessClass is false, the car is an
     * economy-class car.
     * 
     * @param carID ID of the car.
     * @param isBusinessClass if the car is Buisiness class or Economy.
     */
    public Car(int carId, boolean isBusinessClass)
    {
        id = carId;
        if (isBusinessClass == true) {
            businessClass = true;
            seats = new Seat[BUSINESS_SEATS];
            for (int i=0; i<BUSINESS_SEATS; ++i) {
                seats[i] = new Seat(i+1,BUSINESS_SEAT_COST);
            }
        }else {
            businessClass = false;
            seats = new Seat[ECONOMY_SEATS];
            for (int i=0; i < ECONOMY_SEATS; ++i) {
                seats[i] = new Seat(i+1,ECONOMY_SEAT_COST);
            }
        }
    }

    /**
     * Returns this car's list of seats. This method is intended for 
     * testing purposes only, and should not be called by other objects,
     * as it may be removed from the final version of this class.
     * 
     * @return The seats in this car, an array of Seat objects.
     */
    public Seat[] seats()
    {
        return seats;
    }
 
    /** 
     * Returns true if this is a business-class car,
     * false if this is an economy-class car.
     */
    public boolean isBusinessClass()
    {
        if (businessClass == true) {
            return true;
        }
        return false;
    }
 
    /**
     * Returns the id of this car.
     */
    public int id()
    {
        return id;
    }
  
    /**
     * This method is called when the specified seat has been booked,
     * to print a ticket for that seat.
     * 
     * @param seatNo The integer identifier of the seat.
     */
    private void printTicket(int seatNo)
    {
        System.out.println("Car ID: " + id);
        System.out.println("Seat number: " + seatNo);
        System.out.println("Price: ");
        if (businessClass) {
            System.out.println(BUSINESS_SEAT_COST);
        } else {
            System.out.println(ECONOMY_SEAT_COST);
        }
    }   
 
    /**
     * Attempts to book a seat. If successful, this method prints a 
     * ticket and returns true.
     * If no seats are available, this method returns false.
     */
    public boolean bookNextSeat()
    {
        for (int i=0;i<seats.length; ++i) {
            if (seats[i].isBooked() == false) {
                printTicket(i+1);
                seats[i].book();
                return true;
            }
    
        }
        return false;
    }

    /** 
     * Cancels the booking for the specified seat, which must be between
     * 1 and the maximum number of seats in the car.
     * If the seat number is valid and if the seat has been reserved, this
     * method cancels the booking for that seat and returns true. 
     * If the seat number is not valid, this method returns false. 
     * If the seat number is valid, but the seat has not been reserved, 
     * this method returns false.
     */
    public boolean cancelSeat(int seatNo)
    {
        if (1<=seatNo &&  seatNo<=seats.length) {
            if (seats[seatNo-1].isBooked()) {
                seats[seatNo-1].cancelBooking();
                return true;
            }
        }
        return false;
    }    
}
