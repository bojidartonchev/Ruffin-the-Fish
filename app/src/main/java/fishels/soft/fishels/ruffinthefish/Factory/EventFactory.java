package fishels.soft.fishels.ruffinthefish.Factory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.example.fishels.ruffinthefish.R;
import fishels.soft.fishels.ruffinthefish.GameObjects.Event.Event;


public class EventFactory {

    public static Event Create(Context context) {
        Bitmap currentBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        return new Event(currentBitmap);
    }


    //TODO: add more events in future
    //private static Bitmap getRandomStyle(Bitmap currentBitmap){
    //    Random rand = new Random();
    //    int current = rand.nextInt(4);
    //    int height = currentBitmap.getHeight()/4;
    //    return Bitmap.createBitmap(currentBitmap, 0,current*height,currentBitmap.getWidth(), height);
    //}
}
