
/**
 * Creates a shopping list including the items the user wants to collect, the point value,
 * and whether it's a good or bad item. Allows updating of the shopping list to represent 
 * how many of each item the user has collected so far.  
 *
 * @author Anna Kawakami
 */
import java.util.Hashtable;
import java.util.Arrays;
import java.util.Random;
import java.util.Enumeration;
import java.util.ArrayList;
public class ShoppingList
{
    private Hashtable<ItemNode, Integer> shoppingList;
    private int numItems = 5;
    private String[] itemNames; 
    /**
     * Constructor for objects of class ShoppingList
     */
    public ShoppingList()
    {
        shoppingList = new Hashtable<ItemNode, Integer>(numItems); 
        itemNames = new String[]{"Kiwi", "Watermelon",
             "Strawberry", "Tangerine", "Cookie"};
    }

    /**
     * Creates and returns the shopping list hashtable. All keys of hashtable 
     * are filled but values (how much user accumulated of each item) is set to zero. 
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public Hashtable<ItemNode, Integer> makeShoppingList() 
    {
        ItemNode addedItem;
        Random rand = new Random();
        int randInt; 
        int numGood = 0;
        
        // pre-determining 3 fruit items that will have true values to make sure
                // exactly 3 have true values in the hashtable 
        ArrayList<Integer> randNums = getRandom3Ints();
        for (int i = 0; i < numItems; i++ ) {
            addedItem = new ItemNode();
            addedItem.setItemName(itemNames[i]);

            randInt = rand.nextInt(11);
            addedItem.setPointVal(randInt);
            
            if (randNums.contains(i)) { 
                addedItem.setImpact(true);
            } else {
                addedItem.setImpact(false);
            } 

            shoppingList.put(addedItem, 0);
        } 
        return shoppingList;
    }

    /** 
     * helper method to get 3 random numbers that are not overlapping 
     */
    public ArrayList<Integer> getRandom3Ints() {
        Random randInt = new Random();
        int numDiff = 0;
        int randNum;
        ArrayList<Integer> threeNums = new ArrayList<Integer>(3);
        while (numDiff < 3) { 
           randNum = randInt.nextInt(numItems);
           if (!(threeNums.contains(randNum))) {
               threeNums.add(randNum);
               numDiff++;
            } 
        } 
        return threeNums;
    } 
    
    /**
     * Returns most updated hashtable. 
     * @
     */
    public Hashtable<ItemNode, Integer> getShoppingList() {
        return shoppingList;
    } 

    /**
     * Returns the number of points the user accumulated
     */
    public int getUsersPoints() {
        int totalPoints = 0; 
        Enumeration<Integer> shoppingKeys = shoppingList.elements();
        Enumeration<ItemNode> shoppingItemNodes = shoppingList.keys();
        for (int i = 0; i < shoppingList.size(); i++) {
            // stores the number of items added 
            int numAdded = shoppingKeys.nextElement(); 
            // adds the number of items user retrieved * that items' point value  
            totalPoints += numAdded * (shoppingItemNodes.nextElement().getPointVal()) ;
        } 
        return totalPoints; 
    } 
    
    /**
     * Fills in hashtables' values according to when user collects item. 
     * Note (delete this later): ideally the parameter would be of 
     *                              type ItemNode so no loop needed
     */
    public void addPoints(String item) {
        Enumeration<ItemNode> shoppingKeys = shoppingList.keys();
        ItemNode itemAdded = new ItemNode();
        
        for (int i = 0; i < shoppingList.size(); i++) {
            itemAdded = shoppingKeys.nextElement();
            if (itemAdded.getItemName().equals(item)) {
                int addedVal = itemAdded.getPointVal();
                if (itemAdded.getImpact() == true) {
                    shoppingList.put(itemAdded, shoppingList.get(itemAdded) + addedVal);
                } else { 
                    addedVal = addedVal - 2*addedVal;
                    shoppingList.put(itemAdded, shoppingList.get(itemAdded) + addedVal);
                } 
            } 
        } 
    } 
    
    public String toString() {
        Enumeration<ItemNode> shoppingKeys = shoppingList.keys();
        ItemNode item = new ItemNode();
        String str = "<Top of shopping list>\n";
        for (int i = 0; i < shoppingList.size(); i++ ) {
            item = shoppingKeys.nextElement();
            str += "[" + i + "] item info: \n" + item;
            str += "Points accumulated from this item: " + shoppingList.get(item) + "\n\n";
        } 
        str += "<Bottom of shopping list>";
        return str;
    } 

    /**
     * Created for testing purposes. 
     */
    public static void main(String[] args) {
        ShoppingList testing = new ShoppingList();
        testing.makeShoppingList();
        System.out.println("test shopping list looks like this: \n" + testing);
        
        System.out.println("\nNow we are testing addPoints method:");
        System.out.println("Adding one strawberry, to tangerines and one cookie");
        testing.addPoints("Strawberry");
        testing.addPoints("Tangerine");
        testing.addPoints("Tangerine");
        testing.addPoints("Cookie");
        System.out.println("(It should add negative values to false booleans)");
        System.out.println(testing);
    } 
}
