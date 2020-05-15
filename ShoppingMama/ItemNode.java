
/**
 * Represents an item and its values, including the String name, pointValue, and impact (as
 * represented as true = good item to collect, false = bad item to collect).
 * Has these three elements as constructors. 
 *
 * @author Anna Kawakami 
 */
public class ItemNode
{
    // instance variables - replace the example below with your own
    private int pointValue; 
    private String itemName;
    private boolean impact; 
    /**
     * Constructor for objects of class Item
     */
    public ItemNode()
    {
        // initialise instance variables
        this.itemName = "";
        this.pointValue = 0;
        this.impact = true; 
    }

    /**
     * Returns the impact, in other words, whether the item will add or deduct points 
     * from the user's final score. 
     *
     * @param  N/A
     * @return boolean false if it deducts points, true if it adds points 
     */
    public boolean getImpact() {
        return impact; 
    }
    
    /**
     * Sets the impact of the item to true or false. 
     * 
     * @param boolean true if it adds points, false if it deducats points 
     */
    public void setImpact(boolean newImpact) {
        this.impact = newImpact;
    } 
    
    public String getItemName() {
        return itemName;
    } 
    
    public void setItemName(String newName) {
        this.itemName = newName; 
    } 
    
    public int getPointVal() {
        return pointValue; 
    } 
    
    public void setPointVal(int newPointVal) {
        this.pointValue = newPointVal;
    } 
    
    public String toString() {
        String str = "";
        str += "Item name: " + getItemName() + "\n";
        str += "Point value: " + getPointVal() + "\n";
        str += "Good item: " + getImpact() + "\n";
        return str;
    } 
    /**
     * Created for testing purposes. 
     */
    public static void main(String[] args) {
        ItemNode testing = new ItemNode();
        testing.setItemName("Apple");
        testing.setPointVal(9);
        testing.setImpact(false);
        
        System.out.println(testing);
    } 
}
