import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * ShoppingMamaGUI demonstrates a graphical user interface and an event listener
 * for the ShoppingMama game.
 * 
 * 230 member contributions:
 * Tiasa - created GUI
 * Beryce - created the RXCardLayout and connected the panels
 * Anna - [Anna, insert description of what you did here]
 *
 * @author Tiasa Kim, Anna Kawakami, Beryce Garcia
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

        JPanel panelCont = new JPanel(); // container that contains all of the panels
        JPanel result_buttons = new JPanel(); // panel for buttons replay and exit on results
        // panel
        JPanel home_buttons = new JPanel();
        JPanel level_buttons = new JPanel();

        // meant for home page, but buttons don't work or appear
        // JRadioButton easy = new JRadioButton("Easy");
        // JRadioButton med = new JRadioButton("Medium");
        // JRadioButton hard = new JRadioButton("Hard");
        // JRadioButton extreme = new JRadioButton("Extreme");
    
            // level_buttons.add(easy);
            // level_buttons.add(med);
            // level_buttons.add(hard);
            // level_buttons.add(extreme);
            // level_buttons.setLayout(new FlowLayout());

        RXCardLayout cl = new RXCardLayout();
        panelCont.setLayout(cl);

        // creating panels
        InstructionsPanel intro = new InstructionsPanel();
        HomePanel home = new HomePanel(intro); // panelCont
        //String levelChosen = home.getLevel();
        ShoppingMama game = new ShoppingMama("Easy");
        ResultsPanel result = new ResultsPanel(game);
        ShoppingMamaPanel main = new ShoppingMamaPanel(panelCont, cl, game);

        // home panel -- NEEDS WORK
        JButton instructions = new JButton("How to Play");
        JButton start = new JButton("Start");
        home_buttons.setLayout(new FlowLayout());
        home_buttons.add(start);
        home_buttons.add(instructions);
        home.add(level_buttons, BorderLayout.CENTER);
        home.add(home_buttons);
        //home.add(instructions); // doesn't work
        //home.add(home_buttons, BorderLayout.CENTER);

        // instructions panel
        JButton back = new JButton("Back");
        intro.add(back, BorderLayout.SOUTH);

        // results panel
        JButton exit = new JButton("Exit");
        //JButton back_home = new JButton("Back to Home");
        result_buttons.add(exit);
        //result_buttons.add(back_home);
        result.add(result_buttons, BorderLayout.SOUTH);

        // "cards" in the panelCont container
        // this adds each of the panel into the container
        // the second parameter is the "id" associated with each of the
        // cards, so we can call them whenever we want 
        panelCont.add(home, "1");
        panelCont.add(intro, "2");
        panelCont.add(main, "3");
        panelCont.add(result, "4");
        cl.show(panelCont, "1"); // first page to be shown when 
        // we run the GUI (shows home page)

        // ?: ask if this is okay that it's formatted like this
        // Code inspired by a video on CardLayout demonstrated on YouTube
        // video link: https://www.youtube.com/watch?v=sAReaaTxNGU&t=464s

        // HOME PAGE LISTENERS
        // start
        start.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    // when the start button is pressed, the panel
                    // switches to the main panel with the game
                    cl.show(panelCont, "3");
                    main.getTimer().start();
                }
            });

        // instructions
        instructions.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    // when the instructions button is pressed, the
                    // panel switches to the panel with the instructions
                    cl.show(panelCont, "2");
                }
            });

        // INSTRUCTIONS ACTION LISTENERS
        // back
        back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    // when the back button is pressed, the panel
                    // switches from the instructions panel to the
                    // home page panel
                    cl.show(panelCont, "1");
                }
            });

        // RESULTS PAGE ACTION LISTENERS
        // exit
        exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    System.exit(0);
                }
            });

        frame.add(panelCont);
        frame.pack();
        panelCont.requestFocusInWindow();

        frame.setSize(880,705); // keep it this way, even though the 10 by 10 one
        // shows gray squares on the side -- need it for 11 by 11 maze
        frame.setResizable(false); // means that frame cannot be resized
        frame.setVisible(true); // makes the frame visible
    }
}