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

import android.os.SystemClock;

import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Player;

public class MultiScore extends PowerUp {
    private static final String MULTISCORE_ABOUT = "Multiplies all the score Ruffin gains for 10 seconds.";
    private static final int MULTISCORE_COST = 6;
    private static final int MULTISCORE_TIME = 10000;

    public MultiScore() {
        super(MULTISCORE_ABOUT, MULTISCORE_COST);
        this.setTime(MULTISCORE_TIME);
    }

    @Override
    public void applyEffect(final Player player) {
        super.applyEffect(player);
        player.setMultiScore(true);
        Thread thr = new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(MULTISCORE_TIME);
                player.setMultiScore(false);
                startTimer(PowerUp.DEFAULT_COOLDOWN_TIME,true);
            }
        });
        thr.start();
    }
}
