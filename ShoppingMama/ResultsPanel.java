import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/**
 * Description: ResultsPanel displays the resulting score of the user after the game has
 * terminated. It provides a score breakdown and also shows how many of which
 * items were collected by the user. Lastly, it provides users with the option
 * to replay the game or exit the game.
 * 
 * 230 member contributions:
 * Beryce - created entire class
 * 
 *
 * @author Beryce Garcia
 * @version December 21, 2018
 */
public class ResultsPanel extends JPanel
{
    private JPanel main, bottom, scores_section, 
    items_collected, your_score_pan, yscore_min, max_score_pan, mscore_min, 
    overall_score_pan, oscore_min, fruits_panel, buttons;

    private JLabel your_score, max_score, overall_score, 
    yscore_label, mscore_label, oscore_label, items_collected_txt, 
    kiwiPic, waterPic, strawPic, tangPic, 
    cookiePic, numKiwi, numGreen, numOrange,
    numWater, numTang, numCookie, numRed, numStraw;

    private ImageIcon kiwi, watermelon, red_apple, green_apple, tangerine, 
    strawberry, orange, cookie;

    private JButton replay, exit;

    private ShoppingMamaPanel game;
    
    private GameOverPanel gameOver;
    private WhiteSpacePanel whiteSpace;
    /** Constructor which creates and intializes the results panel.
     * @rparam N/A
     * @return N/A
     */
    public ResultsPanel(ShoppingMama game)
    {
        // background and layout of actual results panel
        setLayout(new BorderLayout());
        setBackground(Color.white);
        
        // calling on another class to create a panel that will 
        // say "Game Over" with a pink background. This will be
        // displayed at the top
        gameOver = new GameOverPanel();

        // creating the blank, white panel to put on the sides in order to 
        // create a whitespace
        whiteSpace = new WhiteSpacePanel();

        // creating center of the panel where the items to be
        // collected and the scores will be
        main = new JPanel();
        main.setLayout(new GridLayout(3,1));
        main.setBackground(Color.white);
        main.setBorder(BorderFactory.createLineBorder(Color.black));

        // calling on a helper function to draw the scores
        this.drawScores(); // will incude your, max, and overall score
        this.drawImages(); // draws the images and includes the icons
        this.drawItemsCollected(); // says how much of each item
        // collected

        // creating buttons exist and replay
        buttons = new JPanel();
        replay = new JButton("Replay");
        exit = new JButton("Exit");
        exit.addActionListener (new ButtonListener() );
        replay.addActionListener (new ButtonListener() );
        buttons.setBackground(Color.white);
        //buttons.add(replay);
        buttons.add(exit);

        this.drawHighScores();
       
        // adding all of the elements to the results panel
        add(main, BorderLayout.CENTER);
        add(whiteSpace.getJPanel(), BorderLayout.WEST);
        add(whiteSpace.getJPanel(), BorderLayout.EAST);
        add(gameOver.getJPanel(), BorderLayout.NORTH);
        add(buttons, BorderLayout.SOUTH);
    }
    
    
    /**
     * Helper method to display the top high scores on the results panel.
     * @param N/A
     * @return N/A
     */
    private void drawHighScores() {
        // getting top scores
        HighScores test = new HighScores("\n\n\nhighscores.txt");
        test.add(135); // hard coded for now
        test.add(243);
        test.add(343);
        String[] top5 = test.getTop5();
        
        // panel to add scores and text saying high scores
        JPanel highscorespanel = new JPanel();
        highscorespanel.setBackground(Color.white);
        highscorespanel.setLayout(new BorderLayout());
        
        // need to change this and figure it out
        JLabel score1 = new JLabel(" " + top5[0]);
        score1.setFont(new Font("SansSerif", Font.PLAIN, 20));
        JLabel score2 = new JLabel (" " + top5[1]);
        score2.setFont(new Font("SansSerif", Font.PLAIN, 20));
        JLabel score3 = new JLabel (" " + top5[2]);
        score3.setFont(new Font("SansSerif", Font.PLAIN, 20));
        
        // new panel for actual top 5 scores
        JPanel top5Scores = new JPanel();
        top5Scores.setBackground(Color.white);
        top5Scores.setLayout(new GridLayout(5,1));
        
    }

    /**Helper to create the scores on the panel
     * @param N/A
     * @return N/A
     */
    private void drawScores() {
        // creating the boxes for your score, max score, and overall score

        // this is for your score mini panel (box with solid black 
        // border that appears after JLabel Your score)
        yscore_min = new JPanel();
        yscore_min.setBackground(Color.white);
        yscore_min.setPreferredSize(new Dimension(50,50));
        yscore_min.setBorder(BorderFactory.createLineBorder(Color.black));
        yscore_label = new JLabel("98");
        yscore_label.setFont(new Font("SansSerif", Font.PLAIN, 22));
        yscore_min.add(yscore_label);

        // max score mini panel (box with solid black border that appears
        // after JLabel Max score
        // mscore_min = new JPanel();
        // mscore_min.setBackground(Color.white);
        // mscore_min.setPreferredSize(new Dimension(50,50));
        // mscore_min.setBorder(BorderFactory.createLineBorder(Color.black));
        // mscore_label = new JLabel("100");
        // mscore_label.setFont(new Font("SansSerif", Font.PLAIN, 22));
        // mscore_min.add(mscore_label);

        // // overall score mini panel (box with solid black border that appears
        // // after JLabel Max score
        // oscore_min = new JPanel();
        // oscore_min.setBackground(Color.white);
        // oscore_min.setPreferredSize(new Dimension(50,50));
        // oscore_min.setBorder(BorderFactory.createLineBorder(Color.black));
        // oscore_label = new JLabel("98%");
        // oscore_label.setFont(new Font("SansSerif", Font.PLAIN, 22));
        // oscore_min.add(oscore_label);

        // creating components inside main panel
        // adding the text "Your Score: " as well as the black box that 
        // appears next to your score which will include the score of the
        // user
        your_score_pan = new JPanel();
        your_score_pan.setLayout(new FlowLayout());
        your_score = new JLabel("Your score:     ");
        your_score.setFont(new Font("SansSerif", Font.PLAIN,24));
        your_score_pan.setBackground(Color.white);
        your_score_pan.add(your_score);
        your_score_pan.add(yscore_min);

        // adding overall score, your score, and max section to a panel
        // which will be placed near the top of the results panel
        scores_section = new JPanel();
        scores_section.setLayout(new GridLayout(3,1));
        scores_section.add(your_score_pan);
        scores_section.setBackground(Color.white);

        main.add(scores_section);
        main.setPreferredSize(new Dimension(600,600));
    }

    /**Helper method to put the image icons on the panel
     * @param N/A
     * @return N/A
     */
    private void drawImages() {
        // creating a new panel for fruits. This is where the images 
        // will go.
        fruits_panel = new JPanel();
        fruits_panel.setLayout(new GridLayout(1,1));
        fruits_panel.setBackground(Color.white);

        // creating the labels for the fruits to be displayed in items
        // collected
        // Creating labels that will specify how much of each item was
        // collected. This will be changed later with the implementation
        // of the backend.
        numKiwi = new JLabel("X3");
        numKiwi.setFont(new Font("SansSerif", Font.PLAIN, 24));
        numWater = new JLabel("X3");
        numWater.setFont(new Font("SansSerif", Font.PLAIN, 24));
        numTang = new JLabel("X3");
        numTang.setFont(new Font("SansSerif", Font.PLAIN, 24));
        numCookie = new JLabel("X3");
        numCookie.setFont(new Font("SansSerif", Font.PLAIN, 24));
        numStraw = new JLabel("X3");
        numStraw.setFont(new Font("SansSerif", Font.PLAIN, 24));

        // KIWI
        ImageIcon kiwi = new ImageIcon("kiwi.jpeg");
        kiwiPic = new JLabel();
        kiwiPic.setIcon(kiwi);

        // WATERMELON
        ImageIcon watermelon = new ImageIcon("watermelon.png");
        waterPic = new JLabel();
        waterPic.setIcon(watermelon);

        // TANGERINE
        ImageIcon tangerine = new ImageIcon("tangerine.jpg");
        tangPic = new JLabel();
        tangPic.setIcon(tangerine);

        // STRAWBERRY
        ImageIcon strawberry = new ImageIcon("strawberry.png");
        strawPic = new JLabel();
        strawPic.setIcon(strawberry);

        // COOKIE
        ImageIcon cookie = new ImageIcon("cookie.png");
        cookiePic = new JLabel();
        cookiePic.setIcon(cookie);

        fruits_panel.add(kiwiPic);
        fruits_panel.add(numKiwi);
        fruits_panel.add(waterPic);
        fruits_panel.add(numWater);
        fruits_panel.add(tangPic);
        fruits_panel.add(numTang);
        fruits_panel.add(strawPic);
        fruits_panel.add(numCookie);
        fruits_panel.add(cookiePic);
        fruits_panel.add(numStraw);
    }

    /**Helper method to draw the items collected
     * @param N/A
     * @return N/A
     */
    private void drawItemsCollected() {
        // creating a separate panel for all of the items collected
        items_collected = new JPanel();
        items_collected.setLayout(new GridLayout(2,1));
        items_collected.setBackground(Color.white);
        items_collected_txt = new JLabel("Items Collected: ");
        items_collected_txt.setFont(new Font("SansSerif", Font.PLAIN,24));
        //items_collected.setBorder(BorderFactory.createLineBorder(Color.black));

        items_collected.add(items_collected_txt);
        items_collected.add(fruits_panel);
        main.add(items_collected);

        // white space between thank you text and items collected
        JPanel empty_panel = new JPanel();
        empty_panel.setBackground(Color.white);
    }
    
    /**Listener that is used by the exit button to terminate the system
     * @param N/A
     * @return N/A 
     */
    private class ButtonListener implements ActionListener
    {
        /**
         * Method that terminates the system when button is clicked.
         * Should it terminate the system or redirect to another panel?
         * @param ActionEvent event
         * @return N/A
         */
        public void actionPerformed (ActionEvent event)
        {
            if (event.getSource() == exit) {
                System.exit(0);
            } else if (event.getSource() == replay) {
                System.exit(0); // currently has it so that the system terminates
                // when replay button is clicked, but this should be changed to go
                // to the results panel
            }
        }
    }
}
