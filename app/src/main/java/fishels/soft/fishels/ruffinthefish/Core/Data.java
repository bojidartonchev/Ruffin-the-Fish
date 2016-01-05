package fishels.soft.fishels.ruffinthefish.Core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.fishels.ruffinthefish.R;

import java.util.ArrayList;

public class Data {
    private static ArrayList<Bitmap> images;

    //values
    public static final int BACKGROUND = 0;
    public static final int FRONTGROUND = 1;
    public static final int JOYSTICK_INNER = 2;
    public static final int JOYSTICK_OUTER = 3;
    public static final int PROGRESS_FRAME = 4;
    public static final int PROGRESS_FILL = 5;
    public static final int PLAYER = 6;


    public static void loadContent(Context context){
        images = new ArrayList<>();
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.background));
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.frontground));
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.inner));
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.outer));
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.frame));
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.fillbar));
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.player));
    }

    public static Bitmap getImage(int current){
        return images.get(current);
    }

}
