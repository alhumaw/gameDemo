package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
/*
The game panel allows us to create a "panel" inside of our frame, which we can draw on
When we extend JPanel, we inherit all classes from JPanel. Namely, paintComponent
paintComponent allows us to "paint" on our panel
We draw a rectangle :)
 */

public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img, subImg;


    public GamePanel() {

        mouseInputs = new MouseInputs(this);
        importImg();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/AnimationSheet_Character.png");
        try{
            img = ImageIO.read(is);
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
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
        subImg = img.getSubimage(1*32,1*32,32,32);
        g.drawImage(subImg, (int)xDelta, (int)yDelta,128, 80, null);


    }


}
