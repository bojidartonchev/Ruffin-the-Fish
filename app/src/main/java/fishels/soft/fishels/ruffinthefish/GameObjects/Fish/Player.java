package fishels.soft.fishels.ruffinthefish.GameObjects.Fish;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import fishels.soft.fishels.ruffinthefish.Core.Activities.Game;
import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.Enums.Level;
import fishels.soft.fishels.ruffinthefish.Music.SoundManager;

public class Player extends Fish {
    private static final int PLAYER_NUMROWS = 5;
    private static final int PLAYER_NUMFRAMES = 8;
    private static final int STARTING_PLAYER_SCORE = 0;
    private static final Level STARTING_PLAYER_LEVEL = Level.ONE;
    private int score;
    private int shards;

    public Player(Bitmap res) {
        super(res, STARTING_PLAYER_LEVEL, PLAYER_NUMROWS, PLAYER_NUMFRAMES);
        this.updateBitmap();
        this.setX(GamePanel.getWIDTH() / 2);
        this.setY(GamePanel.getHEIGHT() / 2);
        this.setScore(STARTING_PLAYER_SCORE);
        this.loadShards();
    }

    @Override
    public void setX(int xCurrent) {
        if(xCurrent<0){
            xCurrent=0;
        }
        else if(xCurrent+this.getWidth()>GamePanel.getWIDTH()){
            xCurrent=GamePanel.getWIDTH()-this.getWidth();
        }
        super.setX(xCurrent);
    }

    @Override
    public void setY(int yCurrent) {
        if(yCurrent<0){
            yCurrent=0;
        }
        else if(yCurrent+this.getHeight()>GamePanel.getHEIGHT()){
            yCurrent=GamePanel.getHEIGHT()-this.getHeight();
        }
        super.setY(yCurrent);
    }

    public int getScore() {
        return score;
    }

    public void tryEat(Fish enemy) {
        if(this.isDead()){
            return;
        }
        if (this.getCurrentLevel().isBiggerThanOrEqual(enemy.getCurrentLevel())) {
            this.setIsEating(true);
            if(this.intersects(enemy,40,50)) {
                enemy.setDead(true);
                this.addScore( enemy.getCurrentLevel().getValue());
                SoundManager.playSound(SoundManager.EAT_SOUND);
            }
        }
        else {
            if(this.intersects(enemy,40,50)) {
                enemy.setIsEating(true);
                this.setDead(true);
                SoundManager.playSound(SoundManager.EAT_SOUND);
            }
        }
    }

    protected void addScore(int score){
        if(this.getGold()){
            score*=2;
        }
        this.setScore(this.getScore()+score);
    }

    private void setScore(int score) {
        if(score>=10+this.getCurrentLevel().getValue()*10){
            this.levelUp();
            this.updateBitmap();
            score=0;
        }
        this.score = score;
    }

    private void saveShards(){;
        SharedPreferences settings = Game.main.getApplicationContext().getSharedPreferences("shards", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("shards", this.getShards());
        editor.apply();
    }

    private void loadShards() {
        this.setShards(0);
        try {
            SharedPreferences sharedPref = Game.main.getSharedPreferences("shards", 0);
            this.setShards(sharedPref.getInt("shards", 0));
        } catch (NullPointerException ex){
            this.setShards(0);
        }
    }

    private void levelUp(){
        int currentLevel = this.getCurrentLevel().getValue();
        currentLevel+=1;
        Level newLevel;
        switch (currentLevel){
            case 2:
                newLevel=Level.TWO;
                break;

            case 3:
                newLevel=Level.THREE;
                break;

            case 4:
                newLevel=Level.FOUR;
                break;
            default:
                this.setShards(this.getShards()+1);
                this.saveShards();
                newLevel=Level.ONE;
                break;
        }
        System.out.println(this.getShards());
        this.setCurrentLevel(newLevel);
    }

    private void setShards(int count){
        this.shards = count;
    }

    private int getShards(){
        return shards;
    }
}
