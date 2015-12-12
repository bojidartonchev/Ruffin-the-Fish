package fishels.soft.fishels.ruffinthefish.GameObjects.Fish;

import android.graphics.Bitmap;

import java.util.Random;

import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.Entity.EnemySpeedGenerator;
import fishels.soft.fishels.ruffinthefish.Enums.Level;

public class Enemy extends Fish {
    private static final int ENEMY_NUMROWS = 1;
    private static final int ENEMY_NUMFRAMES = 5;
    private int directionMultiplier;
    private EnemySpeedGenerator speedGen = new EnemySpeedGenerator();

    public Enemy(Bitmap res, Level level) {
        super(res, level, ENEMY_NUMROWS, ENEMY_NUMFRAMES);
        this.directionMultiplier = generateDirection();
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
        }
        super.setX(x);
    }

    @Override
    public int getY() {
        return super.getY();
    }

    private int generateDirection(){
        if(this.getX()<0){
            return 1;
        }
        return -1;
    }

    @Override
    public void reset() {
        super.reset();
        this.setSpeedX(directionMultiplier* speedGen.generateXspeed());
        this.setSpeedY(speedGen.generateYspeed());
    }
}
