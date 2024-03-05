/**
 * The test class CarTest.
 *
 * @author  Lynn Marshall, SCE
 * @version 1.2 May 1st, 2015
 * 
 * @author  Zachary Gallant
 * @version Feb 2024
 */
public class CarTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class CarTest
     */
    public CarTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }
    /**
     * Test Create a Business Car.
     * 
     * Creates a business car and verifies that the car as the right number
     * of seats and that each of those seats has the correct number and price.
     */
    public void testCreateBusinessCar()
    {
        Car aCar = new Car(1385, true);
        Seat[] seats = aCar.seats();
        
        /* Verify that the car has the right number of seats. */
        assertEquals(Car.BUSINESS_SEATS, seats.length);
        
        /* Verify that each seat has the correct number and price. */
        for (int i = 0; i < seats.length; i++) {
            assertEquals(i+1, seats[i].number());
            assertEquals(Car.BUSINESS_SEAT_COST, seats[i].price());
        }
    }
    /**
     * Test Create a Economy Car.
     * 
     * Creates a Economy car and verifies that the car as the right number
     * of seats and that each of those seats has the correct number and price.
     */
    public void testCreateEconomyCar()
    {
        Car aCar = new Car(1400, false);
        Seat[] seats = aCar.seats();
        
        /* Verify that the car has the right number of seats. */        
        assertEquals(Car.ECONOMY_SEATS, seats.length);
        
        /* Verify that each seat has the correct number and price. */       
        for (int i = 0; i < seats.length; i++) {
            assertEquals(i+1, seats[i].number());
            assertEquals(Car.ECONOMY_SEAT_COST, seats[i].price());
        }
    }    
    /**
     * Test Car Id.
     * 
     * Creates a Economy and Business car with id's and check if its the right ID for each.
     */
    public void testID()
    {
         Car aCar;
         aCar= new Car(1385, true);
         assertEquals(1385, aCar.id());
         aCar = new Car(1400, false);
         assertEquals(1400, aCar.id());
    }
    /**
     * Test if car is business class.
     * 
     * Creates a Economy and Business cars and checks that the economy car will return false and the 
     * business car will return true.
     */
    public void testIsBusinessClass()
    {
         Car aCar;
         aCar = new Car(1385, true);
         assertTrue(aCar.isBusinessClass());
         aCar = new Car(1400, false);
         assertFalse(aCar.isBusinessClass()); 
    }
    /**
     * Test booking next seat.
     * 
     * Creates a Economy and Business cars and checks that book next seat will book the next seat available.
     */
    public void testBookNextSeat()
    {
        Car aCar;
        Car bCar;
        aCar = new Car(1234, true);
        bCar = new Car(5678, false);
        
        Seat[] seats1 = aCar.seats();
        Seat[] seats2 = bCar.seats();
        
        /* Verify that no seats are booked. */
        for (int i = 0; i < seats1.length; i++) {
            assertFalse(seats1[i].isBooked());
        }    
        for (int i = 0; i < seats2.length; i++) {
            assertFalse(seats2[i].isBooked());
        }        
        
        /* Verify that the seats are booked consecutively,
         * starting with Seat #1.
         */
        for (int i = 0; i < seats1.length; i++) {
            seats1 = aCar.seats();
            assertFalse(seats1[i].isBooked()); // not booked
            assertTrue(aCar.bookNextSeat()); // book it
            assertTrue(seats1[i].isBooked()); // now booked
            if (i!=seats1.length-1) {
                assertFalse(seats1[i+1].isBooked()); // but next isn't
            }
        }
        for (int i = 0; i < seats2.length; i++) {
            seats1 = bCar.seats();
            assertFalse(seats2[i].isBooked()); // not booked
            assertTrue(bCar.bookNextSeat()); // book it
            assertTrue(seats2[i].isBooked()); // now booked
            if (i!=seats2.length-1) {
                assertFalse(seats2[i+1].isBooked()); // but next isn't
            }
        }
        
        /* Try to book a seat now that all the seats have been booked. */
        assertFalse(aCar.bookNextSeat());
        assertFalse(bCar.bookNextSeat());
    }
    /**
     * Test cancel seat.
     * 
     * Creates a Economy and Business cars and checks if all seat can be canceled.
     */
    public void testCancelSeat()
    {
        Car aCar;
        Car bCar;
        aCar = new Car(1234, true);
        bCar = new Car(5678, false);
        
        Seat[] seats1 = aCar.seats();
        Seat[] seats2 = bCar.seats();
        
        /* Cancel seat 0. cancelSeat() should return false, as there
         * is no seat 0.
         */
        assertFalse(aCar.cancelSeat(0));
        assertFalse(bCar.cancelSeat(0));
        
        /* Try cancelling a seat whose number is one higher than 
         * the highest valid seat number (seats.length - 1). 
         * cancelSeat() should return false.
         */
        assertFalse(aCar.cancelSeat(seats1.length));
        assertFalse(bCar.cancelSeat(seats2.length));
        /* Try cancelling seat 50 whose number higher than 
         * the highest valid seat number. 
         * cancelSeat() should return false.
         */
        assertFalse(aCar.cancelSeat(50));
        assertFalse(bCar.cancelSeat(50));
        /* Try cancelling all the seats in the car, even though 
         * they haven't been booked. cancelSeat() should 
         * return false.
         */
        for (int i = 0; i < seats1.length; i++) {
            assertFalse(aCar.cancelSeat(i+1));
        }
        for (int i = 0; i < seats2.length; i++) {
            assertFalse(bCar.cancelSeat(i+1));
        }
        
        /* Book all the seats */
        for (int i = 0; i < seats1.length; i++) {
            aCar.bookNextSeat();
        }  
        for (int i = 0; i < seats2.length; i++) {
            bCar.bookNextSeat();
        }  
        
        /* Try cancelling all the seats in the car.
         */
        for (int i = 0; i < seats1.length; i++) {
            assertTrue(aCar.cancelSeat(i+1));
        } 
        for (int i = 0; i < seats2.length; i++) {
            assertTrue(bCar.cancelSeat(i+1));
        } 
        
        /* In case seat numbers are off, try some more tests.
         */
        Car cCar;
        cCar = new Car (4321,false);
        // book 2 seats
        assertTrue(cCar.bookNextSeat());
        assertTrue(cCar.bookNextSeat());
        // try to cancel the 3rd (not booked)
        assertFalse(cCar.cancelSeat(3));
        // cancel the 1st and 2nd (were both booked)
        assertTrue(cCar.cancelSeat(1));
        assertTrue(cCar.cancelSeat(2));
       
    }      
}
