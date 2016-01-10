package fishels.soft.fishels.ruffinthefish.GameObjects.Event;

import android.graphics.Bitmap;
import android.os.SystemClock;

import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Player;
import fishels.soft.fishels.ruffinthefish.Music.SoundManager;

public class Goldfish extends Event {
    private static final int GOLDFISH_NUMROWS = 1;
    private static final int GOLDFISH_NUMFRAMES = 6;

    public Goldfish(Bitmap res) {
        super(res, GOLDFISH_NUMROWS, GOLDFISH_NUMFRAMES);
    }

    @Override
    public void executeEvent(final Player player) {
        SoundManager.playSound(SoundManager.EAT_GOLD);
        player.setGold(true);
        player.setCurrentAction(-1);
        Thread thr = new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(8000);
                player.setGold(false);
                player.setCurrentAction(-1);
            }
        });
        thr.start();
    }
}
