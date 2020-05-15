import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/**
 * Returns an empty panel to create white space within the Results Panel
 *
 * @author Beryce Garcia
 * @version December 21, 2018
 */
public class WhiteSpacePanel
{
    private JPanel whiteSpace;
    public WhiteSpacePanel() {
        // this is the panel in which the text will be added.
        // this panel will be at the top of the results panel.
        whiteSpace = new JPanel();
    }
    
    /**
     * Helper method that completes the creation of the JPanel
     */
    private void createJPanel() {
        whiteSpace.setBackground(Color.white);
    }
    
    /**
     * Getter method that returns the JPanel
     */
    public JPanel getJPanel() {
        createJPanel();
        return whiteSpace;
    }
}
