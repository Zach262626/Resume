import java.util.ArrayList;

/**
 * The test class TrainTest.
 *
 * @author  Lynn Marshall
 * @version May 2015
 * 
 * @author  Zachary Gallant
 * @version Feb 2024
 */
public class TrainTest extends junit.framework.TestCase
{
    private Train aTrain;
    private Car car1;
    private Car car2;
    private Car car3;
    private Car car4;
    /**
     * Default constructor for test class TrainTest
     */
    public TrainTest()
    {
        aTrain = new Train();
        
        car1 = new Car(1250, true);
        aTrain.addCar(car1);
        
        car2 = new Car(1300, false);
        aTrain.addCar(car2);
        
        car3 = new Car(1740, false);
        aTrain.addCar(car3);
        
        car4 = new Car(2001, true);
        aTrain.addCar(car4);
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
     * Test empty train.
     *
     * Checks if you can create an empty train.
     */
    public void testCreateEmptyTrain()
    {
        Train emptyTrain = new Train();
        
        /* Verify that a new train has no cars. */
        assertEquals(0, emptyTrain.cars().size());
    }
    /**
     * Test add car.
     *
     * Checks if the car added to the train we added in order.
     */
    public void testAddCar()
    {
        ArrayList<Car> cars = aTrain.cars();
        assertEquals(4, cars.size());
        
        
        /* Verify that each car added to the train was placed at
         * the end of the list.
         */
        
        /* Important - assertSame() does not compare the Car objects 
         * referred to by car1 and cars.get() to detemine if they are equal
         * (have the same state). It verifies that car1 and cars.get(0) refer to
         * the same object; i.e., that the Car (reference) retrieved by get(0)
         * is the first first that was added to the ArrayList.
         */
        assertSame(car1, cars.get(0));
        assertSame(car2, cars.get(1));
        assertSame(car3, cars.get(2));     
        assertSame(car4, cars.get(3)); 
    }
    /**
     * Test Issue Ticket.
     *
     * Books tickets in the cars of the train to check if you can issue a ticket.
     */        
    public void testIssueTicket()
    {
        /* Book all the seats in the business-class car. */
        for (int i = 0; i < (Car.BUSINESS_SEATS * 2); i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        /* Attempt to book one more business-class seat, even
         * though they are all booked.
         */
        assertFalse(aTrain.issueTicket(true));        
 
        ArrayList<Car> cars = aTrain.cars();
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertFalse(cars.get(1).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertFalse(cars.get(2).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(3).seats()[i].isBooked());
        }        
        /* Book all the seats in the first economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }
        
        cars = aTrain.cars();
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertTrue(cars.get(1).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertFalse(cars.get(2).seats()[i].isBooked());
        } 
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(3).seats()[i].isBooked());
        }  
        
        /* Book all the seats in the second economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }
        
        /* check that all seats are now booked */
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertTrue(cars.get(1).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertTrue(cars.get(2).seats()[i].isBooked());
        }  
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(3).seats()[i].isBooked());
        }  
        
        /* Try to book another business class seat (fails)*/
        assertFalse(aTrain.issueTicket(true));
        /* Try to book another economy class seat (fails)*/
        assertFalse(aTrain.issueTicket(false));
    }
    /**
     * Test cancel ticket.
     *
     * Books tickets in the cars of the train and checks if you can cancel those ticket by index.
     */     
    public void testCancelTicket()
    {
        /* Book all the seats in the business-class car + half in the second business car. */
        for (int i = 0; i <Car.BUSINESS_SEATS; i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        for (int i = 0; i < (Car.BUSINESS_SEATS / 2); i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        /* Book all the seats in the first economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }
        
        ArrayList<Car> cars = aTrain.cars();
        
        assertTrue(aTrain.cancelTicket(1300, 4));
        assertFalse(cars.get(1).seats()[3].isBooked());        
        
        /* Cancel ticket in a non-existent car. */
        assertFalse(aTrain.cancelTicket(1500, 7));
        
        /* Cancel ticket in a non-existent seat. */
        assertFalse(aTrain.cancelTicket(1300, 54));
        
        /* Cancel ticket for a seat that hasn't been booked. */
        assertFalse(aTrain.cancelTicket(1740, 21));
        assertFalse(cars.get(2).seats()[20].isBooked());     
        
        /* Cancel a booked seat in the fourth car (business). */
        assertTrue(cars.get(3).seats()[8].isBooked()); 
        assertTrue(aTrain.cancelTicket(2001, 9));
        assertFalse(cars.get(3).seats()[8].isBooked());  
        
        /* Cancel a unbooked seat in the fourth car (business). */
        assertFalse(cars.get(3).seats()[25].isBooked()); 
        assertFalse(aTrain.cancelTicket(2001, 26));
        assertFalse(cars.get(3).seats()[25].isBooked()); 
        
        /* Attempt to cancel the same ticket twice. */
        assertTrue(aTrain.cancelTicket(1250, 11));
        assertFalse(cars.get(0).seats()[10].isBooked());
        
        assertFalse(aTrain.cancelTicket(1250, 11));   
        assertFalse(cars.get(0).seats()[10].isBooked());         
    }
        /**
     * Test cancel ticket.
     *
     * Books tickets in the cars of the train and checks if you can cancel those ticket by index. It also checks if you
     * can book those canceled seats.
     */     
        public void testBookCancelTicket()
    {
        /* Book all the seats in the business-class car. */
        for (int i = 0; i <Car.BUSINESS_SEATS; i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        for (int i = 0; i < (Car.BUSINESS_SEATS / 2); i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        /* Book all the seats in the first economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }
        
        /* Cancel 3 ticket in economy-class car. */
        assertTrue(aTrain.cancelTicket(1300, 3));
        assertTrue(aTrain.cancelTicket(1300, 11));
        assertTrue(aTrain.cancelTicket(1300, 9));
        
        /* book 4 ticket in economy-class car (include 3 canceled ticket from before). */
        for (int i = 0; i < 4; i++) {
            assertTrue(aTrain.issueTicket(false));
        }
        
        /* Cancel 3 ticket in business-class car. */
        assertTrue(aTrain.cancelTicket(1250, 22));
        assertTrue(aTrain.cancelTicket(2001, 3));
        assertTrue(aTrain.cancelTicket(2001, 6));
        
        /* book 4 ticket in Business-class car (include 3 canceled ticket from before). */
        for (int i = 0; i < 4; i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        
        
        ArrayList<Car> cars = aTrain.cars();
        /*Book all canceled seats. */
        assertTrue(cars.get(1).seats()[2].isBooked());
        assertTrue(cars.get(1).seats()[10].isBooked());
        assertTrue(cars.get(1).seats()[8].isBooked());
        assertTrue(cars.get(2).seats()[0].isBooked());
        
        assertTrue(cars.get(0).seats()[21].isBooked());
        assertTrue(cars.get(3).seats()[2].isBooked());
        assertTrue(cars.get(3).seats()[5].isBooked());
        assertTrue(cars.get(3).seats()[15].isBooked());
    }
}
