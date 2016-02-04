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
import android.os.SystemClock;

import fishels.soft.fishels.ruffinthefish.Entity.ScoreContainer;
import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Player;

public class Jellyfish extends Event {
    private static final int JELLYFISH_NUMROWS = 1;
    private static final int JELLYFISH_NUMFRAMES = 9;

    public Jellyfish(Bitmap res) {
        super(res, JELLYFISH_NUMROWS, JELLYFISH_NUMFRAMES);
    }

    @Override
    public void executeEvent(final Player player) {
        if(player.isStunned()||player.isPowerUpActivated()){
            return;
        }
        ScoreContainer.addGlobalScore(-528);
        player.setStunned(true);
        Thread thr = new Thread(new Runnable() {
        @Override
        public void run() {
            SystemClock.sleep(3000);
            player.setStunned(false);
        }
        });
        thr.start();
    }

}
