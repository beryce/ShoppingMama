/**
 * Description:
 *
 * @author Anna Kawakami
 * @version December 16, 2018
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.util.Random;

public class InstructionsPanel extends JPanel {

    private JPanel topPanel, middlePanel;
    private JLabel titleLabel, objective, directions;

    private ShoppingMamaPanel game;  
    public InstructionsPanel() {

        this.game = game;

        this.setLayout(new BorderLayout());

        // creating two panel components 
        topPanel = new TitlePanel(); // contains just the title
        middlePanel = new DescriptionsPanel(); // contains instructions

        // adding the two panel components onto the main panel 
        this.add(topPanel, BorderLayout.PAGE_START);
        this.add(middlePanel, BorderLayout.CENTER);
    }
    private class TitlePanel extends JPanel {
        public TitlePanel() {
            titleLabel = new JLabel("<html><h1><strong><i>How to Play</i></strong></h1><hr></html>");
            add(titleLabel);
            setBackground(Color.white);
        }
    }
    private class DescriptionsPanel extends JPanel {
        public DescriptionsPanel() {
            // create various instructions 
            objective = new JLabel("<html><br><br>Objective: Grab as many fruits as posisble" +
                "on your shopping list!<br></html>");
            String dir = ("<html><br><br>Directions:<br><br>" + 
               "1. Your actions will be represented through a small shopping " +
               "bag on the maze.<br>Use the up-down-left-right arrows on your" +
               "keyboard to move <br>this shopping bag.<br><br>" +
               "2. You will be provided with a shopping list of items to grab." +
               "Avoid grabbing items <br>that are not on your shopping list. Doing so will cost "  +
               "you one X and deduct points<br>from your final score. " + 
               "If you lose three X's, the game ends early!<br><br>" +
               "3. The shopping list will indicate the point value of each item." +
               "Consider prioritizing the items<br>with the highest point value, as indicated "+ 
                "on the shopping list. But, your time<br>is limited so choose wisely!<br><br>"+
                "4. Try to reach the door in the bottom right corner of the maze. Doing so " +
                "will bring you<br>to a new maze with a higher level. Once you go through " + 
                "all the mazes,the game will end.</html>");

            directions = new JLabel(dir);
            
            
            directions.setAlignmentX(Component.LEFT_ALIGNMENT);
            directions.setHorizontalAlignment(JLabel.CENTER);
            directions.setVerticalAlignment(JLabel.CENTER);

            // add labels to the main panel 
            add(objective);
            add(directions);
            
            //add(levels);

            // set background color 
            setBackground(Color.pink);
        } 
    } 
} 


