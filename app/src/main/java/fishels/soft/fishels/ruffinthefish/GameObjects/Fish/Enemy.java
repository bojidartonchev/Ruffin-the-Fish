package fishels.soft.fishels.ruffinthefish.GameObjects.Fish;

import android.graphics.Bitmap;

import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.Enums.Level;

public class Enemy extends Fish {
    private static final int ENEMY_NUMROWS = 1;
    private static final int ENEMY_NUMFRAMES = 6;
    // TODO : Create factories for each level fish

    public Enemy(Bitmap res, Level level) {
        super(res, level, ENEMY_NUMROWS, ENEMY_NUMFRAMES);
    }
}
