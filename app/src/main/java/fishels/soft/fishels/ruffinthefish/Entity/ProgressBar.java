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
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Matrix;

import com.example.fishels.ruffinthefish.R;

import fishels.soft.fishels.ruffinthefish.Core.Data;
import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Player;

public class ProgressBar {

    private Bitmap frame;
    private Bitmap barImage;
    private Bitmap outputBar;
    private Bitmap pattern;
    private double scoreWidth;
    private Player player;
    private int scoreToLevelUp;


    public ProgressBar(Bitmap frame, Bitmap bar,Player player)
    {
        this.frame = Bitmap.createScaledBitmap(frame, GamePanel.getWIDTH(),frame.getHeight(),true);
        this.barImage= bar;
        this.player=player;
        this.pattern= Data.getImage(Data.PATTERN);
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

    private Paint applyFilter(){

        Paint pnt = new Paint();
        pnt.setTextSize(70);
        int[] rainbow = getRainbowColors();
        /*Shader shader = new LinearGradient(0, 0, 400, 500, rainbow,
                null, Shader.TileMode.MIRROR);
        Matrix matrix = new Matrix();
        matrix.setRotate(90);
        shader.setLocalMatrix(matrix);
        pnt.setShader(shader);
        */
        Shader shader = new BitmapShader(barImage,
                Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        pnt.setShader(shader);


        float radius = pnt.getTextSize() / 10;
        BlurMaskFilter filter = new BlurMaskFilter(radius,  BlurMaskFilter.Blur.SOLID);
        pnt.setMaskFilter(filter);
        return pnt;
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(outputBar, 0, 0, null);
        canvas.drawBitmap(frame, 0, 0, null);
        canvas.drawText("TESTTEXT", 500, 500, this.applyFilter());
    }

    private int[] getRainbowColors() {
        return new int[] {
                Color.RED,
                Color.BLUE,
                Color.GREEN,
                Color.CYAN,
                Color.YELLOW
        };
    }
}


