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

    private static int currentNumRows=0;
    private static int currentNumFrames=0;

    public static Enemy Create(Context context) {
        Level currentLevel = getLevel();
        Bitmap currentBitmap = getBitmap(currentLevel,context);
        return new Enemy(currentBitmap, currentLevel, currentNumRows, currentNumFrames);
    }

    private static Bitmap getBitmap(Level currentLevel, Context context) {
        Bitmap current = null;
        int numOfAnimations=0;
        switch (currentLevel){
            case ONE:
                current=BitmapFactory.decodeResource(context
                        .getResources(), R.drawable.level1_enemy);
                numOfAnimations=4;
                currentNumRows = 1;
                currentNumFrames = 5;
                break;
            case TWO:
                current=BitmapFactory.decodeResource(context
                        .getResources(), R.drawable.evilfaashe);
                numOfAnimations=1;
                currentNumRows=2;
                currentNumFrames=9;
                break;
            case THREE:
                current=BitmapFactory.decodeResource(context
                        .getResources(), R.drawable.evilfaashe);
                numOfAnimations=1;
                currentNumRows=2;
                currentNumFrames=9;
                break;
            case FOUR:
                current=BitmapFactory.decodeResource(context
                        .getResources(), R.drawable.evilfaashe);
                numOfAnimations=1;
                currentNumRows=2;
                currentNumFrames=9;
                break;
        }
        current = getRandomStyle(current,numOfAnimations);
        return current;
    }

    private static Bitmap getRandomStyle(Bitmap currentBitmap, int numOfAnimations){
        Random rand = new Random();
        int current = rand.nextInt(numOfAnimations);
        int height = currentBitmap.getHeight()/numOfAnimations;
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
