package fishels.soft.fishels.ruffinthefish.GameObjects.Fish;

import android.graphics.Bitmap;
import fishels.soft.fishels.ruffinthefish.Core.GamePanel;

public class Player extends Fish {
    private static final int PLAYER_NUMROWS = 1;
    private static final int PLAYER_NUMFRAMES = 6;

    public Player(Bitmap res) {
        super(res, PLAYER_NUMROWS, PLAYER_NUMFRAMES);
        this.setX(GamePanel.getWIDTH() / 2);
        this.setY(GamePanel.getHEIGHT() / 2);
    }
}
