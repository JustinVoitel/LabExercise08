import java.awt.Color;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;



/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Daan Lockhorst, Justin Voitel and Ella katajisto
 * @version 05.07.2018
 */

public class BallDemo   
{
    public static final boolean LEAVE_TRACES_ON_GROUND = false;
    public static final boolean LEAVE_TRACE = false;
    // position of the ground line
    public int ground = 400;
    private Canvas myCanvas;
    
    

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    { 
        myCanvas = new Canvas("Ball Demo", 600, 500,LEAVE_TRACE);
        
    }
    

    /**
     * Simulate two bouncing balls
     */
    
    public void drawGround()
    {
            myCanvas.setForegroundColor(Color.BLACK);
            if (LEAVE_TRACES_ON_GROUND)
                myCanvas.drawLine(50, ground, 550, ground);
            else
                myCanvas.drawLine(50, ground+1, 550, ground+1);
            
    }
    /**
     * Draw some modern "art" on the screen, So much emotion
     */
    public void drawStuff()
    {
     myCanvas.fillRectangle(50, 50, 200, 200);
     myCanvas.drawLine(20, 20, 550, 200);
     myCanvas.drawLine(20, 550, 550, 0);
     myCanvas.fillCircle(150, 150, 400);
    }
    
    
    /**
     * Draw a rectangle frame a nr of pixles from the edge you can also choose the thickness 
     * @param int distance for the distance to the frames edge
     * @param int thickness for the thickness of the line
     */
    public void drawFrame(int distance, int thickness)
    {
        int height = (int) myCanvas.getHeight();
        int width = (int) myCanvas.getWidth();
        
     myCanvas.fillRectangle(distance, distance,
        width - (distance * 2),
        height - (distance *2));
        
     myCanvas.eraseRectangle(distance + thickness, distance + thickness,
        width - (distance * 2 + thickness *2),
        height - (distance * 2 + thickness *2));
    }
    
    /**
     * Draw a rectangle frame a nr of pixles from the edge you can also choose the thickness 
     * @param int n for the number of BounceingBalls created
     */
    public void bounce(int n)
    {
        ArrayList<BouncingBall> ballz = new ArrayList();
        Random r = new Random();
        int height = (int) myCanvas.getHeight();
        int width = (int) myCanvas.getWidth();
     
        myCanvas.setVisible(true);
        drawGround();
        // create n number of BouncingBalls with random parameters
        for(int i = 0; i < n; i ++){
            // generates a random color for every bouncingBall
            float red = r.nextFloat();
            float green = r.nextFloat();
            float blue = r.nextFloat();
            Color randomColor = new Color(red, green, blue);
            System.out.println(width);
            ballz.add(new BouncingBall(r.nextInt(width), r.nextInt(100),
                r.nextInt(100), randomColor, ground, myCanvas));
                ballz.get(i).draw();
            }
        // make them bounce
        boolean finished =  false;
        while (!finished) {
           int lastX = 1000;
           for(int i = 0; i < n; i++)
           {
           ballz.get(i).move();
           if(ballz.get(i).getXPosition() < lastX){
               lastX = ballz.get(i).getXPosition(); 
           }  
        }
        if(lastX > width){
               finished = true;
           }
                       
        myCanvas.wait(20);
    }
            // stop once ball has travelled a certain distance on x axis
            
           
        }
    }

