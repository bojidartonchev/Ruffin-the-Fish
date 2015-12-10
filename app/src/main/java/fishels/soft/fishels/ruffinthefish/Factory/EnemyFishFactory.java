package fishels.soft.fishels.ruffinthefish.Factory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.fishels.ruffinthefish.R;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

import fishels.soft.fishels.ruffinthefish.Enums.Level;
import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Enemy;

public class EnemyFishFactory {

    public static Enemy Create(Context context) {
        Level currentLevel = getLevel();
        Bitmap currentBitmap = getBitmap(currentLevel,context);
        return new Enemy(currentBitmap, Level.ONE);
        //TODO set currentLevel in the constructor above when bigger sprites are ready!
    }

    private static Bitmap getBitmap(Level currentLevel, Context context) {
        Bitmap current = null;
        switch (currentLevel){
            case ONE:
                current=BitmapFactory.decodeResource(context
                        .getResources(), R.drawable.level1_enemy);
                break;
            //TODO impement
        }
        current = getRandomStyle(current);
        return current;
    }

    private static Bitmap getRandomStyle(Bitmap currentBitmap){
        Random rand = new Random();
        int current = rand.nextInt(4);
        int height = currentBitmap.getHeight()/4;
        return Bitmap.createBitmap(currentBitmap, 0,current*height,currentBitmap.getWidth(), height);
    }

    private static Level getLevel(){
        Random rand = new Random();
        int current = rand.nextInt(10000);
        if(current%10==0){
            return Level.FOUR;
        }
        else if(current%5==0){
            return Level.THREE;
        }
        else if(current % 4==0){
            return Level.TWO;
        }
        return Level.ONE;
    }


}
