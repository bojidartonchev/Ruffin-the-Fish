package fishels.soft.fishels.ruffinthefish.Factory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.example.fishels.ruffinthefish.R;

import java.util.ArrayList;
import java.util.Random;

import fishels.soft.fishels.ruffinthefish.GameObjects.Event.Event;
import fishels.soft.fishels.ruffinthefish.GameObjects.Event.Goldfish;
import fishels.soft.fishels.ruffinthefish.GameObjects.Event.Jellyfish;

public class EventFactory {

    private static ArrayList<Bitmap> events;
    private static long spawnTimer;
    private static long nextSpawnIn;

    public static Event Create() {
        if(getIsReady()) {
            Event currentEvent = getRandomEvent();
            spawnTimer = System.nanoTime();
            nextSpawnIn = getNextSpawnTime();
            System.out.println("Next event in: "+nextSpawnIn);

            return currentEvent;
        }
        return null;
    }

    public static void loadContent(Context context){
        spawnTimer = System.nanoTime();
        nextSpawnIn = getNextSpawnTime();

        //load bitmaps
        events = new ArrayList<>();
        events.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.jelly));
        events.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.goldfish));
    }

    private static Event getRandomEvent(){
        Random rand = new Random();
        int current = rand.nextInt(10000);
        if(current%2==0){
            return new Jellyfish(events.get(0));
        }
        return new Goldfish(events.get(1));
    }

    private static boolean getIsReady() {
        long elapsedSpawnTime = (System.nanoTime() - spawnTimer) / 10000000;
        if(elapsedSpawnTime > nextSpawnIn)
        {
            return true;
        }
        return false;
    }

    private static long getNextSpawnTime(){
        Random rand = new Random();
        return rand.nextInt(6000)+1000;
    }

}
