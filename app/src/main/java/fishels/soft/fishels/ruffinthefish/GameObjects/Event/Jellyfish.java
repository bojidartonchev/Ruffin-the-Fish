package fishels.soft.fishels.ruffinthefish.GameObjects.Event;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.SystemClock;

import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Player;

public class Jellyfish extends Event {
    private static final int JELLYFISH_NUMROWS = 1;
    private static final int JELLYFISH_NUMFRAMES = 9;

    public Jellyfish(Bitmap res) {
        super(res, JELLYFISH_NUMROWS, JELLYFISH_NUMFRAMES);
    }

    @Override
    public void executeEvent(final Player player) {
        player.setStunned(true);
    Thread thr = new Thread(new Runnable() {
        @Override
        public void run() {
            SystemClock.sleep(2000);
            player.setStunned(false);
        }
    });
        thr.start();
    }

}
