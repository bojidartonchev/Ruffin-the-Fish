package fishels.soft.fishels.ruffinthefish.Music;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import com.example.fishels.ruffinthefish.R;

import java.util.ArrayList;

public class SoundManager {
    //values
    public static int EAT_SOUND = 0;
    public static int EAT_GOLD = 1;
    public static int LEVELUP = 2;
    public static int GAME_OVER = 3;

    private static boolean soundOn;
    private static ArrayList<Integer> sounds = new ArrayList<>();
    private static SoundPool sp;

    public static void loadSounds(Context context){
        sounds.add(sp.load(context, R.raw.eat, 1));
        sounds.add(sp.load(context, R.raw.eatgold, 1));
        sounds.add(sp.load(context, R.raw.levelup, 1));
        sounds.add(sp.load(context, R.raw.gameover, 1));
    }

    public static void playSound(int sound){
        if(soundOn){
            int currentId = sounds.get(sound);
            sp.play(currentId, 1, 1, 1, 0, 1.0f);
        }
    }

    public static void release(){
        sp.release();
    }
    
    public static void loadSoundManager(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            createNewSoundPool();
        }else{
            createOldSoundPool();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected static void createNewSoundPool(){
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        sp = new SoundPool.Builder()
                .setMaxStreams(10)
                .setAudioAttributes(attributes)
                .build();
    }

    @SuppressWarnings("deprecation")
    protected static void createOldSoundPool(){
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
    }

    public static void setSoundOn(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences("settings", Context.MODE_PRIVATE);
        boolean current = prefs.getBoolean("sound", true); //true is the default value
        soundOn = current;
    }


}
