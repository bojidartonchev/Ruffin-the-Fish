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

package fishels.soft.fishels.ruffinthefish.Entity;

import java.util.Random;

public class EnemySpeedGenerator {

    public EnemySpeedGenerator() {

    }
    //TODO remove hardcode values (calculate speed for every phone size

    public int generateXspeed(){
        Random rand = new Random();
        int minNumber = 4;
        int maxNumber = 20;
        int generater = rand.nextInt((maxNumber-minNumber)+minNumber)+minNumber;
        return generater;
    }

    public int generateYspeed(){
        Random rand = new Random();
        int minNumber = -2;
        int maxNumber = 2;
        int generater = rand.nextInt((maxNumber-minNumber)+minNumber)+minNumber;
        return generater;
    }

}
