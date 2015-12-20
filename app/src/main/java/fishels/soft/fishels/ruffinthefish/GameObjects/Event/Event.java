package fishels.soft.fishels.ruffinthefish.GameObjects.Event;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.Entity.Animation;
import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Player;
import fishels.soft.fishels.ruffinthefish.GameObjects.GameObject;

public class Event extends GameObject {
    //constants
    private static final int EVENT_NUMROWS = 2;
    private static final int EVENT_NUMFRAMES = 8;
    private static final int EVENT_SPEED_X = 12;
    private static final int EVENT_STARTING_X = -120;

    //animation
    private final int numRows;
    private final int numFrames;
    private int currentAction;
    private boolean isKilling;

    private Bitmap spritesheet;
    private Animation animation = new Animation();

    // Animation actions
    private static final int SWIMMING = 0;
    private static final int KILLING = 1;

    //stats
    public int speedX = 0;
    private int speedY = 0;
    private boolean playing;
    //private boolean dead;
    private boolean onScreen;
    private long startTime;

    public Event(Bitmap res) {
        this.numRows = EVENT_NUMROWS;
        this.numFrames = EVENT_NUMFRAMES;

        this.height = res.getHeight() / this.numRows;
        this.width = res.getWidth() / this.numFrames;
        this.setX(EVENT_STARTING_X);
        this.setY(getRandomY());
        this.setSpeedX(EVENT_SPEED_X);

        this.setPlaying(true);
        this.setOnScreen(true);

        Bitmap[][] image = this.createBitmap(res);

        this.animation.setFrames(image);
        this.animation.setDelay(100);
        this.startTime = System.nanoTime();
    }

   //public void setDead(boolean dead) {
   //    this.dead = dead;
   //}

   //public boolean isDead() {
   //    return this.dead;
   //}

    public Animation getAnimation() {
        return animation;
    }

    public boolean isOnScreen() {
        return onScreen;
    }

    public void setOnScreen(boolean onScreen) {
        this.onScreen = onScreen;
    }

    public boolean isKilling() {
        return this.isKilling;
    }

    public void setIsKILLING(boolean isKILLING) {
        this.isKilling = isKILLING;
    }

    public int getCurrentAction() {
        return this.currentAction;
    }

    public void setCurrentAction(int currentAction) { this.currentAction = currentAction; }

    public int getSpeedX() { return speedX; }

    public void setSpeedX(int speedX) { this.speedX = speedX; }

    public int getSpeedY() { return speedY; }

    public void setSpeedY(int speedY) { this.speedY = speedY; }

    public void update() {
        long elapsed = (System.nanoTime() - this.startTime) / 1000000;
        if (elapsed > 100) {
            this.startTime = System.nanoTime();
        }

        this.animation.update();

        this.setX(this.x + this.speedX);
        this.setY(this.y + this.speedY);

        // check attack has stopped
        if (this.getCurrentAction() == this.KILLING) {
            if (this.getAnimation().playedOnce()) {
                this.setIsKILLING(false);
            }
        }

        if (this.isKilling()) {
            if (this.getCurrentAction() != this.KILLING) {
                this.setCurrentAction(this.KILLING);
                this.getAnimation().setCurrentAction(this.KILLING);
            }
        } else {
            if (this.getCurrentAction() != this.SWIMMING) {
                this.setCurrentAction(this.SWIMMING);
                this.getAnimation().setCurrentAction(this.SWIMMING);
            }
        }
    }

    @Override
    public void setX(int xCurrent) {
       if(x + this.getWidth() < -10 || x-this.getWidth() > GamePanel.getWIDTH() + 10) {
            this.setOnScreen(false);
            return;
        }
        super.setX(xCurrent);
    }

    public void eat(Player player){
        player.setDead(true);
        // TODO: Change activity to Game Over Activity...

    }

    private int getRandomY() {
        Random rand = new Random();
        int minimumY = this.getHeight() / 5;
        int randomY = rand.nextInt(GamePanel.getHEIGHT() - this.getHeight() * 2) + this.getHeight();

        if(randomY < minimumY){
            randomY = minimumY;
        }

        return randomY;
    }

    public void draw(Canvas canvas) {
        Bitmap currentFrame = this.animation.getImage();
        canvas.drawBitmap(currentFrame, this.x, this.y, null);
    }

    public void setPlaying(boolean b) {
        this.playing = b;
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

}