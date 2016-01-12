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

import android.graphics.Bitmap;
import android.graphics.Canvas;

import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Player;

public class ProgressBar {

    private Bitmap frame;
    private Bitmap barImage;
    private Bitmap outputBar;
    private double scoreWidth;
    private Player player;
    private int scoreToLevelUp;

    public ProgressBar(Bitmap frame, Bitmap bar,Player player)
    {
        this.frame = Bitmap.createScaledBitmap(frame, GamePanel.getWIDTH(),frame.getHeight(),true);
        this.barImage= bar;
        this.player=player;
    }

    public void update(int score)
    {
        if(score==0){
            this.scoreToLevelUp = 10+this.player.getCurrentLevel().getValue()*10;
            this.scoreWidth = GamePanel.getWIDTH()/this.scoreToLevelUp;
        }
        int width = 1+(int) (score*this.scoreWidth);
        outputBar = Bitmap.createBitmap(barImage, 0, 0,
                width, barImage.getHeight());
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(outputBar, 0,0, null);
        canvas.drawBitmap(frame,0,0,null);
    }
}

