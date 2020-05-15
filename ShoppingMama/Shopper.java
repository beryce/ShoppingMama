import javax.swing.*; //image icon
/**
 * Shopper represents the user of the ShoppingMama game.
 * 
 * @author Tiasa, Anna, Beryce
 * @version CS230 Final Project; Dec 2018
 */
public class Shopper {
    private int x, y; //x and y coordinates of user's current location
    private int score;
    private ImageIcon icon;
    
    /**
     * Constructs a new instance of the Shopper object
     */
    public Shopper() {
        x = y = score = 0;
        icon = new ImageIcon("shopper.png");
    }
    
    /**
     * Returns the image of the user icon
     * 
     * @return ImageIcon icon - the shopping bag user icon 
     */
    public ImageIcon getIcon() {
        return icon;
    }
    
    /**
     * Returns the user's current x-coordinate location
     * 
     * @return int x 
     */
    public int getX() {
        return x;
    }
    
    /**
     * Returns the user's current y-coordinate location
     * 
     * @return int y 
     */
    public int getY() {
        return y;
    }
    
    /**
     * Updates the user's current location on the maze.
     * Adds the number of steps moved in the horizontal and
     * vertical direction to current x and y position.  
     * 
     * @param int dx - change in x direction
     * @param int dy - change in y direction
     */
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
    
    /**
     * Returns the user's current score
     * 
     * @return int score 
     */
    public int getScore() {
        return score;
    }
    
    /**
     * Adds points to the user's current score
     * 
     * @param int pts - new points added
     */
    public void setScore(int pts) {
        score += pts;
    }
}