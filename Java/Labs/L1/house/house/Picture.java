/**
 * This class represents a simple picture. You can draw the picture using
 * the draw method. But wait, there's more: being an electronic picture, it
 * can be changed. You can set it to black-and-white display and back to
 * colors (only after it's been drawn, of course).
 *
 * This class was written as an early example for teaching Java with BlueJ.
 * 
 * @author  Michael Kšlling and David J. Barnes
 * @version 2016.02.29
 */
public class Picture
{
    private Square wall;
    private Square background;
    private Square window;
    private Triangle roof;
    private Circle moon;
    private Circle moonCover;
    private boolean drawn;

    /**
     * Constructor for objects of class Picture
     */
    public Picture()
    {
        wall = new Square();
        background = new Square();
        window = new Square();
        roof = new Triangle();  
        moon = new Circle();
        drawn = false;
    }

    /**
     * Draw this picture.
     */
    public void draw()
    {
        if(!drawn) {
            background.changeColor("black");
            background.moveVertical(-200);
            background.moveHorizontal(-400);
            background.changeSize(1000);
            background.makeVisible();
            
            wall.moveHorizontal(-140);
            wall.moveVertical(10);
            wall.changeSize(120);
            wall.makeVisible();
            
            window.changeColor("black");
            window.moveHorizontal(-120);
            window.moveVertical(40);
            window.changeSize(40);
            window.makeVisible();
    
            roof.changeSize(60, 180);
            roof.moveHorizontal(20);
            roof.moveVertical(-60);
            roof.makeVisible();
    
            moon.changeColor("blue");
            moon.moveHorizontal(130);
            moon.moveVertical(-40);
            moon.changeSize(80);
            moon.makeVisible();
            
            drawn = true;
        }
    }
    
    /**
     * this displays the moon phases
     */
    public void moonPhases()
    {
        moonCover = new Circle();
        moonCover.changeColor("black");
        moonCover.moveHorizontal(50);
        moonCover.moveVertical(-40);
        moonCover.changeSize(80);
        moonCover.makeVisible();
        moonCover.slowMoveHorizontal(160);
    }

    /**
     * Change this picture to black/white display
     */
    public void setBlackAndWhite()
    {
        wall.changeColor("white");
        window.changeColor("black");
        roof.changeColor("white");
        moon.changeColor("white");
    }

    /**
     * Change this picture to use color display
     */
    public void setColor()
    {
        wall.changeColor("red");
        window.changeColor("black");
        roof.changeColor("green");
        moon.changeColor("blue");
    }
}
