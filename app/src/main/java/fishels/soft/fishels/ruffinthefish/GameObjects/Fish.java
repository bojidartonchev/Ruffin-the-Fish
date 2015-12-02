package fishels.soft.fishels.ruffinthefish.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import fishels.soft.fishels.ruffinthefish.Entity.Animation;
import fishels.soft.fishels.ruffinthefish.Core.GamePanel;

import java.util.Random;

public class Fish extends GameObject {
    private final int numRows = 1;
    private final int numFrames = 6;
    private Bitmap spritesheet;
    private double dya;
    private boolean dead;
    private int speedX=0;
    private int speedY=0;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;

    public Fish(Bitmap res) {

        x = 300;
        y = getRandomY();
        dy = 0;
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

    private int getRandomY() {
        Random rand = new Random();
        return rand.nextInt((int) (GamePanel.HEIGHT-GamePanel.HEIGHT/2.7)) + GamePanel.HEIGHT/108;
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

    public void setDead(boolean b){
        dead = b;
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

    public boolean getPlaying(){return playing;}
    public void setPlaying(boolean b){playing = b;}
    public void resetDYA(){dya = 0;}

}
