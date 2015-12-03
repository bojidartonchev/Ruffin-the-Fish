package fishels.soft.fishels.ruffinthefish.GameObjects.Fish;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;

import fishels.soft.fishels.ruffinthefish.Core.Game;
import fishels.soft.fishels.ruffinthefish.Entity.Animation;
import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.Enums.Level;
import fishels.soft.fishels.ruffinthefish.GameObjects.GameObject;

import java.util.Random;

public abstract class Fish extends GameObject {
    //animation
    private final int numRows;
    private final int numFrames;
    private Bitmap spritesheet;
    private Animation animation = new Animation();

    //stats
    private Level currentLevel;
    public int speedX = 0;
    private int speedY = 0;
    private boolean playing;
    private boolean turnedRight=true;
    private boolean dead;

    private long startTime;

    public Fish(Bitmap res, Level level, int numRows, int numFrames) {
        this.numRows = numRows;
        this.numFrames = numFrames;
        this.setCurrentLevel(level);

        this.height = res.getHeight()/this.numRows;
        this.width = res.getWidth()/this.numFrames;
        this.setX(this.getRandomX());
        this.setY(this.getRandomY());

        this.setPlaying(true);
        this.setDead(false);

        Bitmap[][] image = new Bitmap[numRows][numFrames];
        spritesheet = res;
        for (int j = 0; j < numRows; j++) {
            for (int i = 0; i < numFrames; i++) {
                image[j][i] = Bitmap.createBitmap(spritesheet, i * width, j*height, width, height);
            }
        }

        animation.setFrames(image);
        animation.setDelay(10);
        startTime = System.nanoTime();

    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isDead() {
        return this.dead;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }


    public void setSpeedX(int speedX) {
        if(speedX<0){
            this.turnedRight=false;
        }
        else if(speedX>0){
            this.turnedRight=true;
        }
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

        this.setX(x + this.speedX);
        this.setY(y + this.speedY);
    }

    public void draw(Canvas canvas)
    {
        Bitmap currentFrame = animation.getImage();
        if(!turnedRight){
            currentFrame=flipHorizontal(currentFrame);
        }
        canvas.drawBitmap(currentFrame,x,y,null);
    }

    public void setPlaying(boolean b){
        this.playing = b;
    }

    private int getRandomY() {
        Random rand = new Random();
        return rand.nextInt((int) (GamePanel.getHEIGHT()-GamePanel.getHEIGHT()/2.7)) + GamePanel.getHEIGHT()/108;
    }

    private int getRandomX() {
        Random rand = new Random();
        int minNumber = 0;
        int maxNumber = 10000;
        int generated = rand.nextInt((maxNumber-minNumber)+minNumber)+minNumber;

        if(generated%2==0){
            return (GamePanel.getWIDTH()+this.getWidth());
        }
        return -(this.getWidth());
    }


    private Bitmap flipHorizontal(Bitmap d)
    {
        Matrix m = new Matrix();
        m.preScale(-1, 1);
        Bitmap dst = Bitmap.createBitmap(d, 0, 0, d.getWidth(), d.getHeight(), m, false);
        return dst;
    }

}
