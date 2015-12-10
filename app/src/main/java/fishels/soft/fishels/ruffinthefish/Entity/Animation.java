package fishels.soft.fishels.ruffinthefish.Entity;

import android.graphics.Bitmap;

import java.util.Random;

public class Animation {
    private Bitmap[][] frames;
    private int currentFrame;
    private int currentAction;
    private long startTime;
    private long delay;
    private boolean playedOnce;
    private int numOfFrames;

    public void setFrames(Bitmap[][] frames)
    {
        this.frames = frames;
        this.setFrame(0);
        currentAction=0;
        startTime = System.nanoTime();
        this.numOfFrames = this.frames[0].length;
    }
    public void setDelay(long d){delay = d;}
    public void setFrame(int i){currentFrame= i;}
    public void setCurrentAction(int j) {currentAction=j;}

    public void update()
    {
        long elapsed = (System.nanoTime()-startTime)/1000000;

        if(elapsed>delay)
        {
            currentFrame++;
            startTime = System.nanoTime();
        }
        if(currentFrame >= numOfFrames){
            currentFrame = 0;
            //currentAction++;
            playedOnce = true;
        }
        if(currentAction>=frames.length){
            currentAction=0;
        }
    }
    public Bitmap getImage(){
        return frames[currentAction][currentFrame];
    }
    public int getFrame(){return currentFrame;}
    public boolean playedOnce(){return playedOnce;}
}