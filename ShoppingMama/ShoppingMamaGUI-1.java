//package ShoppingMama
import javax.swing.JFrame;
//import javax.swing.*;

/**
 * ShoppingMamaGUI demonstrates a graphical user interface and an event listener
 * for the ShoppingMama game. 
 *
 * @author Tiasa K., Anna K., Beryce G.
 * @version CS230 Final Project; Dec 2018
 */
public class ShoppingMamaGUI
{
    /**
     * Main method: Creates and displays the main program frame
     *
     */
    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("ShoppingMamaGUI"); //title of the window
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); // by clicking the red "close window" button
        
        ShoppingMamaPanel game = new ShoppingMamaPanel(10,10, "Hard");
        //testPanel maze = new testPanel(10,10);
        
        frame.getContentPane().add(game);
        //frame.getContentPane().add(maze);
        frame.pack();
        //frame.setSize(502, 523);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}