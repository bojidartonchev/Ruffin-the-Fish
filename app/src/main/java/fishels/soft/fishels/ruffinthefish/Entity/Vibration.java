package fishels.soft.fishels.ruffinthefish.Entity;

import android.content.Context;
import android.os.Vibrator;

public class Vibration {
    private static Vibrator vibrator;

    public static void loadVibrator(Context ctx){
        vibrator = (Vibrator) ctx.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public static void vibrate(long millisecs){
        vibrator.vibrate(millisecs);
    }

}
