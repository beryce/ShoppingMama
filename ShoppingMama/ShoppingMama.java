import java.util.*;
import java.io.*;
/**
 * Description: ShoppingMama acts as a driver for the game.
 * 
 * 230 member contributions:
 * Beryce - created entire class
 *
 * @author Beryce Garcia
 * @version December 17, 2018
 */

public class ShoppingMama
{
    private String difficulty;
    private ShoppingMamaPanel panel;
    private int width;
    private int height;
    /**
     * Constructor for the ShoppingMama class. This method creates and initializes a 
     * new Shopping Mama game.
     * @param String level
     * @return N/A
     */
    public ShoppingMama(String level)
    {
       difficulty = level;
       this.setDimensions();
    }
    
    /**
     * Getter method that returns the difficulty level set by the user.
     * @param N/A
     * @return String difficulty
     */
    public String getDifficulty() {
        return difficulty;
    }
    
    /**
     * Setter method that sets the difficulty according to the level
     * passed into the parameter.
     * @param String level
     * @return N/A
     */
    public void setDifficulty(String level) {
        difficulty = level;
        this.setDimensions();
    }
    
    /**
     * Helper method that sets the dimensions of the main game panel according to the level
     * chosen by the user.
     * @param N/A
     * @return N/A
     */
    private void setDimensions() {
         if (difficulty.equals("Easy")) {
            width = 10;
            height = 10;
        } else if (difficulty.equals("Medium")) {
            width = 10;
            height = 10;
        } else if (difficulty.equals("Hard")) {
            width = 11;
            height = 11;
        } else {
            width = 11;
            height = 11;
        }
    }
    
    /**
     * Getter method that returns the width.
     * @param N/A
     * @return int width
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Getter method that returns the height.
     * @param N/A
     * @return int height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Used for testing.
     * @param String[] args
     * @return N/A
     */
    public static void main(String[] args) {
        ShoppingMama test = new ShoppingMama("Easy");
        System.out.println(test.getDifficulty());
    }
}
