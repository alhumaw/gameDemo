package main;

import javax.swing.*;
/*
This game window class is how we open up our GUI.
We utilize JFrame to open the GUI
The reason we create a GamePanel parameter inside of our GameWindow is so that
We force gamePanel to draw on our GameWindow every time we create a GameWindow object
 */
public class GameWindow {
    private JFrame Jframe;
    public GameWindow(GamePanel gamePanel){
        Jframe = new JFrame();

        Jframe.setSize(400,400);
        Jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Jframe.add(gamePanel);
        Jframe.setLocationRelativeTo(null);
        //THIS ALWAYS NEEDS TO BE AT THE BOTTOM
        Jframe.setVisible(true);

    }
}
