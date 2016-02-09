/*
 * Copyright © 2015 Ruffin the Fish
 *
 * This file is part of "Ruffin the Fish".
 *
 * "Ruffin the Fish" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "Ruffin the Fish" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with "Ruffin the Fish".  If not, see <http://www.gnu.org/licenses/>.
 */

package fishels.soft.fishels.ruffinthefish.Factory;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Random;

import fishels.soft.fishels.ruffinthefish.Core.Data;
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

    public static void LoadImages(){
        enemyFish = new ArrayList<>();
        enemyFish.add(Data.getImage(Data.LEVEL1_ENEMY));
        enemyFish.add(Data.getImage(Data.LEVEL2_ENEMY));
        enemyFish.add(Data.getImage(Data.LEVEL3_ENEMY));
        enemyFish.add(Data.getImage(Data.LEVEL4_ENEMY));
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
                currentNumFrames=9;
                break;
            case FOUR:
                current=enemyFish.get(LEVEL_4);
                numOfAnimations=1;
                currentNumRows=2;
                currentNumFrames=7;
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
