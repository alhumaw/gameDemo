package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;
/*
The game panel allows us to create a "panel" inside of our frame, which we can draw on
When we extend JPanel, we inherit all classes from JPanel. Namely, paintComponent
paintComponent allows us to "paint" on our panel
We draw a rectangle :)
 */

public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    // tracking where our player is
    private float xDelta = 100, yDelta = 100;
    // importing an image
    private BufferedImage img;
    // empty animation array
    private BufferedImage[][] animations;
    // need to measure index, tickrate, and speed of player
    private int aniTick, aniIndex, aniSpeed = 20;

    private int playerAction = IDLE;
    // if we are not moving, then we are idle -1
    private int playerDir = -1;
    private boolean moving = false;


    public GamePanel() {

        mouseInputs = new MouseInputs(this);
        importImg();
        loadAnimations();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimations() {
        animations = new BufferedImage[9][8];


    /*
    array to loop through our idle animation
    we only have 2 idle pictures, so our array is of size 2
    if we want to reference them, we choose either idleAni[0] or idleAni[1]
     */
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/AnimationSheet_Character.png");
        try{
            img = ImageIO.read(is);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                is.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
    }
    // setter for moving up, down, left, right based on key movements
    public void setDirection(int direction){
        this.playerDir = direction;
        moving = true;
    }
    // ability to stop
    public void setMoving(boolean moving){
        this.moving = moving;
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
        updateAnimationTick();
        setAnimation();
        updatePos();
        requestFocus(true);
        g.drawImage(animations[playerAction][aniIndex], (int)xDelta, (int)yDelta,256, 160, null);


    }
    // this method will allow us to update the position of the character
    private void updatePos() {
        if(moving){
            switch(playerDir){
                case LEFT:
                    xDelta -=5;
                    break;
                case RIGHT:
                    xDelta+=5;
                    break;
                case UP:
                    yDelta-=5;
                    break;
                case DOWN:
                    yDelta+=5;
                    break;
            }
        }
    }
    // This will allow us to swap our character between moving and idle based on keyboard inputs
    private void setAnimation() {
        if(moving){
            playerAction = RUNNING;

        }
        else{
            playerAction = IDLE;
        }
    }

    /*
    This is how our animation updates

     */
    private void updateAnimationTick() {

        aniTick++;
        // if the tickrate is greater than or equal to the speed of our animation, reset our tickrate,
        // then swap our animation picture
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(playerAction)){
                aniIndex = 0;
            }
        }


    }


}
