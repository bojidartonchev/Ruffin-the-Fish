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

package fishels.soft.fishels.ruffinthefish.Core.Labels.Elements;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.view.MotionEvent;

import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Player;

public class GameButton {
    public Matrix btn_matrix;
    public RectF btn_rect;
    public float width;
    public float height;
    private Bitmap bg;
    private Paint paint;

    public GameButton(Bitmap bg,int x,int y){
        this.btn_matrix = new Matrix();
        this.width = bg.getWidth();
        this.height = bg.getHeight();
        this.bg = bg;

        this.btn_rect = new RectF(0, 0, width, height);
        y+=this.bg.getHeight()/2;
        this.setPosition(x,y);
    }

    public void setPosition(float x, float y){
        btn_matrix.setTranslate(x, y);
        btn_matrix.mapRect(btn_rect);
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bg, btn_matrix, paint);
    }

    public boolean onTouch(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN: {
                this.setFilter();
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL: {
                this.clearFilter();
                break;
            }
        }
        return true;
    }

    public void setFilter(){
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);

        this.paint = new Paint();
        paint.setColorFilter(filter);
    }

    public void clearFilter(){
        if(Player.getPowerUp().getInCooldown()){
            return;
        }
        this.paint = null;
    }
}
