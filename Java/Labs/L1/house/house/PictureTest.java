

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class PictureTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PictureTest
{
    /**
     * Default constructor for test class PictureTest
     */
    public PictureTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void test1()
    {
        Picture picture1 = new Picture();
        picture1.draw();
        picture1.moonPhases();
        picture1.setBlackAndWhite();
        picture1.setColor();
    }
}

