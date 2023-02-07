package main;
/*
This is where we will have our calls to open our GUI and draw on it
 */

import entities.Player;
import levels.LevelManager;

import java.awt.*;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Player player;
    private LevelManager levelManager;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    public Game(){
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        //input focus for keystrokes
        gamePanel.requestFocus();

        startGameLoop();


    }

    private void initClasses() {
        levelManager = new LevelManager(this);
        player = new Player(200,200,(int)(32*SCALE), (int)(32*SCALE));
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());

    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    public void update(){
        player.update();
        levelManager.update();
    }

    public void render(Graphics g){
        levelManager.draw(g);
        player.render(g);

    }

    @Override
    public void run() {
        //nano seconds. Creating a game loop here
        double timePerFrame = 1000000000.0/FPS_SET;
        double timePerUpdate = 1000000000.0/UPS_SET;

        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;
        //infinite loop
        while(true){
            /*
            We check for the previous frame until now, if that duration passes we refresh the game screen
            by using repaint

            if now minus the last time we had an update of the frame,
            is that more or equal to the duration we want to display the frame
             */
            long currentTime = System.nanoTime();
            /*
            We need a way to update our game state without losing frames. It's a way that we can catch up to our gamestate
            deltaU stores every single update, until it hits 1 then calls update() to update all the frames
            Without this, we would have a ton of lost events
             */
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime =currentTime;
            if(deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }
            if(deltaF>= 1){
                gamePanel.repaint();
                frames++;
                deltaF--;
            }


            /*
        This is an FPS counter
         */

        /*
        if now minus the last time we checked is more than 1 second,
        print out the FPS, then reset frames
         */
            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;

            }

        }
    }

    public Player getPlayer(){
        return player;
    }

    public void windowFocusLost(){
        player.resetDirBooleans();
    }
}
