import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.util.Random;
/**
 * Description: The home panel for the game that displays the start and instructions buttons.
 * 
 *
 * @author Anna Kawakami, Beryce Garcia
 * @version 
 */

public class HomePanel extends JPanel {
    private JPanel topPanel, middlePanel, leftPanel, rightPanel, bottomPanel;
    private ImageIcon titleImage, watermelon, apple, orange, bag; 
    private JButton startButton, instrButton;
    private JLabel titleLabel, watermelonLabel, 
    appleLabel, orangeLabel, bagLabel;
    private JRadioButton easyButton, medButton, hardButton, extremeButton;

    private ShoppingMama game;  
    private InstructionsPanel instr;

    private String level;
    
    // used as the container for cards in card layout 
    private JPanel cards; 
    public HomePanel(InstructionsPanel instr) {
        this.instr = instr; 

        this.setLayout(new BorderLayout());
       
        // creating the five panel components 
        topPanel = new TitlePanel(); // contains just the title
        middlePanel = new StartPanel(); // contains JRadioButtons and start button
        bottomPanel = new HowToPanel(); // contains instructions button
        rightPanel = new RightPanel(); // contains images of fruit
        leftPanel = new LeftPanel(); // contains images of fruit 

        // adding the five panel components onto the main panel 
        this.add(topPanel, BorderLayout.PAGE_START);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.PAGE_END);
        this.add(rightPanel, BorderLayout.LINE_START);
        this.add(leftPanel, BorderLayout.LINE_END);
    }

    public String getLevel() {
        return level;
    }

    private class TitlePanel extends JPanel {
        public TitlePanel() {
            titleLabel = new JLabel("<html><h1><strong><i>Shopping Mama</i></strong></h1><hr></html>");
            add(titleLabel);
            setBackground(Color.pink);
        }
    }
    
    private class StartPanel extends JPanel {
        public StartPanel() {
            // note - originally, we 
            // 1. create new buttons 
            startButton = new JButton("Start");
            easyButton = new JRadioButton("Easy");
            // easyButton.setBackground(color.pink);
            medButton = new JRadioButton("Medium");
            hardButton = new JRadioButton("Hard");
            extremeButton = new JRadioButton("Extreme");

            // 2. create a button group to set up a listener
            ButtonGroup group = new ButtonGroup(); 
            group.add(easyButton);
            group.add(medButton);
            group.add(hardButton);
            group.add(extremeButton);

            // 3. set up listener and connect to the buttons 
            DifficultyListener listener = new DifficultyListener();
            // might not need the below 
            easyButton.addActionListener(listener);
            medButton.addActionListener(listener);
            hardButton.addActionListener(listener);
            extremeButton.addActionListener(listener);
            // but still keep this 
            startButton.addActionListener(listener);

            // 4. add everything to the main panel 
            // add(easyButton);
            // add(medButton);
            // add(hardButton);
            // add(extremeButton);
            // add(startButton);

            // 5. optional i think? set background and preferred size 
            setBackground(Color.white);
            setPreferredSize(new Dimension(400, 200));

            // create how to play button underneath 
            // 1. create the instructions button
            instrButton = new JButton("How to Play");

            // 2. create the listener
            InstrListener listener2 = new InstrListener();
            instrButton.addActionListener(listener2);
            // 3. add the button to main panel and set the background
            //add(instrButton);
        }
    }
    
    private class DifficultyListener implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            Object source = event.getSource();

            if (source == easyButton) {
                level = "Easy";
                game = new ShoppingMama("Easy");
            } else if (source == medButton) {
                level = "Medium";
                game = new ShoppingMama("Medium");
            } else if (source == hardButton) {
                level = "Hard";
                game = new ShoppingMama("Hard"); 
            } else if (source == extremeButton) {
                level = "Extreme";
                game = new ShoppingMama("Extreme");
            } else {
                // should go to new panel with specified board
            }

        }

    } 
    
    private class HowToPanel extends JPanel {
        public HowToPanel(){
            // create the instructions button
            instrButton = new JButton("How to Play");

            // create the listener
            InstrListener listener = new InstrListener();
            instrButton.addActionListener(listener);

            // add the button to main panel and set the background
            add(instrButton);
            setBackground(Color.pink);
        }
    } 
    
    private class InstrListener implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            Object source = event.getSource();
            if (source == instrButton) { // might not need
                // 
            } 

        } 
    }
    
    private class RightPanel extends JPanel {
        public RightPanel() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBackground(Color.white);

            bag = new ImageIcon("images/shoppingbag.png");
            orange = new ImageIcon("images/watermelon.png");

            bagLabel = new JLabel(bag);
            orangeLabel = new JLabel(orange);

            add(Box.createRigidArea(new Dimension(0, 50)));
            add(bagLabel);
            add(orangeLabel);

        } 
    } 

    private class LeftPanel extends JPanel {
        public LeftPanel() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBackground(Color.white);

            bag = new ImageIcon("images/shoppingbag.png");
            orange = new ImageIcon("images/watermelon.png");

            bagLabel = new JLabel(bag);
            orangeLabel = new JLabel(orange);

            add(Box.createRigidArea(new Dimension(0, 50)));
            add(orangeLabel);
            add(bagLabel);

        } 
    } 
} 


