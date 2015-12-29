package fishels.soft.fishels.ruffinthefish.GameObjects.Fish;

import android.graphics.Bitmap;

import java.util.Random;

import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.Entity.EnemySpeedGenerator;
import fishels.soft.fishels.ruffinthefish.Enums.Level;

public class Enemy extends Fish {
    private EnemySpeedGenerator speedGen = new EnemySpeedGenerator();

    public Enemy(Bitmap res, Level level,int numRows, int numFrames) {
        super(res, level, numRows, numFrames);
        this.setSpeedX(directionMultiplier* speedGen.generateXspeed());
        this.setSpeedY(speedGen.generateYspeed());
        this.setPlaying(true);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void setX(int x) {
        if(x+this.getWidth()<-10||x-this.getWidth()>GamePanel.getWIDTH()+10){
            this.setDead(true);
            return;
        }
        super.setX(x);
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public void setSpeedX(int speedX) {
        speedX=(int)(speedX*(this.getCurrentLevel().getValue()*0.5));
        super.setSpeedX(speedX);
    }



}
