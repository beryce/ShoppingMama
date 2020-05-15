import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;

public class testPanel extends JPanel {
    private ImageIcon shopper, cookie, path, wall, 
    watermelon, tangerine, strawberry, kiwi;
    private ImageIcon[][] icons; 
    private ArrayList<ImageIcon> imgIcons; 

    private int WIDTH, HEIGHT;
    private Random r = new Random(); //generate a random icon from list

    private Shopper s;

    private boolean move;

    public testPanel(int row, int col) {
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

