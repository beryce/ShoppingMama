import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/**
 * YourScore panel displays the score of the user. This will be used
 * in the ScoresPanel, which will then be displayed onto the ResultsPanel.
 *
 * @author Beryce Garcia
 * @version December 21, 2018
 */
public class YourScorePanel
{
    private JPanel yourScore;
    private JLabel userScoreText;
    public YourScorePanel(int user_score) {
        yourScore = new JPanel();
        userScoreText = new JLabel(Integer.toString(user_score));
    }
    
    private void createJPanel() {
        yourScore.setBackground(Color.white);
        yourScore.setPreferredSize(new Dimension(50,50));
        yourScore.setBorder(BorderFactory.createLineBorder(Color.black));
        userScoreText.setFont(new Font("SansSerif", Font.PLAIN, 22));
        yourScore.add(userScoreText);
    }
    
    public JPanel getJPanel() {
        createJPanel();
        return yourScore;
    }
}
