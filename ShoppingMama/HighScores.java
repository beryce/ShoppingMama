import java.util.*;
import java.io.*;
import java.text.*;
/**
 * HighScores keeps track of the scores and sorts them in descending order with the 
 * highest score at the top. This information will be revealed in the results panel
 * at the end of the game. Only the top 5 scores will be displayed.
 * 
 * 230 member contributions:
 * Beryce - created entire class
 *
 * @author Beryce Garcia
 * @version December 17, 2018
 */

public class HighScores
{
    private int highestScore;
    private Vector<Integer> scoresList; // vector of high scores
    private String fileName; // what the user wants the file name to be (in our case it
    // is highscores.txt. This already exists in the ShoppingMama/FruityMama file
    
    /**
     * Creates and initializes a new HighScores class with the given parameters.
     * @param String fileName
     * @return N/A
     */
    public HighScores(String fileName) {
        this.fileName = fileName;
        scoresList = new Vector<Integer>();
    }

    /**
     * Adds a score to the vector and also updates the text file.
     * @param int score
     * @return N/A
     */
    public void add(int score) {
        // add the score to the vector that keeps tracks of the scores
        scoresList.add(score);
        
        // sort the scores in the vector in order
        this.sortScores();
        
        // update the text file
        this.writeScore();
    }
    
    /**
     * Helper method that sorts the scores in descending order.
     * @param N/A
     * @return N/A
     */
    private void sortScores() {
        // Found this code on StackOverFlow
        
        Comparator comparator = Collections.reverseOrder(); // method in vector class in java
        // that sorts the vector in the order desired
        
        Collections.sort(scoresList, comparator); // sorting the vector
    }

    /**
     * Writes the scores onto the text file.
     * @param N/A
     * @return N/A
     */
    public void writeScore() {
        try {
            PrintWriter writer = new PrintWriter(fileName);
            
            // iterating through the vector and writing the scores onto the file
            for (int i = 0; i < scoresList.size(); i++) {
                writer.println(scoresList.get(i));
            }
            
            writer.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
    
    /**
     * Returns an array of strings with the top 5 scores.
     * @param N/A
     * @return String[] top5
     */
    public String[] getTop5() {
        try {
            Scanner reader = new Scanner(new File(fileName));
            String[] result = new String[5];
            int count = 0; // to keep track of how many scores added
            
            while (reader.hasNext()) { 
                if (count == 5) {
                    return result; // exit early in case we already have our top 5
                } else {
                    // otherwise, add the next top score to the String[] result
                    result[count] = reader.nextLine() + "\n";
                    
                    count++;
                }
            }
            reader.close();
            return result;
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            String[] empty = new String[0];
            return empty;
        }
    }
    
    /**
     * Returns a string that displays all of the scores. Scores should be sorted
     * in descending order.
     * @param N/A
     * @return String
     */
    public String toString() {
        String result = "<Highest>\n";
        sortScores(); // sort in order we want
        
        // iterate through the scoresList vector and add it to the resulting
        // string that we want to return
        for (int i = 0; i < scoresList.size(); i++) {
            result += scoresList.get(i) + "\n";
        }
        result += "<Lowest>";
        return result;
    }

    /**
     * Used for testing.
     * @param String[] args
     * @return N/A
     */
    public static void main(String [] args) throws IOException {
        HighScores test = new HighScores("highscores.txt");
        test.add(276);
        test.add(182);
        test.add(5);
        test.add(789);
        test.add(277);
        test.add(900);
        test.add(8908990);
        test.add(0);
        //System.out.println(test);
        //test.sortScores();
        
        test.writeScore();
        String[] top = test.getTop5();
        for (int i = 0; i < top.length; i++) {
            System.out.print(top[i]);
        }
    }
}
