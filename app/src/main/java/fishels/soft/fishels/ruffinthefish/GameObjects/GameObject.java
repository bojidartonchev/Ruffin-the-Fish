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

package fishels.soft.fishels.ruffinthefish.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import java.util.Random;

import fishels.soft.fishels.ruffinthefish.Core.GamePanel;

public abstract class GameObject {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    protected Bitmap spritesheet;
    protected int numRows;
    protected int numFrames;

    protected boolean turnedRight=true;
    protected int speedX = 0;
    protected int speedY = 0;
    protected int directionMultiplier;
    private boolean playing;

    public void setX(int x)
    {
        if(x<0){
           this.directionMultiplier = 1;
        }
        else {
            this.directionMultiplier = -1;
        }
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public void setPlaying(boolean b){
        this.playing = b;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public void setSpeedX(int speedX) {
        if(speedX<0){
            this.turnedRight = false;
        }
        else if(speedX>0){
            this.turnedRight = true;
        }
        this.speedX = speedX;
    }

    public boolean isTurnedRight() {
        return turnedRight;
    }


    public boolean intersects(GameObject obj2, int percentX, int percentY){
        int playerCenterX = x+getWidth()/2;
        int playerCenterY = y+getHeight()/2;
        int enemyCenterX = obj2.x+obj2.getHeight()/2;
        int enemyCenterY = obj2.y+obj2.getHeight()/2;
        if((Math.abs(playerCenterX-enemyCenterX)<=percentX*getWidth()/100 && Math.abs(playerCenterY-enemyCenterY)<=percentY*getHeight()/100)){
            return true;
        }
        return false;
    }

    protected Bitmap[][] createBitmap(Bitmap res){
        Bitmap[][] image = new Bitmap[this.numRows][this.numFrames];
        this.spritesheet = res;
        for (int j = 0; j < this.numRows; j++) {
            for (int i = 0; i < this.numFrames; i++) {
                image[j][i] = Bitmap.createBitmap(this.spritesheet, i * this.width, j * this.height, this.width, this.height);
            }
        }
        return image;
    }

    protected Bitmap flipHorizontal(Bitmap d)
    {
        Matrix m = new Matrix();
        m.preScale(-1, 1);
        Bitmap dst = Bitmap.createBitmap(d, 0, 0, d.getWidth(), d.getHeight(), m, false);
        return dst;
    }

    protected int getRandomX() {
        Random rand = new Random();
        int minNumber = 0;
        int maxNumber = 10000;
        int generated = rand.nextInt((maxNumber-minNumber)+minNumber)+minNumber;

        if(generated%2==0){
            return (GamePanel.getWIDTH()+this.getWidth());
        }
        return -(this.getWidth());
    }

    protected int getRandomY() {
        Random rand = new Random();
        int minNumber =this.getHeight()/2;
        int maxNumber = GamePanel.getHEIGHT()-this.getHeight()-this.getHeight()/2;

        return rand.nextInt((maxNumber-minNumber)+minNumber)+minNumber;
    }

}

