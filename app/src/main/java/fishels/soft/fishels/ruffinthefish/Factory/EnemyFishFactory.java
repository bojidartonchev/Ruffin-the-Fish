package fishels.soft.fishels.ruffinthefish.Factory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.fishels.ruffinthefish.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

import fishels.soft.fishels.ruffinthefish.Enums.Level;
import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Enemy;

public class EnemyFishFactory {

    private static int currentNumRows=0;
    private static int currentNumFrames=0;
    private static ArrayList<Bitmap> enemyFish;

    //values
    public static final int LEVEL_1 = 0;
    public static final int LEVEL_2 = 1;
    public static final int LEVEL_3 = 2;
    public static final int LEVEL_4 = 3;

    public static void LoadImages(Context context){
        enemyFish = new ArrayList<>();
        enemyFish.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.level1_enemy));
        enemyFish.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.level2_enemy));
        enemyFish.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.level3_enemy));
        enemyFish.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.level4_enemy));
    }
    public static Enemy Create() {
        Level currentLevel = getLevel();
        Bitmap currentBitmap = getBitmap(currentLevel);
        return new Enemy(currentBitmap, currentLevel, currentNumRows, currentNumFrames);
    }

    private static Bitmap getBitmap(Level currentLevel) {
        Bitmap current = null;
        int numOfAnimations=0;
        switch (currentLevel){
            case ONE:
                current=enemyFish.get(LEVEL_1);
                numOfAnimations=4;
                currentNumRows = 1;
                currentNumFrames = 5;
                break;
            case TWO:
                current=enemyFish.get(LEVEL_2);
                numOfAnimations=1;
                currentNumRows=2;
                currentNumFrames=9;
                break;
            case THREE:
                current=enemyFish.get(LEVEL_3);
                numOfAnimations=1;
                currentNumRows=2;
                currentNumFrames=7;
                break;
            case FOUR:
                current=enemyFish.get(LEVEL_4);
                numOfAnimations=1;
                currentNumRows=2;
                currentNumFrames=9;
                break;
        }
        if(numOfAnimations>1) {
            current = getRandomStyle(current, numOfAnimations);
        }
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
        else if(current%8==0){
            return Level.THREE;
        }
        else if(current % 6==0){
            return Level.TWO;
        }
        return Level.ONE;
    }


}
