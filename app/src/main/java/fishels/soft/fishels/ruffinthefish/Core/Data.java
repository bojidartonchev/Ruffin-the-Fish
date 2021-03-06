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

package fishels.soft.fishels.ruffinthefish.Core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;

import com.example.fishels.ruffinthefish.R;

import java.util.ArrayList;

public class Data {
    private static ArrayList<Bitmap> images;

    //font
    private static Typeface font;

    //game images
    public static final int BACKGROUND = 0;
    public static final int FRONTGROUND = 1;
    public static final int JOYSTICK_INNER = 2;
    public static final int JOYSTICK_OUTER = 3;
    public static final int PROGRESS_FRAME = 4;
    public static final int PROGRESS_FILL = 5;
    public static final int PLAYER = 6;
    public static final int BUBBLE = 7;
    public static final int PATTERN = 8;
    public static final int PATTERN_GREEN = 16;
    public static final int POWER_UP_BTN = 9;
    //enemies
    public static final int LEVEL1_ENEMY = 10;
    public static final int LEVEL2_ENEMY = 11;
    public static final int LEVEL3_ENEMY = 12;
    public static final int LEVEL4_ENEMY = 13;
    //events
    public static final int JELLYFISH = 14;
    public static final int GOLDFISH = 15;

     public static void loadContent(Context context){
        images = new ArrayList<>();
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.background));
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.frontground));
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.inner));
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.outer));
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.frame));
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.fillbar));
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.player));
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.bubble));
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.pattern));
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.powerupbtn));
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.level1_enemy));
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.level2_enemy));
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.level3_enemy));
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.level4_enemy));
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.jelly));
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.goldfish));
        images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.patterngreen));

        //load fonts;
        font = Typeface.createFromAsset(context.getAssets(), "fonts/Grandstander.ttf");
    }

    public static Bitmap getImage(int current){
        return images.get(current);
    }

    public static Typeface getTypeFace(){
        return font;
    }


}
