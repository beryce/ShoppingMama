import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/**
 * ScoresPanel is the main panel in the Results Panel/
 *
 * @author Beryce Garcia
 * @version December 21, 2018
 */
public class ScoresPanel
{
    private JPanel scoresContainer, scores; 
    private YourScorePanel yourScorePanel;
    private JLabel your_score_text;
    /**
     * Constructor for objects of class ScoresPanel
     */

    public ScoresPanel(int userScore)
    {
        scoresContainer = new JPanel();
        scores = new JPanel();
        yourScorePanel = new YourScorePanel(userScore);
    }

    private void createScores() {
        scores = new JPanel();
        scores.setLayout(new FlowLayout());
        scores.setBackground(Color.white);
        your_score_text = new JLabel("Your score:     ");
        your_score_text.setFont(new Font("SansSerif", Font.PLAIN,24));

        scores.add(your_score_text);
        scores.add(yourScorePanel.getJPanel());

        scoresContainer.setLayout(new GridLayout(3,1));
        scoresContainer.setBackground(Color.white);
    }

    public JPanel getJPanel() {
        createScores();
        return scoresContainer;
    }
}
