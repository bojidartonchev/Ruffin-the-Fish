/*
 * Copyright © 2015 Ruffin the Fish
 */

package fishels.soft.fishels.ruffinthefish.Factory;

import java.util.Random;

import fishels.soft.fishels.ruffinthefish.Core.Data;
import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.Entity.Bubble;

public class BubbleFactory {

    public static Bubble Create(){
        Bubble bubble = new Bubble(Data.getImage(Data.BUBBLE),getRandomX(), GamePanel.getHEIGHT(), getRandomSpeed());
        return bubble;
    }

    private static int getRandomX(){
        Random rnd = new Random();
        int x = rnd.nextInt(GamePanel.getWIDTH());
        return x;
    }
    private static int getRandomSpeed(){
        int speed;
        Random rnd = new Random();
        speed = rnd.nextInt(10)+5;
        return speed;
    }
}
