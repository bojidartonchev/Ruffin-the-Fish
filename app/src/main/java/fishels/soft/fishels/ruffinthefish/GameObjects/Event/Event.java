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

package fishels.soft.fishels.ruffinthefish.GameObjects.Event;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.Entity.Animation;
import fishels.soft.fishels.ruffinthefish.Entity.EnemySpeedGenerator;
import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Player;
import fishels.soft.fishels.ruffinthefish.GameObjects.GameObject;

public abstract class Event extends GameObject {

    private EnemySpeedGenerator speedGen = new EnemySpeedGenerator();

    //animation
    private Animation animation = new Animation();

    //private boolean dead;
    private boolean onScreen;
    private long startTime;

    public Event(Bitmap res,int numRows, int numFrames) {
        this.numRows = numRows;
        this.numFrames = numFrames;

        this.height = res.getHeight() / this.numRows;
        this.width = res.getWidth() / this.numFrames;
        this.setX(this.getRandomX());
        this.setY(this.getRandomY());
        this.setSpeedX(directionMultiplier * speedGen.generateXspeed());
        this.setSpeedY(speedGen.generateYspeed());

        this.setPlaying(true);
        this.setOnScreen(true);

        Bitmap[][] image = this.createBitmap(res);

        this.animation.setFrames(image);
        this.animation.setDelay(100);
        this.startTime = System.nanoTime();
    }

    public boolean isOnScreen() {
        return this.onScreen;
    }

    public void setOnScreen(boolean onScreen) {
        this.onScreen = onScreen;
    }

    public void update() {
        long elapsed = (System.nanoTime() - this.startTime) / 1000000;
        if (elapsed > 100) {
            this.startTime = System.nanoTime();
        }

        this.animation.update();

        this.setX(this.x + this.speedX);
        this.setY(this.y + this.speedY);
    }

    @Override
    public void setX(int x) {
       if(x + this.getWidth() < -500 || x-this.getWidth() > GamePanel.getWIDTH() + 500) {
            this.setOnScreen(false);
            return;
       }
        super.setX(x);
    }

    public abstract void executeEvent(Player player);

    public void draw(Canvas canvas)
    {
        if(this.isOnScreen()) {
            Bitmap currentFrame = animation.getImage();
            if (!this.isTurnedRight()) {
                currentFrame = flipHorizontal(currentFrame);
            }
            canvas.drawBitmap(currentFrame, x, y, null);
        }
    }

}