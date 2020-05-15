
/**
 * A class that stores the ImageIcon and its string name representation. 
 *
 * @author Anna Kawakami
 */
import javax.swing.*;
public class MyImageIcon
{
    // instance variables - replace the example below with your own
    private ImageIcon myIcon;
    private String iconName;
    /**
     * Constructor for objects of class MyImageIcon
     */
    public MyImageIcon(ImageIcon icon, String name)
    {
        myIcon = icon;
        iconName = name;
    }

    /**
     * Returns the string name representation of the icon 
     *
     * @param N/A
     * @return String representation of the image 
     */
    public String getIconName()
    {
        return iconName;
    }
    
    /**
     * Returns the image icon to be used 
     * 
     * @param N/A
     * @return ImageIcon object representation of the image 
     */
    public ImageIcon getIconImage() {
        return myIcon;
    } 
}
