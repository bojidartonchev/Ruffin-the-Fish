package fishels.soft.fishels.ruffinthefish.GameObjects.Fish;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import fishels.soft.fishels.ruffinthefish.Core.Game;
import fishels.soft.fishels.ruffinthefish.Entity.Animation;
import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.GameObjects.GameObject;

import java.util.Random;

public abstract class Fish extends GameObject {
    //animation
    private final int numRows;
    private final int numFrames;
    private Bitmap spritesheet;
    private Animation animation = new Animation();

    //stats
    private boolean dead;
    private int speedX=0;
    private int speedY=0;
    private boolean playing;

    private long startTime;

    public Fish(Bitmap res,int numRows,int numFrames) {
        this.numRows = numRows;
        this.numFrames = numFrames;
        this.setX(this.getRandomX());
        this.setY(this.getRandomY());

        this.height = res.getHeight()/this.numRows;
        this.width = res.getWidth()/this.numFrames;
        this.setPlaying(true);

        Bitmap[][] image = new Bitmap[numRows][numFrames];
        spritesheet = res;
        for (int j = 0; j < numRows; j++) {
            for (int i = 0; i < numFrames; i++) {
                image[j][i] = Bitmap.createBitmap(spritesheet, i * width, j*height, width, height);
            }
        }

        animation.setFrames(image);
        animation.setDelay(1);
        startTime = System.nanoTime();

    }

    public boolean isDead() {

        return dead;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public void update()
    {
        long elapsed = (System.nanoTime()-startTime)/1000000;
        if(elapsed>100)
        {
            startTime = System.nanoTime();
        }
        animation.update();

        x+=this.speedX;
        y+=this.speedY;
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(),x,y,null);
    }

    public void setPlaying(boolean b){
        playing = b;
    }

    private int getRandomY() {
        Random rand = new Random();
        return rand.nextInt((int) (GamePanel.getHEIGHT()-GamePanel.getHEIGHT()/2.7)) + GamePanel.getHEIGHT()/108;
    }

    private int getRandomX() {
        Random rand = new Random();
        int generater = rand.nextInt(2);
        if(generater!=0){
            return GamePanel.getWIDTH();
        }
        return generater;
    }

}
