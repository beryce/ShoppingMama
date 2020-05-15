import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.util.Random;

public class ShoppingMamaPanel extends JPanel {
    protected int WIDTH, HEIGHT;

    private final JLabel 
    valWatermelon = new JLabel("1"), 
    valTangerine = new JLabel("3"), 
    valStrawberry = new JLabel("5"),
    valKiwi = new JLabel("7");

    private JLabel timerLabel, x1, x2, x3;
    private int score, numX;  
    private String level;
    private JPanel top, maze, list;
    private ImageIcon whiteX, redX; 
    private JButton newMaze, exit;

    private Timer timer;
    private Timer reshuffle;
    private static int TIMELIMIT, RESHUFFLE;

    public ShoppingMamaPanel(int row, int col, String levelOfDiff) {
        WIDTH = row;
        HEIGHT = col;

        level = levelOfDiff;
        score = numX = 0;

        //sets timer to 30 seconds
        TIMELIMIT = 30;
        //sets reshuffling interval to 10 seconds
        RESHUFFLE = 10;

        //creates timer for reshuffling board and time limit of game
        timer = new Timer(TIMELIMIT, new TimerListener());
        reshuffle = new Timer(RESHUFFLE, new TimerListener());

        //set main panel layout
        this.setLayout(new BorderLayout());

        //create panels
        top = new topPanel();
        maze = new MazePanel(WIDTH, HEIGHT);
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

        //starts timers
        timer.start();
        reshuffle.start();
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
            newMaze = new JButton("Draw New Maze");
            newMaze.addActionListener(new ButtonListener());
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
    private class itemsList extends JPanel {
        private JLabel numWatermelon, numTangerine, numStrawberry, numKiwi;
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

            JLabel l1 = new JLabel(i1);
            JLabel l2 = new JLabel(i2);
            JLabel l3 = new JLabel(i3);
            JLabel l4 = new JLabel(i4);

            numWatermelon = new JLabel("0");
            numTangerine = new JLabel("0");
            numStrawberry = new JLabel("0");
            numKiwi = new JLabel("0");

            //set JLabel font styling
            item.setFont(new Font("SansSerif", Font.PLAIN, 14));

            numCollected.setFont(new Font("SansSerif", Font.PLAIN, 14));
            ptsWorth.setFont(new Font("SansSerif", Font.PLAIN, 14));
            numWatermelon.setFont(new Font("SansSerif", Font.PLAIN, 22));
            numTangerine.setFont(new Font("SansSerif", Font.PLAIN, 22));
            numStrawberry.setFont(new Font("SansSerif", Font.PLAIN, 22));
            numKiwi.setFont(new Font("SansSerif", Font.PLAIN, 22));
            valWatermelon.setFont(new Font("SansSerif", Font.PLAIN, 22));
            valTangerine.setFont(new Font("SansSerif", Font.PLAIN, 22));
            valStrawberry.setFont(new Font("SansSerif", Font.PLAIN, 22));
            valKiwi.setFont(new Font("SansSerif", Font.PLAIN, 22));

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
        }
    }
    private class topPanel extends JPanel {
        public topPanel() {
            //set panel styling
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBackground(new Color(224, 255, 255));

            //create new JLabel to display level of difficulty
            JLabel levelLabel = new JLabel("Level: " + level);
            levelLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
            //level.setAlignmentX(Component.CENTER_ALIGNMENT);

            //create new JLabel to display current score
            JLabel scoreLabel = new JLabel("Score: " + score);
            scoreLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));

            //create sub panel to store and display 3 Xs
            XPanel xMarks = new XPanel();

            //create new JLabel to display timer
            timerLabel = new JLabel("00:15");
            timerLabel.setFont(new Font("SansSerif", Font.PLAIN, 36));

            //add elements to panel
            add(Box.createRigidArea(new Dimension(50, 0)));
            add(levelLabel);
            add(Box.createRigidArea(new Dimension(50, 0)));
            add(scoreLabel);
            add(Box.createRigidArea(new Dimension(50, 0)));
            add(xMarks);
            add(Box.createRigidArea(new Dimension(50, 0)));
            add(timerLabel);
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

    //not updated!!! 
    private class MazePanel extends JPanel {
        private ImageIcon shopper, cookie, path, wall, 
        watermelon, tangerine, strawberry, kiwi;
        private ImageIcon[][] icons; 
        private ArrayList<ImageIcon> imgIcons; 

        private int WIDTH, HEIGHT;
        private Random r = new Random(); //generate a random icon from list

        private Shopper s;

        private boolean move;

        public MazePanel(int row, int col) {
            WIDTH = row;
            HEIGHT = col;

            move = false;

            this.setLayout(new GridLayout(row,col));
            icons = new ImageIcon[row][col];
            imgIcons = new ArrayList<>();
            s = new Shopper();

            //create icons
            createIcons(); 
            addKeyListener(new DirectionListener());

            setBackground(Color.pink);
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
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

            //add icons to imgIcons list
            imgIcons.add(shopper);
            imgIcons.add(cookie);
            imgIcons.add(path);
            imgIcons.add(wall);
            imgIcons.add(watermelon);
            imgIcons.add(tangerine);
            imgIcons.add(strawberry);
            imgIcons.add(kiwi);
        }

        /**
         * Draws the item icons on the maze
         * 
         * @param Graphics g - object used to draw image on screen 
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            shopper.paintIcon(this, g, s.getX()*50, s.getY()*50);
            icons[s.getX()][s.getY()] = shopper;

            if(!move) {
                for(int i=0; i<WIDTH; i++) {
                    for(int j=0; j<HEIGHT; j++) {
                        if(icons[i][j] != shopper) {
                            int randImg = r.nextInt(imgIcons.size()-2)+2;
                            ImageIcon nextItem = imgIcons.get(randImg);
                            //System.out.println(nextItem);
                            icons[i][j] = nextItem; 
                            nextItem.paintIcon(this, g, i*50, j*50);
                        }
                    }
                }
            } else {
                for(int i=0; i<WIDTH; i++) {
                    for(int j=0; j<HEIGHT; j++) {
                        if(icons[i][j] != shopper) {
                            ImageIcon repaintItem = icons[i][j]; 
                            repaintItem.paintIcon(this, g, i*50, j*50);
                        }
                    }
                }
            }
        }

        /**
         * Represents the listener for keyboard activity
         */
        private class DirectionListener implements KeyListener {
            /**
             * Responds to the user pressing arrow keys by adjusting the 
             * image location of shopping bag image accordingly.
             */
            public void keyPressed(KeyEvent event) {
                switch(event.getKeyCode()) {
                    case KeyEvent.VK_UP:
                    if(!icons[s.getX()][s.getY()-1].equals(wall)) {
                        //if cell is not a wall, change icon at current location to 
                        //path and new location to shopping bag
                        s.move(0, -1); 

                        icons[s.getX()][s.getY()+1] = path;
                        icons[s.getX()][s.getY()] = shopper;

                        move = true;
                    }
                    break;

                    case KeyEvent.VK_DOWN:
                    if(!icons[s.getX()][s.getY()+1].equals(wall)) {
                        s.move(0, 1);

                        icons[s.getX()][s.getY()+1] = path;
                        icons[s.getX()][s.getY()] = shopper;

                        move = true;
                    }
                    break;
                    case KeyEvent.VK_LEFT:
                    if(!icons[s.getX()-1][s.getY()].equals(wall)) {
                        s.move(-1, 0);

                        icons[s.getX()+1][s.getY()] = path;
                        icons[s.getX()][s.getY()] = shopper;

                        move = true;
                    }
                    break;
                    case KeyEvent.VK_RIGHT:
                    if(!icons[s.getX()+1][s.getY()].equals(wall)) {
                        s.move(1, 0);

                        icons[s.getX()-1][s.getY()] = path;
                        icons[s.getX()][s.getY()] = shopper;

                        move = true;
                    }
                    break;
                }
                repaint();
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
            if(event.getSource() == reshuffle) {
                //reshuffle maze
                //repaint(); 

            } else {
                //quit game and direct to results panel
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
                
                System.out.println("This is a new maze");
                //start a new instance of ShoppingMamaPanel 
            } else {
                System.exit(0);
                //HomePanel home = new HomePanel();
                //add(home);
                //quit game and return to home panel
            }
        }
    }
}