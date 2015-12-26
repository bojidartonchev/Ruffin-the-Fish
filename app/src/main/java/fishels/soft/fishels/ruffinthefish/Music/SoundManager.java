package fishels.soft.fishels.ruffinthefish.Music;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import com.example.fishels.ruffinthefish.R;

import java.util.ArrayList;

public class SoundManager {
    public static int EAT_SOUND = 0;

    private static ArrayList<Integer> sounds = new ArrayList<>();

    private static AudioAttributes attrs = new AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build();

    private static SoundPool sp = new SoundPool.Builder()
            .setMaxStreams(10)
            .setAudioAttributes(attrs)
            .build();

    public static void loadSounds(Context context){
        sounds.add(sp.load(context, R.raw.eat,1));
    }

    public static void playSound(int sound){
        int currentId = sounds.get(sound);
        sp.play(currentId,1,1,1,0,1.0f);
    }

    public static void release(){
        sp.release();
    }



}
