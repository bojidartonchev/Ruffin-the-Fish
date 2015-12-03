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
