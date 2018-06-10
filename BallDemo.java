import java.awt.Color;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;



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
    private Canvas myCanvas;
    
    public int ground;
    public int height = 500; // (int) myCanvas.getHeight();
    public int width = 600; // (int) myCanvas.getWidth();
    
    public ArrayList<BouncingBall> ballz = new ArrayList<>();
    public Random r = new Random();
    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo(){ 
        r = new Random();
        ballz = new ArrayList<>();
        myCanvas = new Canvas("Ball Demo",this.width,this.height,LEAVE_TRACE);
    }
  
    
    public void getSize(){
        System.out.println(myCanvas.getWidth());
    }

    /**
     * Draw a ground line depending on the canvas width and height
     * height is 90% of canvas height
     */
    
    public void drawGround(){
        double groundDouble = height*0.9;
        ground = (int) groundDouble;
        myCanvas.setForegroundColor(Color.BLACK);
        if (LEAVE_TRACES_ON_GROUND)
            myCanvas.drawLine(50, ground, this.width-50, ground);
        else
            myCanvas.drawLine(50, ground+1, this.width-50, ground+1);
    }
    
    /**
     * Draw some modern "art" on the screen, So much emotion
     */
    public void drawStuff(){
        myCanvas.fillRectangle(50, 50, 200, 200);
        myCanvas.drawLine(20, 20, 550, 200);
        myCanvas.drawLine(20, 550, 550, 0);
        myCanvas.fillCircle(150, 150, 400);
    }
    
    /**
     * get the current window size and set the Canvas object to the width and height of the window
     */
    public void adaptCanvas(){
        this.width = (int)myCanvas.getWidth();
        this.height = (int)myCanvas.getHeight();
        myCanvas.setSize(this.width,this.height);
    }
    
    /**
     * Draw a rectangle frame a nr of pixles from the edge you can also choose the thickness 
     */
    public void drawFrame(){
        int thick = 20;
        adaptCanvas();
        
        //create the outer rectangle with the color red 
        myCanvas.setForegroundColor(Color.red);
        Rectangle outerFrame = new Rectangle(0, 0,width,height);
        myCanvas.fill(outerFrame);
        
        //erase the inner rectangle
        Rectangle innerFrame = new Rectangle(thick, thick,
            width - (thick *2),
            height - (thick *2));
        myCanvas.erase(innerFrame);
        
    }
    
    /**
     * Add n Balls to a ArrayList with random specifications
     * @param int distance for the distance to the frames edge
     * @param int thickness for the thickness of the line
     */
    public void createBalls(int n){        
        for(int i = 0; i < n; i ++){
            // generates a random color for every bouncingBall
            float red = r.nextFloat();
            float green = r.nextFloat();
            float blue = r.nextFloat();
            Color randomColor = new Color(red, green, blue);
            ballz.add(new BouncingBall(r.nextInt(this.width), r.nextInt(100),
                r.nextInt(100), randomColor, ground, myCanvas));
                ballz.get(i).draw();
        }
    }
    
    /**
     * Draw a rectangle frame a nr of pixles from the edge you can also choose the thickness 
     * @param int n for the number of BounceingBalls created
     */
    public void bounce(int n){
        myCanvas.setVisible(true);
        drawGround();
        createBalls(n);
        
        // make them bounce
        boolean finished =  false;
        while (!finished) {
            int lastX = 1000;
            for(int i = 0; i < n; i++){
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
        
        /*
        while (!ballz.isEmpty()) {
            myCanvas.wait(50);
            for(BouncingBall ball : ballz){
                ball.move();
                //stop if hit specific X
                if(ball.getXPosition() >=this.width - 10){
                    
                    ballz.remove(ball); //doesnt work ;(
                }
            }            
        }*/
       
        
        
        
    }
}
