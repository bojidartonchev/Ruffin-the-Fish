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

package fishels.soft.fishels.ruffinthefish.Entity;

import android.content.Context;
import android.content.SharedPreferences;

public class ScoreContainer {
    private static long highestScore;
    private static long currentScore;
    private static Context context;

    public static void loadHighestScore(Context ctx) {
        context=ctx;
        currentScore = 0;
        SharedPreferences prefs = context.getSharedPreferences("highestScore", Context.MODE_PRIVATE);
        long score = prefs.getLong("highscore", 0); //0 is the default value
        highestScore = score;
    }

    public static void saveNewHighestScore(){
        if(currentScore<=highestScore){
            return;
        }
        highestScore = currentScore;
        SharedPreferences keyValues = context.getSharedPreferences("highestScore", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = keyValues.edit();
        editor.putLong("highscore", highestScore);
        editor.commit();
    }

    public static void addGlobalScore(long score){
        //we can use system clock to make it count up;
        currentScore+=score;
    }

    public static long getCurrentScore(){
        return currentScore;
    }
}
