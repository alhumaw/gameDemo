package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
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
    private Game game;


    public GamePanel(Game game) {

        mouseInputs = new MouseInputs(this);
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println("size: " + GAME_WIDTH + " : " + GAME_HEIGHT);
    }


    public void paintComponent(Graphics g){
        //calling super class(JPanel) paintComponent
        // do this first then we can paint, mr. JPanel
        super.paintComponent(g);
        game.render(g);
    }
    public Game getGame(){
        return game;
    }


}
