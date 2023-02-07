package entities;

import main.Game;
import utils.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.Directions.*;
import static utils.Constants.Directions.DOWN;
import static utils.Constants.PlayerConstants.*;
import static utils.HelpMethods.*;

public class Player extends Entity {

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 20;
    private int playerAction = IDLE;
    // if we are not moving, then we are idle -1
    private boolean left, up, right,down;
    private boolean moving = false, attacking = false;
    private float playerSpeed = 2.0f;
    private int[][] lvlData;
    private float xDrawOffset = 6 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;
    public Player(float x, float y, int height, int width) {
        super(x, y, height, width);
        loadAnimations();
        initHitbox(x,y,20*Game.SCALE, 28 * Game.SCALE);
    }

    public void update(){
        updatePos();
        updateAnimationTick();
        setAnimation();

    }
    public void render(Graphics g){
        //calling super class(JPanel) paintComponent
        // do this first then we can paint, mr. JPanel
        g.drawImage(animations[playerAction][aniIndex], (int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset),width, height, null);
        drawHitbox(g);
    }
    private void loadAnimations() {


            BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
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
    public void loadLvlData(int[][] lvlData){
        this.lvlData = lvlData;
    }

    private void updateAnimationTick() {

        aniTick++;
        // if the tickrate is greater than or equal to the speed of our animation, reset our tickrate,
        // then swap our animation picture
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(playerAction)){
                aniIndex = 0;
                attacking = false;
            }

        }



    }
    private void setAnimation() {
        int startAni = playerAction;

        if(moving){
            playerAction = RUNNING;

        }
        else{
            playerAction = IDLE;
        }

        if(attacking){
            playerAction = ATTACK;
        }
        if(startAni != playerAction){
            resetAniTick();
        }
    }

    private void resetAniTick() {

        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {

        moving = false;
        if(!left && !right && !up && !down){
            return;
        }

        float xSpeed = 0, ySpeed = 0;

        if(left && !right){
            xSpeed  = -playerSpeed;
        }else if(right && !left){
            xSpeed = playerSpeed;
        }
        if(up && !down){
            ySpeed = -playerSpeed;
        }
        else if(down && !up){
            ySpeed = playerSpeed;
        }
        /*
        if(CanMoveHere(x+xSpeed,y+ySpeed, width, height,lvlData)){
            this.x+=xSpeed;
            this.y+=ySpeed;
            moving = true;
        }
        */
        if(CanMoveHere(hitbox.x+xSpeed,hitbox.y+ySpeed, hitbox.width, hitbox.height,lvlData)){
            hitbox.x+=xSpeed;
            hitbox.y+=ySpeed;
            moving = true;
        }
    }

    public void resetDirBooleans(){
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttacking(boolean attacking){
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
