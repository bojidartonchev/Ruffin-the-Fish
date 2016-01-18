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

import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Player;

public class Frenzy extends PowerUp {
    private static final String FRENZY_ABOUT = "Ruffin enrages for 8 seconds and can eat almost everything";
    private static final int FRENZY_COST = 8;

    public Frenzy() {
        super(FRENZY_ABOUT,FRENZY_COST);
    }

    @Override
    public void applyEffect(Player player) {

    }
}
