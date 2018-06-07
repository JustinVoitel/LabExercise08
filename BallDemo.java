import java.awt.Color;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class BallDemo   
{
    public static final boolean LEAVE_TRACES_ON_GROUND = false;
    public static final boolean LEAVE_TRACE = false;
    private Canvas myCanvas;
    private int ground = 400;   // position of the ground line

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo(int width,int height)
    {
        myCanvas = new Canvas("Ball Demo", width, height,LEAVE_TRACE);
    }

    public void drawGround(){
        myCanvas.setForegroundColor(Color.BLACK);
        if (LEAVE_TRACES_ON_GROUND)
            myCanvas.drawLine(50, ground, 550, ground);
        else
            myCanvas.drawLine(50, ground+1, 550, ground+1);
    }
    
    public void drawFrame(){
        
    }
    
    // public void fillRectangle(int xPos, int yPos, int width, int height)
    //public void drawLine(int x1, int y1, int x2, int y2)
    public void drawStuff(){
        myCanvas.fillRectangle(100,50, 100, 100);
        myCanvas.drawLine(10,10,100,100);
    }
    
    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        myCanvas.setVisible(true); //setVisible

        drawGround();
        
        // create and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();
        BouncingBall ball3 = new BouncingBall(70, 80, 100, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            ball3.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550 || ball3.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
}
