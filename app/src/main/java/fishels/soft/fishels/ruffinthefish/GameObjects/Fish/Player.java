package fishels.soft.fishels.ruffinthefish.GameObjects.Fish;

import android.graphics.Bitmap;
import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.Entity.Animation;
import fishels.soft.fishels.ruffinthefish.Enums.Level;

public class Player extends Fish {
    private static final int PLAYER_NUMROWS = 2;
    private static final int PLAYER_NUMFRAMES = 8;
    private static final int STARTING_PLAYER_SCORE = 0;
    private static final Level STARTING_PLAYER_LEVEL = Level.ONE;

    private int score;

    public Player(Bitmap res) {
        super(res, STARTING_PLAYER_LEVEL, PLAYER_NUMROWS, PLAYER_NUMFRAMES);
        this.setX(GamePanel.getWIDTH() / 2);
        this.setY(GamePanel.getHEIGHT() / 2);
        this.setScore(STARTING_PLAYER_SCORE);
    }

    @Override
    public void setX(int xCurrent) {
        if(xCurrent<0){
            xCurrent=0;
        }
        else if(xCurrent+this.getWidth()>GamePanel.getWIDTH()){
            xCurrent=GamePanel.getWIDTH()-this.getWidth();
        }
        super.setX(xCurrent);
    }

    @Override
    public void setY(int yCurrent) {
        if(yCurrent<0){
            yCurrent=0;
        }
        else if(yCurrent+this.getHeight()>GamePanel.getHEIGHT()){
            yCurrent=GamePanel.getHEIGHT()-this.getHeight();
        }
        super.setY(yCurrent);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void tryEat(Fish enemy) {
        if (this.getCurrentLevel().isBiggerThanOrEqual(enemy.getCurrentLevel())) {
            this.setIsEating(true);
            enemy.setDead(true);
            this.setScore(this.getScore() + enemy.getCurrentLevel().getValue());
        }
        else {
            enemy.setIsEating(true);
            this.setDead(true);
        }
    }
}
