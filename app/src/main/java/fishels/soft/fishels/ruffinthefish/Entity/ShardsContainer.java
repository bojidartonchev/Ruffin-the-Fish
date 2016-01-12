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

public class ShardsContainer {

    private static int shards;
    private static Context context;

    public static void save(){;
        SharedPreferences settings = context.getApplicationContext().getSharedPreferences("shards", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("shards", shards);
        editor.apply();
    }

    public static void load(Context ctx) {
        context = ctx;
        shards=0;
        try {
            SharedPreferences sharedPref = context.getSharedPreferences("shards", 0);
            shards = sharedPref.getInt("shards", 0);
        } catch (NullPointerException ex){
            shards=0;
        }
    }
    public static void add(int count){
        shards+=count;
        save();
    }

    public static void remove(int count){
        shards-=count;
        save();
    }

    public static int getShards(){
        return shards;
    }



}
