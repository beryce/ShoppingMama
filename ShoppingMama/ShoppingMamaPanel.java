import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.util.Random;
import javafoundations.*;
import java.util.LinkedList;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Enumeration;
import java.lang.Math;

/**
 * ShoppingMamaGUI demonstrates a graphical user interface and an event listener
 * for the ShoppingMama game. 
 * 
 * 230 member contributions:
 * Tiasa -
 * Beryce -
 * Anna -
 *
 * @author Tiasa Kim, Beryce Garcia, Anna Kawakami
 * @version December 17, 2018
 */

public class ShoppingMamaPanel extends JPanel {
    protected int WIDTH, HEIGHT;

    private final JLabel 
    valWatermelon = new JLabel("1"), 
    valTangerine = new JLabel("3"), 
    valStrawberry = new JLabel("5"),
    valKiwi = new JLabel("7");
    JLabel valCookie = new JLabel("2");

    private JLabel timerLabel, x1, x2, x3;
    private int score, numX, timeLeft;

    //private String level;
    private JPanel top, maze, list, timer;
    private ImageIcon whiteX, redX; 
    private JButton newMaze, exit;

    private boolean stillRunning = true; // changes to false when game runs out of time, all items
    // are collected and user reached the door, or 3 x's are gone

    private String difficulty;

    private Timer t;

    // MyImageIcon stores the ImageIcon and the icon's string name - anna added 
    private MyImageIcon cookieIcon, watermelonIcon, tangerineIcon, strawberryIcon, kiwiIcon;  

    // storing the number of items of each item the user collected - anna added 
    private int cookieNum, watermelonNum, tangerineNum, strawberryNum, kiwiNum;
    // storing the number of items displayed on the board into an array - anna added 
    private Integer[] totalNumItems;   // still have to add nums to this     

    // used to connect the shoppingListPanel with the shoppinglist - anna added 
    private ShoppingList shopList;
    private Hashtable<ItemNode, Integer> pointsHash;

    private ShoppingMama game;
    private JPanel panelCont;
    private RXCardLayout cl;
    /**
     * Constructor for the ShoppingMamaPanel class. Creates a ShoppingMamaPanel
     * with the given parameters.
     * @param JPanel p, RXCardLayout c, ShoppingMama g
     * @return N/A
     */
    public ShoppingMamaPanel(JPanel p, RXCardLayout c, ShoppingMama g) {
        // initalizing the variables to the parameters given
        game = g;
        panelCont = p;
        cl = c;
        WIDTH = game.getWidth();
        HEIGHT = game.getHeight();
        difficulty = g.getDifficulty();
        score = numX = 0;
        setTimeLimit();

        //creates timer for time limit of game
        t = new Timer(1000, new TimerListener());

        //set main panel layout
        this.setLayout(new BorderLayout());

        //create panels
        top = new topPanel();
        maze = new MazePanel(WIDTH, HEIGHT, difficulty);
        list = new shoppingListPanel();

        //set border panels
        Border b1 = BorderFactory.createLineBorder(Color.orange, 2);
        Border b2 = BorderFactory.createEtchedBorder();
        Border b3 = BorderFactory.createRaisedBevelBorder();

        top.setBorder(BorderFactory.createCompoundBorder(b3, b2)); 
        maze.setBorder(BorderFactory.createCompoundBorder(b1, b2));
        list.setBorder(BorderFactory.createCompoundBorder(b1, b2));

        //add panels to main panel
        this.add(top, BorderLayout.NORTH);
        this.add(maze, BorderLayout.CENTER);
        this.add(list, BorderLayout.EAST);

        //starts timer
        //t.start();

        // setting numbers collected to zero
        cookieNum = 0;
        watermelonNum = 0;
        tangerineNum = 0; 
        strawberryNum = 0; 
        kiwiNum = 0; 

        // creating an instance of the points list hashtable 
        shopList = new ShoppingList();
        pointsHash = shopList.makeShoppingList(); 
    }

    /**
     * Helper method that returns the timer. Useful to have this
     * so that timers of new instantations of the game can be restarted.
     * @param N/A
     * @return Timer t
     */
    public Timer getTimer() {
        return t;
    }
    
    public void setTimer(Timer r) {
        t = r;
    }

    /**
     * Method that sets the time limit of the timer according the the 
     * level of difficulty chosen.
     * @param N/A
     * @return N/A
     */
    public void setTimeLimit() {
        //sets time limit 
        if(difficulty.equals("Easy")) {
            timeLeft = 60;
        } else if(difficulty.equals("Medium")) {
            timeLeft = 45;
        } else if(difficulty.equals("Hard")) {
            timeLeft = 30;      
        } else { 
            timeLeft = 15;
        }
    }
    
    public int getTimeLeft() {
        return timeLeft;
    }
    
    public void setTimeLeft(int t) {
        timeLeft = t;
    }

    private class shoppingListPanel extends JPanel {
        private itemsList items;
        public shoppingListPanel() {
            //set panel styling
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBackground(Color.pink);

            items = new itemsList();

            //create Shopping List JLabel
            JLabel list = new JLabel("   Shopping List   ");
            list.setAlignmentX(Component.CENTER_ALIGNMENT);
            list.setFont(new Font("SansSerif", Font.PLAIN, 24));

            //create Draw New Maze JButton
            newMaze = new JButton("Draw new Maze");
            newMaze.addActionListener(new ButtonListener());
            newMaze.addActionListener(new TimerListener());
            newMaze.setAlignmentX(Component.CENTER_ALIGNMENT);
            newMaze.setFont(new Font("SansSerif", Font.PLAIN, 16));

            //create Exit JButton
            exit = new JButton("Exit Game");
            exit.addActionListener(new ButtonListener());
            exit.setAlignmentX(Component.CENTER_ALIGNMENT);
            exit.setFont(new Font("SansSerif", Font.PLAIN, 16));

            //add elements to panel
            add(Box.createRigidArea(new Dimension(25, 25)));
            add(list);
            add(Box.createRigidArea(new Dimension(25, 25)));
            add(items);
            add(Box.createRigidArea(new Dimension(25, 25)));
            add(newMaze);
            add(exit);
            add(Box.createRigidArea(new Dimension(25, 25)));
        }
    }

    /**
     * 
     */
    private class itemsList extends JPanel {
        private JLabel numWatermelon, numTangerine, numStrawberry, numKiwi, numCookie;
        public itemsList() {
            setLayout(new GridLayout(5,3));
            //lab.setHorizontalAlignment(SwingConstants.LEFT);
            //yourGrid.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel item = new JLabel("Item:");
            JLabel numCollected = new JLabel("Items Collected:");
            JLabel ptsWorth = new JLabel("Point Value:");

            ImageIcon i1 = new ImageIcon("watermelon.png");
            ImageIcon i2 = new ImageIcon("tangerine.jpg");
            ImageIcon i3 = new ImageIcon("strawberry.png");
            ImageIcon i4 = new ImageIcon("kiwi.jpeg");
            ImageIcon i5 = new ImageIcon("cookie.png");

            JLabel l1 = new JLabel(i1);
            JLabel l2 = new JLabel(i2);
            JLabel l3 = new JLabel(i3);
            JLabel l4 = new JLabel(i4);
            JLabel l5 = new JLabel(i5);

            numWatermelon = new JLabel("0");
            numTangerine = new JLabel("0");
            numStrawberry = new JLabel("0");
            numKiwi = new JLabel("0");
            numCookie = new JLabel("0");

            //set JLabel font styling
            item.setFont(new Font("SansSerif", Font.PLAIN, 14));

            numCollected.setFont(new Font("SansSerif", Font.PLAIN, 14));
            ptsWorth.setFont(new Font("SansSerif", Font.PLAIN, 14));
            numWatermelon.setFont(new Font("SansSerif", Font.PLAIN, 22));
            numTangerine.setFont(new Font("SansSerif", Font.PLAIN, 22));
            numStrawberry.setFont(new Font("SansSerif", Font.PLAIN, 22));
            numKiwi.setFont(new Font("SansSerif", Font.PLAIN, 22));
            numCookie.setFont(new Font("SansSerif", Font.PLAIN, 22));
            valWatermelon.setFont(new Font("SansSerif", Font.PLAIN, 22));
            valTangerine.setFont(new Font("SansSerif", Font.PLAIN, 22));
            valStrawberry.setFont(new Font("SansSerif", Font.PLAIN, 22));
            valKiwi.setFont(new Font("SansSerif", Font.PLAIN, 22));
            valCookie.setFont(new Font("SansSerif", Font.PLAIN, 22));

            //set JLabel alignment 
            item.setHorizontalAlignment(SwingConstants.CENTER);
            numCollected.setHorizontalAlignment(SwingConstants.CENTER);
            ptsWorth.setHorizontalAlignment(SwingConstants.CENTER);
            numWatermelon.setHorizontalAlignment(SwingConstants.CENTER);
            numTangerine.setHorizontalAlignment(SwingConstants.CENTER);
            numStrawberry.setHorizontalAlignment(SwingConstants.CENTER);
            numKiwi.setHorizontalAlignment(SwingConstants.CENTER);
            valWatermelon.setHorizontalAlignment(SwingConstants.CENTER);
            valTangerine.setHorizontalAlignment(SwingConstants.CENTER);
            valStrawberry.setHorizontalAlignment(SwingConstants.CENTER);
            valKiwi.setHorizontalAlignment(SwingConstants.CENTER);

            //add elements to panel
            add(item);
            add(numCollected);
            add(ptsWorth);
            add(l1);
            add(numWatermelon);
            add(valWatermelon);
            add(l2);
            add(numTangerine);
            add(valTangerine);
            add(l3);
            add(numStrawberry);
            add(valStrawberry);
            add(l4);
            add(numKiwi);
            add(valKiwi);
            //add(l5);
            //add(numCookie);
            //add(valCookie);
        }
    }

    /**
     * Updates number of points on screen and accumulates users' points so far 
     * 
     * @input N/A 
     * @return N/A
     */ 
    private void accumulatePoints() { 
        //if the shopping cart crosses over item strawberry 
        strawberryNum++;
        // update number on board 

        //update the points in the ShoppingList
        shopList.addPoints(cookieIcon.getIconName()); 

        // if the shopping cart crosses over watermelon 
        watermelonNum++;
        // update number on board 

        //update the points in the ShoppingList
        shopList.addPoints(watermelonIcon.getIconName());
        // update number on board

        // if the shopping cart crosses over kiwi 
        kiwiNum++;
        // update number on board 

        //update the points in the ShoppingList
        shopList.addPoints(kiwiIcon.getIconName());

        // if the shopping cart crosses over tangerine 
        tangerineNum++; 
        // update number on board 

        //update the points in the ShoppingList
        shopList.addPoints(tangerineIcon.getIconName());
    } 

    /**
     * Returns the max number of points user could get 
     */
    public int getMaxScore() { 
        int maxPoints = 0; 
        Enumeration<ItemNode> shoppingItemNodes = pointsHash.keys();
        for (int i = 0; i < pointsHash.size(); i++) {
            // stores the number of items added 
            ItemNode itemAdded = shoppingItemNodes.nextElement(); 
            // adds the number of items user retrieved * that items' point value  
            maxPoints += totalNumItems[i] * (itemAdded.getPointVal());
            // note -- totalNumItems is an empty array right now. 
            //  somewhere in the code we need to add the number of items 
            // displayed on the board of 0. kiwi, 1. watermelon, 2. strawberry
            // 3. tangerine, 4. cookie 
        } 
        return maxPoints; 
        // you can get the users points by calling ShoppingList's method, getUsersPoints
    }

    /** 
     * Returns the percent of points user got with respect to max score 
     */
    public int getOverallScore() { 
        int maxNum = getMaxScore();
        int usersNum = shopList.getUsersPoints();
        return Math.round(usersNum/maxNum); 
    } 

    private class topPanel extends JPanel {
        public topPanel() {
            //set panel styling
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBackground(new Color(224, 255, 255));

            //create new JLabel to display level of difficulty
            JLabel levelLabel = new JLabel("Level: " + difficulty);
            levelLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
            //level.setAlignmentX(Component.CENTER_ALIGNMENT);

            //create new JLabel to display current score
            JLabel scoreLabel = new JLabel("Score: " + score);
            scoreLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));

            //create sub panel to store and display 3 Xs
            XPanel xMarks = new XPanel();

            //create new JLabel to display timer
            SimpleDateFormat df = new SimpleDateFormat("ss:SS");
            timerLabel = new JLabel(df.format(timeLeft)); 
            timerLabel.setFont(new Font("SansSerif", Font.PLAIN, 36));
            add(timerLabel);

            //add elements to panel
            add(Box.createRigidArea(new Dimension(50, 0)));
            add(levelLabel);
            add(Box.createRigidArea(new Dimension(50, 0)));
            add(scoreLabel);
            add(Box.createRigidArea(new Dimension(50, 0)));
            add(xMarks);
            add(Box.createRigidArea(new Dimension(50, 0)));
            //add(timerLabel);
            add(Box.createRigidArea(new Dimension(50, 0)));
        }
    }

    private class XPanel extends JPanel {
        public XPanel() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

            whiteX = new ImageIcon("whiteX.png");
            redX = new ImageIcon("redX.png");

            x1 = new JLabel(whiteX);
            x2 = new JLabel(whiteX);
            x3 = new JLabel(whiteX);

            add(Box.createRigidArea(new Dimension(0, 50)));
            add(x1);
            add(x2);
            add(x3); 
            add(Box.createVerticalGlue());
        }
    }

    private class MazePanel extends JPanel {
        private ImageIcon shopper, cookie, path, wall, 
        watermelon, tangerine, strawberry, kiwi, door;
        private ImageIcon[][] icons; 
        private ArrayList<ImageIcon> imgIcons; 
        private int WIDTH, HEIGHT, cookieCounter, numCookiesAllowed, dimension;
        // cookieCounter and numCookiesAllowed might not be used if we can't
        // change number of cookies per graph

        private Random r = new Random(); //generate a random icon from list
        private Shopper s;
        private boolean move;

        public MazePanel(int row, int col, String level) {
            WIDTH = row;
            HEIGHT = col;
            dimension = WIDTH * HEIGHT;
            cookieCounter = 0;
            difficulty = level;

            //disables paintCompenent() to redraw all the items
            //whenever user moves across the board
            move = false;

            this.setLayout(new GridLayout(row,col));
            icons = new ImageIcon[row][col];
            imgIcons = new ArrayList<>();
            s = new Shopper();

            //create icons
            createIcons(); 
            addKeyListener(new DirectionListener());

            //setBackground(Color.pink);
            setPreferredSize(new Dimension(WIDTH*50, HEIGHT*50));
            setFocusable(true);
        }

        /**
         * Creates item icons to and add to array list
         */
        private void createIcons() {
            //create new ImageIcons       
            shopper = new ImageIcon("shoppingbag.png");
            cookie = new ImageIcon("cookie.png");
            path = new ImageIcon("path.png");
            wall = new ImageIcon("wall.png");
            watermelon = new ImageIcon("watermelon.png");
            tangerine = new ImageIcon("tangerine.jpg");
            strawberry = new ImageIcon("strawberry.png");
            kiwi = new ImageIcon("kiwi.jpeg");
            door = new ImageIcon("door.png"); // this is supposed to be where the user
            // has to go after collecting all of the items they need to collect.

            //add icons to imgIcons list
            imgIcons.add(shopper); //index 0
            imgIcons.add(cookie); // index 1
            imgIcons.add(path); // index 2
            imgIcons.add(wall); // index 3
            imgIcons.add(watermelon); // index 4
            imgIcons.add(tangerine); // index 5
            imgIcons.add(strawberry); // index 6
            imgIcons.add(kiwi); // index 7
        }

        /**
         * Draws the item icons on the maze
         * 
         * @param Graphics g - object used to draw image on screen 
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            boolean finish = false;

            // SETS NUMBER OF COOKIES NEEDED PER DIFFICULTY
            // if(difficulty.equals("Easy")) {
            // numCookiesAllowed = dimension / 8;
            // } else if(difficulty.equals("Medium")) {
            // numCookiesAllowed = dimension / 6;
            // } else if(difficulty.equals("Hard")) {
            // numCookiesAllowed = dimension / 4;   
            // } else { 
            // numCookiesAllowed = dimension / 2;
            // }

            shopper.paintIcon(this, g, s.getX()*50, s.getY()*50);
            icons[s.getX()][s.getY()] = shopper;

            if(!move) {
                for(int i=0; i<WIDTH; i++) {
                    for(int j=0; j<HEIGHT; j++) {
                        if(icons[i][j] != shopper) {
                            ImageIcon nextItem;

                            // if the position of i and j is that of the bottom right
                            // corner of the graph, turn it into a door
                            if (i == (WIDTH - 1) && j == (HEIGHT - 1)) {
                                nextItem = door; 
                            } else {
                                int randImg = r.nextInt(imgIcons.size()-1)+1;
                                nextItem = imgIcons.get(randImg);
                            }
                            icons[i][j] = nextItem; 
                            nextItem.paintIcon(this, g, i*50, j*50);
                        }
                    }
                }
            } else {
                finish = true;
                for(int i=0; i<WIDTH; i++) {
                    for(int j=0; j<HEIGHT; j++) {
                        if(icons[i][j] != shopper) {
                            ImageIcon repaintItem = icons[i][j]; 
                            if(repaintItem == watermelon || repaintItem == tangerine
                            || repaintItem == strawberry || repaintItem == kiwi) {
                                finish = false;
                            }
                            repaintItem.paintIcon(this, g, i*50, j*50);
                        }
                    }
                }
            }

            // calling the hasPath method to ensure that
            // this maze has a path
            Point exit = new Point(WIDTH - 1, HEIGHT - 1);
            boolean isValid = hasPath(icons, s, exit);

            // conducts the DFS--if there is no path, it makes a recursive
            // call to itself and creates a new maze
            if (!isValid) {
                paintComponent(g);
            }

            if(finish) {
                System.out.println("You won!");
            }
        }

        /**
         * Turns the X's from a white X to a red X if a cookie
         * has been collected.
         * @param N/A
         * @return N/A
         */
        public void updateX() {
            numX++;
            // if there is only 1 X, set the first X to a red X
            if (numX <= 1) {
                x1.setIcon(redX);
            } else if (numX > 1 && numX <= 2) { // if there are 2 X's, set second one to red X
                x2.setIcon(redX);
            } else { // if there are 3 X's, set third to red X and terminate the game
                x3.setIcon(redX);
                cl.show(panelCont, "4"); // shows result panel
            }
        }

        /**
         * Implements a DFS method to see if the 2D array has a path
         * from the starting corner to the door
         * @param ImageIcon[][] images, Shopper start, Point end
         * @return boolean true or false
         */
        public boolean hasPath(ImageIcon[][] images, Shopper start, Point end) {
            LinkedStack<Point> stk = new LinkedStack<Point>(); // stack to keep track of the 
            // "children" or in this case, its neighbors
            LinkedList<Point> iter = new LinkedList<Point>(); // keeping track of the path
            boolean[][] visited = new boolean[WIDTH][HEIGHT]; // keeping track of 
            // which points have been visited

            boolean found;
            Point current;
            Point startingPoint = new Point(start.getX(), start.getY());

            // marking all of the points in visited as false initially
            for (int i = 0; i < visited.length; i++) {
                for (int j = 0; j < visited[i].length; j++) {
                    visited[i][j] = false;
                }
            }

            // pushing the starting point to the stack and to the iter
            stk.push(startingPoint);
            iter.add(startingPoint);

            // mark starting point as visited
            visited[startingPoint.x][startingPoint.y] = true;

            while (!stk.isEmpty()) {
                // take the top of the stack and get its neighbors ("children")
                current = stk.peek();
                LinkedList<Point> neighbors = getNeighbors(current);
                found = false;
                for(int i = 0; i < neighbors.size() && !found; i++) {
                    Point point = neighbors.get(i); // get the children's neighbors

                    // if the point (children/neighbor) has not been visited and
                    // the point is not a wall or a cookie
                    if (!visited[point.x][point.y] && 
                    !icons[point.x][point.y].equals(cookie) &&
                    !icons[point.x][point.y].equals(wall)) {
                        // add to stack and iter and mark as visited
                        stk.push(point);
                        iter.add(point);
                        visited[point.x][point.y] = true;
                        found = true; // using this to let system know
                        // that there are neighbors
                    }
                }

                // if there are no neighbors, pop it off
                if (!found && !stk.isEmpty()) {
                    stk.pop();
                }
            }

            // check to see if the desired ending point is in the iter
            if (iter.contains(end)) {
                return true;
            }
            return false;
        }

        /**
         * Helper method to gather the "children" or in this case the
         * "neighbors" of the point
         * @param Point p
         * @return LinkedList
         */
        private LinkedList<Point> getNeighbors(Point p) {
            LinkedList<Point> result = new LinkedList<Point>();
            Point toAdd;
            int x = p.x;
            int y = p.y;

            // see if there's a neighbor above
            if (y > 0) {
                if (icons[x][y-1] != null || !icons[x][y-1].equals(wall)) {
                    toAdd = new Point(x, y-1);
                    result.add(toAdd);
                }
            }
            // see if there's a neighbor to the left
            if (x > 0) {
                if (icons[x-1][y] != null || !icons[x-1][y].equals(wall)) {
                    toAdd = new Point(x-1, y);
                    result.add(toAdd);
                }
            }

            // see if there's a neighbor below
            if (y < HEIGHT - 1) {
                if (icons[x][y+1] != null || !icons[x][y+1].equals(wall)) {
                    toAdd = new Point(x, y+1);
                    result.add(toAdd);
                }
            }

            // see if there's a neighbor to the right
            if (x < WIDTH - 1) {
                if (icons[x+1][y] != null || !icons[x+1][y].equals(wall)) {
                    toAdd = new Point(x+1, y);
                    result.add(toAdd);
                }
            }
            return result;
        }

        /**
         * Represents the listener for keyboard activity
         */
        private class DirectionListener implements KeyListener {
            /**
             * Responds to the user pressing arrow keys by adjusting the 
             * image location of shopping bag image accordingly.
             */
            @Override
            public void keyPressed(KeyEvent event) {
                //System.out.println("count" + count);

                switch(event.getKeyCode()) {
                    case KeyEvent.VK_UP:
                    if(s.getY()!=0 && !icons[s.getX()][s.getY()-1].equals(wall)) {
                        if (icons[s.getX()][s.getY()-1].equals(cookie)) {
                            updateX(); // mark a red X if user ran through cookie
                        }

                        //if cell is not a wall, change icon at current location to 
                        //path and new location to shopping bag
                        s.move(0, -1); 
                        icons[s.getX()][s.getY()+1] = path;
                        icons[s.getX()][s.getY()] = shopper;
                        move = true;
                    }
                    break;

                    case KeyEvent.VK_DOWN:
                    if(s.getY() != WIDTH && !icons[s.getX()][s.getY()+1].equals(wall)) {
                        if (icons[s.getX()][s.getY()+1].equals(cookie)) {
                            updateX(); // mark a red X if user ran through cookie
                        }

                        // move the shopper
                        s.move(0, 1);

                        // if shopper is now on the door, show the results panel if they
                        // were playing on "Extreme" level. Otherwise, move on to the next
                        // level
                        if(isDoor()) {
                            if (difficulty.equals("Extreme")) {
                                cl.show(panelCont, "4");
                            } else {
                                changeLevels();
                            }
                        }

                        icons[s.getX()][s.getY()-1] = path;
                        icons[s.getX()][s.getY()] = shopper;
                        move = true;
                    }
                    break;

                    case KeyEvent.VK_LEFT:
                    if(s.getX() != 0 && !icons[s.getX()-1][s.getY()].equals(wall)) {
                        if (icons[s.getX()-1][s.getY()].equals(cookie)) {
                            updateX(); // mark a red X if user ran through cookie
                        }
                        s.move(-1, 0);

                        icons[s.getX()+1][s.getY()] = path;
                        icons[s.getX()][s.getY()] = shopper;
                        move = true;
                    } 
                    break;
                    case KeyEvent.VK_RIGHT:
                    if(s.getX() != HEIGHT && !icons[s.getX()+1][s.getY()].equals(wall)) {
                        if (icons[s.getX()+1][s.getY()].equals(cookie)) {
                            updateX(); // mark a red X if user ran through cookie
                        }

                        // move shopper
                        s.move(1, 0);

                        // if shopper is now on the door, show the results panel if they
                        // were playing on "Extreme" level. Otherwise, move on to the next
                        // level
                        if(isDoor()) {
                            if (difficulty.equals("Extreme")) {
                                cl.show(panelCont, "4");
                            } else {
                                changeLevels();
                            }
                        }

                        icons[s.getX()-1][s.getY()] = path;
                        icons[s.getX()][s.getY()] = shopper;
                        move = true;
                    }
                    break;
                }
                repaint();
                setFocusable(true);
            }

            /**
             * Helper method that returns true if the user is on the door, false
             * otherwise.
             * @param N/A
             * @return boolean true or false
             */
            private boolean isDoor() {
                if (icons[s.getX()][s.getY()].equals(door)) {
                    return true;
                }
                return false;
            }

            /**
             * Helper method that changes the levels once user is on door.
             * @param N/A
             * @return N/A
             */
            private void changeLevels() {
                if (difficulty.equals("Easy")) {
                    game = new ShoppingMama("Medium");
                } else if (difficulty.equals("Medium")) {
                    game = new ShoppingMama("Hard");
                } else if (difficulty.equals("Hard")) {
                    game = new ShoppingMama("Extreme");
                } 
                // create a new panel
                ShoppingMamaPanel newMain = new ShoppingMamaPanel(panelCont, cl, game);

                // replace old panel with this new panel
                panelCont.add(newMain,"3");
                t.stop();
                setTimeLeft(newMain.getTimeLeft());
                t = getTimer();
                newMain.getTimer().start();
                
                //t.start();
                
                setTimeLimit();
                //t = newMain.getTimer();
                //newMain.getTimer().stop();

                // show the new game panel
                cl.show(panelCont, "3");
            }

            //*****************************************************************
            //  Unused event methods - left as empty defintions
            //*****************************************************************
            public void keyTyped(KeyEvent event) {}

            public void keyReleased(KeyEvent event) {}
        }
    }
    //*****************************************************************
    //  Represents a listener for timer.
    //*****************************************************************
    private class TimerListener implements ActionListener
    {
        //--------------------------------------------------------------
        //  Determines which timer was set off executes action accordingly.
        //-------------------------------------------------------------- 
        public void actionPerformed(ActionEvent event) 
        {
            if(event.getSource() == newMaze) {
                // if user pressed on draw new maze, restart the timer and ensure
                // that the appropriate amount is set to timeLeft
                t.stop();
            } else {
                //quit game and direct to results panel
                timeLeft--;
                SimpleDateFormat df = new SimpleDateFormat("ss:SS");
                timerLabel.setText(df.format(timeLeft)); 
                System.out.println(timeLeft);
                if(timeLeft<=0) {
                    // stop the timer and go straight to results panel
                    t.stop();
                    cl.show(panelCont, "4");
                }
            }
        }
    }

    //*****************************************************************
    //  Represents a listener for Draw New Maze and Exit buttons.
    //*****************************************************************
    private class ButtonListener implements ActionListener
    {
        //--------------------------------------------------------------
        //  Determines which button was pressed and executes action accordingly.
        //--------------------------------------------------------------
        public void actionPerformed (ActionEvent event)
        {
            if (event.getSource() == newMaze) { // We want to know which button clicked!
                //t.stop();
                //cl.show(panelCont, "1");
                //t.restart();
                // following code is similar to the code used above--maybe we can abstract
                // this code and make it its own method

                // create a new game of same difficulty

                game = new ShoppingMama(difficulty);

                // // create a new panel with the main game
                ShoppingMamaPanel newMain = new ShoppingMamaPanel(panelCont, cl, game);

                newMain.getTimer().start();

                // // set this to be the new main panel
                panelCont.add(newMain,"3");
                cl.show(panelCont, "3");

            } else {
                // terminate the system if exit button chosen (?: should we have it direct
                // to the home page?)
                System.exit(0);
            }
        }

    }
}

