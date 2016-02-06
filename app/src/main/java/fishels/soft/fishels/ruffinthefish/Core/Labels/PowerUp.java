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

package fishels.soft.fishels.ruffinthefish.Core.Labels;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;

import fishels.soft.fishels.ruffinthefish.Core.Data;
import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.Core.Labels.Elements.GameButton;
import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Player;

public class PowerUp {
    private GameButton usePowerUpBtn;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isLeft;
    private boolean usePowerUpBtnPressed;

    //text
    private int textSize;
    private Paint fillPnt;
    private Paint strokePnt;
    private Bitmap pattern;

    public PowerUp(boolean left) {
        this.usePowerUpBtnPressed = false;
        Bitmap currentBtnImage = Data.getImage(Data.POWER_UP_BTN);
        this.width = currentBtnImage.getWidth();
        this.height = currentBtnImage.getHeight();
        this.y = GamePanel.getHEIGHT()-this.height*2;
        this.setIsLeft(left);
        this.usePowerUpBtn = new GameButton(currentBtnImage,this.x,this.y);

        this.textSize = GamePanel.getWIDTH()/32;
        this.pattern= Data.getImage(Data.PATTERN);
        this.fillPnt = this.getFillPaint();
        this.strokePnt = this.getStrokePaint();

        this.fillPnt.setTypeface(Data.getTypeFace());
        this.strokePnt.setTypeface(Data.getTypeFace());

    }

    public void draw(Canvas canvas)
    {
        this.usePowerUpBtn.draw(canvas);
        if(Player.getPowerUp().getInCooldown()){
            this.drawStrokedText(Player.getPowerUp().getCurrentTimerSeconds(),canvas);
            this.usePowerUpBtn.setFilter();
        }
        else if(!usePowerUpBtnPressed) {
            this.usePowerUpBtn.clearFilter();
        }
    }

    public int onTouch(MotionEvent event) {
        if(Player.getPowerUp().getInCooldown()){
            return 0;
        }
        int index = MotionEventCompat.getActionIndex(event);
        int x;
        int y;

        if (event.getPointerCount() > 1) {
            // The coordinates of the current screen contact, relative to
            // the responding View or Activity.
            x = (int)MotionEventCompat.getX(event, index);
            y = (int)MotionEventCompat.getY(event, index);

        } else {
            // Single touch event
            x = (int)MotionEventCompat.getX(event, index);
            y = (int)MotionEventCompat.getY(event, index);
        }

        if (this.usePowerUpBtn.btn_rect.contains(x, y)||((event.getActionMasked()== MotionEvent.ACTION_UP||event.getActionMasked()== MotionEvent.ACTION_POINTER_UP)&&usePowerUpBtnPressed))
        {
            this.usePowerUpBtnPressed = !this.usePowerUpBtnPressed;
            this.usePowerUpBtn.onTouch(event);
            return 1;
        }
        return 0;
    }

    private void setIsLeft(boolean isLeft) {
        this.isLeft = isLeft;
        if(isLeft==false){
            //right
            this.x =GamePanel.getWIDTH()-this.width-this.width/2;
            return;
        }
        //left
        this.x = this.width/2;
    }

    private Paint getFillPaint(){
        Paint pnt = new Paint();
        pnt.setTextSize(this.textSize);

        Shader shader = new BitmapShader(pattern,
                Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        pnt.setShader(shader);

        return pnt;
    }

    private Paint getStrokePaint(){
        Paint pnt = new Paint();
        pnt.setTextSize(this.textSize);
        pnt.setStyle(Paint.Style.STROKE);
        pnt.setStrokeWidth(2);
        pnt.setColor(Color.BLACK);

        return pnt;
    }

    private void drawStrokedText(String text, Canvas canvas){
        Rect r = new Rect();
        this.strokePnt.setTextAlign(Paint.Align.LEFT);
        this.strokePnt.getTextBounds(text, 0, text.length(), r);
        this.fillPnt.setTextAlign(Paint.Align.LEFT);
        this.fillPnt.getTextBounds(text, 0, text.length(), r);
        float curx = this.width / 2f - r.width() / 2f - r.left;
        float cury = this.height / 1.5f + r.height()/2 - r.top;
        canvas.drawText(text, this.x+curx, this.y + cury, this.strokePnt);
        canvas.drawText(text, this.x+curx, this.y + cury, this.fillPnt);
    }
}
