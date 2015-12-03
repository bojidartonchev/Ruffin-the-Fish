package fishels.soft.fishels.ruffinthefish.GameObjects.Fish;

import android.graphics.Bitmap;
import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.Enums.Level;

public class Player extends Fish {
    private static final int PLAYER_NUMROWS = 1;
    private static final int PLAYER_NUMFRAMES = 6;
    private static final long STARTING_PLAYER_SCORE = 0;
    private static final Level STARTING_PLAYER_LEVEL = Level.ONE;

    private long score;

    public Player(Bitmap res) {
        super(res, STARTING_PLAYER_LEVEL, PLAYER_NUMROWS, PLAYER_NUMFRAMES);
        this.setX(GamePanel.getWIDTH() / 2);
        this.setY(GamePanel.getHEIGHT() / 2);
        this.setScore(STARTING_PLAYER_SCORE);
    }


    public long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    // Checks if the player's level is bigger or equals to enemy's level.
    // And if is true the enemy is removed and player's points are increased.
    // Otherwise the hero dies and the game ends.
    public boolean tryEat(Fish enemy){
        if (this.getCurrentLevel().isBiggerThanOrEqual(enemy.getCurrentLevel()))
        {
            enemy.setDead(true);
            return true;
        }
        this.setDead(true);
        return false;
    }
}
