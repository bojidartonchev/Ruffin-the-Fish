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

    private String about;
    private int cost;
    private int time;
    private Timer timer;
    private int currentTimerSeconds;

    public PowerUp(String about, int cost) {
        this.timer = new Timer();
        this.about = about;
        this.cost=cost;
    }

    public int getCost() {
        return this.cost;
    }

    public String getAbout() {
        return this.about;
    }

    public void setTime(int time){
        if(time<0){
            throw new IllegalArgumentException("Time must be positive value");
        }
        this.time=time/100;
    }

    public void applyEffect(Player player){
        this.startTimer(this.time);
    }

    private void startTimer(int sec){
        this.currentTimerSeconds = sec;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        currentTimerSeconds--;
                        System.out.println(time);
                    }
                });
            }
        }, 1000, 1000);
    }
}
