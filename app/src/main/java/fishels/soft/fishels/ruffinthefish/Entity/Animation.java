package fishels.soft.fishels.ruffinthefish.Entity;

import android.graphics.Bitmap;

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
            playedOnce = true;
        }
    }
    public Bitmap getImage(){
        return frames[currentAction][currentFrame];
    }

    public boolean playedOnce(){return playedOnce;}

    public void setCurrentAction(int currentAction) {
        this.currentFrame = 0;
        this.playedOnce = false;
        this.currentAction = currentAction;
    }
}
