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
import android.graphics.Canvas;
import android.view.MotionEvent;

import fishels.soft.fishels.ruffinthefish.Core.Data;
import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.Core.Labels.Elements.GameButton;

public class PowerUp {
    private GameButton usePowerUpBtn;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isLeft;
    private boolean usePowerUpBtnPressed;

    public PowerUp(boolean left) {
        this.usePowerUpBtnPressed = false;
        Bitmap currentBtnImage = Data.getImage(Data.POWER_UP_BTN);
        this.width = currentBtnImage.getWidth();
        this.height = currentBtnImage.getHeight();
        this.y = GamePanel.getHEIGHT()-this.height*2;
        this.setIsLeft(left);
        this.usePowerUpBtn = new GameButton(currentBtnImage,this.x,this.y);

    }

    public void draw(Canvas canvas)
    {
        this.usePowerUpBtn.draw(canvas);
    }

    public int onTouch(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (this.usePowerUpBtn.btn_rect.contains(x, y)||(event.getActionMasked()== MotionEvent.ACTION_UP&&usePowerUpBtnPressed))
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
}
