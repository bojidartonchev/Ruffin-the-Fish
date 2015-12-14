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
    private int currentAction;
    private boolean isEating;

    private Bitmap spritesheet;
    private Animation animation = new Animation();

    // Animation actions
    private static final int SWIMMING = 0;
    private static final int EATING = 1;

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

        Bitmap[][] image = this.createBitmap(res);

        this.animation.setFrames(image);
        this.animation.setDelay(100);
        this.startTime = System.nanoTime();

    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isDead() {
        return this.dead;
    }

    public boolean isTurnedRight() {
        return turnedRight;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setSpeedX(int speedX) {
        if(speedX<0){
            this.turnedRight = false;
        }
        else if(speedX>0){
            this.turnedRight = true;
        }
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public boolean isEating() {
        return this.isEating;
    }

    public void setIsEating(boolean isEating) {
        this.isEating = isEating;
    }

    public int getCurrentAction() {
        return this.currentAction;
    }

    public void setCurrentAction(int currentAction) {
        this.currentAction = currentAction;
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

        // check attack has stopped
        if(this.getCurrentAction() == this.EATING) {
            if (this.getAnimation().playedOnce()) {
                this.setIsEating(false);
            }
        }

        if(this.isEating()){
            if(this.getCurrentAction() != this.EATING){
                this.setCurrentAction(this.EATING);
                this.getAnimation().setCurrentAction(this.EATING);
            }
        }

        else {
            if(this.getCurrentAction() != this.SWIMMING) {
                this.setCurrentAction(this.SWIMMING);
                this.getAnimation().setCurrentAction(this.SWIMMING);
            }
        }
    }

    public void draw(Canvas canvas)
    {
        Bitmap currentFrame = animation.getImage();
        if(!this.isTurnedRight()){
            currentFrame=flipHorizontal(currentFrame);
        }
        canvas.drawBitmap(currentFrame,x,y,null);
    }

    public void setPlaying(boolean b){
        this.playing = b;
    }

    private int getRandomY() {
        Random rand = new Random();
        return rand.nextInt(GamePanel.getHEIGHT()-this.getHeight()*2) + this.getHeight()*2;
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

    private Bitmap[][] createBitmap(Bitmap res){
        Bitmap[][] image = new Bitmap[this.numRows][this.numFrames];
        this.spritesheet = res;
        for (int j = 0; j < this.numRows; j++) {
            for (int i = 0; i < this.numFrames; i++) {
                image[j][i] = Bitmap.createBitmap(this.spritesheet, i * this.width, j * this.height, this.width, this.height);
            }
        }
        return image;
    }

    private Bitmap flipHorizontal(Bitmap d)
    {
        Matrix m = new Matrix();
        m.preScale(-1, 1);
        Bitmap dst = Bitmap.createBitmap(d, 0, 0, d.getWidth(), d.getHeight(), m, false);
        return dst;
    }

}
