package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/*
The game panel allows us to create a "panel" inside of our frame, which we can draw on
When we extend JPanel, we inherit all classes from JPanel. Namely, paintComponent
paintComponent allows us to "paint" on our panel
We draw a rectangle :)
 */

public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private float xDir = 1f, yDir = 1f;
    private int frames = 0;
    private long lastCheck = 0;
    private Color color = new Color(150,20,90);
    private Random random;
    public GamePanel() {
        random = new Random();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void changeXDelta(int value){
        this.xDelta += value;

    }

    public void changeYDelta(int value){
        this.yDelta += value;

    }

    public void setRectPos(int x, int y){
        this.xDelta = x;
        this.yDelta = y;
        repaint();
    }

    /*
    paintcomponent:
        called whenever we start the game
    graphics:
        allows us to draw on the panel
     */
    public void paintComponent(Graphics g){
        //calling super class(JPanel) paintComponent
        // do this first then we can paint, mr. JPanel
        super.paintComponent(g);
        updateRectangle();
        g.setColor(color);
        g.fillRect((int)xDelta, (int)yDelta, 200, 50);




    }

    private void updateRectangle() {
        xDelta+= xDir;
        if(xDelta > 400 || xDelta < 0){
            // if it goes over our width, it moves the other way
            xDir *= -1;
            color = getRndColor();
        }
        yDelta+= yDir;
        if(yDelta > 400 || yDelta < 0){
            // if it goes over our width, it moves the other way
            yDir*=-1;
            color = getRndColor();
        }
    }

    private Color getRndColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);


        return new Color(r,g,b);
    }
}
