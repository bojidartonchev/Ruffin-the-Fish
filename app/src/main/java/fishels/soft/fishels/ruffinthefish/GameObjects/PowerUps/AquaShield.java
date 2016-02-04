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

public class AquaShield extends PowerUp {
    private static final String SHIELD_ABOUT = "A magic aqua shield will protect Ruffin for 12 seconds";
    private static final int SHIELD_COST = 7;

    public AquaShield() {
        super(SHIELD_ABOUT,SHIELD_COST);
    }

    @Override
    public void applyEffect(final Player player) {
        player.setPowerUpActivated(true);
        player.setAquaShielded(true);
        player.setCurrentAction(-1);
        Thread thr = new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(12000);
                player.setAquaShielded(false);
                player.setPowerUpActivated(false);
                player.setCurrentAction(-1);
            }
        });
        thr.start();
    }
}
