package main;
/*
This is where we will have our calls to open our GUI and draw on it
 */

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    public Game(){

        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        //input focus for keystrokes
        gamePanel.requestFocus();
        startGameLoop();


    }
    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {
        //nano seconds. Creating a game loop here
        double timePerFrame = 1000000000.0/FPS_SET;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();
        int frames = 0;
        long lastCheck = System.currentTimeMillis();
        //infinite loop
        while(true){
            /*
            We check for the previous frame until now, if that duration passes we refresh the game screen
            by using repaint

            if now minus the last time we had an update of the frame,
            is that more or equal to the duration we want to display the frame
             */
            now = System.nanoTime();
            if(now - lastFrame >= timePerFrame){
                gamePanel.repaint();
                lastFrame = now;
                frames++;
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
                System.out.println("FPS: " + frames);
                frames = 0;

            }

        }
    }
}
