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

package fishels.soft.fishels.ruffinthefish.GameObjects.PowerUps;

import java.util.Timer;
import java.util.TimerTask;

import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Player;

import static com.google.android.gms.internal.zzir.runOnUiThread;

public abstract class PowerUp {
    public final static int DEFAULT_COOLDOWN_TIME = 60;

    private String about;
    private boolean inCooldown;
    private int cost;
    private int time;
    private Timer timer;
    private int currentTimerSeconds;
    private boolean currentTimerIsCooldown;

    public PowerUp(String about, int cost) {
        this.about = about;
        this.cost=cost;
        this.inCooldown = false;
        this.timer = new Timer();
    }

    public int getCost() {
        return this.cost;
    }

    public String getAbout() {
        return this.about;
    }

    public boolean isCurrentTimerIsCooldown() {
        return currentTimerIsCooldown;
    }

    public String getCurrentTimerSeconds(){
        return Integer.toString(this.currentTimerSeconds);
    }

    public void setTime(int time){
        if(time<0){
            throw new IllegalArgumentException("Time must be positive value");
        }
        this.time=time/1000;
    }

    public void applyEffect(Player player){
        this.setInCooldown(true);
        this.startTimer(this.time,false);
    }

    public void setInCooldown(boolean inCd){
        this.inCooldown = inCd;
    }

    public boolean getInCooldown(){
        return this.inCooldown;
    }

    public void startTimer(int sec,boolean isCooldownTimer){
        this.currentTimerSeconds = sec;
        this.currentTimerIsCooldown = isCooldownTimer;
        this.timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tick();
                    }
                });
            }
        }, 1000, 1000);
    }

    private void tick(){
        currentTimerSeconds--;
        if(currentTimerSeconds<=0){
            this.timer.cancel();
            if(this.currentTimerIsCooldown){
                this.setInCooldown(false);
            }
        }
    }
}
