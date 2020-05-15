import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/**
 * GameOverPanel returns a JPanel with the label "Game Over". This panel
 * will be used in the creation of the results panel.
 *
 * @author Beryce Garcia
 * @version December 21, 2018
 */
public class GameOverPanel
{
    private JLabel game_over;
    private JPanel top;
    public GameOverPanel() {
        // creating the top panel which says game over
        // this is the text that says game over
        game_over = new JLabel ("Game Over");
        
        // this is the panel in which the text will be added.
        // this panel will be at the top of the results panel.
        top = new JPanel();
    }
    
    /**
     * Helper method that completes the creation of the JPanel
     */
    private void createJPanel() {
        // set the font for the text to be displayed
        game_over.setFont(new Font("SansSerif", Font.PLAIN, 48));
        
        // top is where the game_over JLabel will be added to
        top.setLayout(new FlowLayout());
        top.setBackground(Color.pink);
        top.add(game_over);
    }
    
    /**
     * Getter method that returns the JPanel
     */
    public JPanel getJPanel() {
        createJPanel();
        return top;
    }
}
