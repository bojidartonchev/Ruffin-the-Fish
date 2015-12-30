package fishels.soft.fishels.ruffinthefish.GameObjects.Fish;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import fishels.soft.fishels.ruffinthefish.Entity.Animation;
import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.Enums.Level;
import fishels.soft.fishels.ruffinthefish.GameObjects.GameObject;

import java.util.Random;

public abstract class Fish extends GameObject {
    //animation
    private int currentAction;
    private boolean isEating;
    private Animation animation = new Animation();

    // Animation actions
    private static final int SWIMMING = 0;
    private static final int EATING = 1;

    //stats
    private Level currentLevel;
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

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Animation getAnimation() {
        return animation;
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
        if(this.isDead()){
            return;
        }
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
        if(!this.isDead()) {
            Bitmap currentFrame = animation.getImage();
            if (!this.isTurnedRight()) {
                currentFrame = flipHorizontal(currentFrame);
            }
            canvas.drawBitmap(currentFrame, x, y, null);
        }
    }


}
