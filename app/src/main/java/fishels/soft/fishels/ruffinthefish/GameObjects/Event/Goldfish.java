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
import fishels.soft.fishels.ruffinthefish.Music.SoundManager;

public class Goldfish extends Event {
    private static final int GOLDFISH_NUMROWS = 1;
    private static final int GOLDFISH_NUMFRAMES = 6;

    public Goldfish(Bitmap res) {
        super(res, GOLDFISH_NUMROWS, GOLDFISH_NUMFRAMES);
    }

    @Override
    public void executeEvent(final Player player) {
        SoundManager.playSound(SoundManager.EAT_GOLD);
        ScoreContainer.addGlobalScore(500);
        player.setGold(true);
        player.setCurrentAction(-1);
        Thread thr = new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(8000);
                player.setGold(false);
                player.setCurrentAction(-1);
            }
        });
        thr.start();
    }
}
