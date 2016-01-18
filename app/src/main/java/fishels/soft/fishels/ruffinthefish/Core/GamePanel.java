/*
 * Copyright © 2015 Ruffin the Fish
<<<<<<< Updated upstream
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
=======
>>>>>>> Stashed changes
 */

package fishels.soft.fishels.ruffinthefish.Core;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.util.ArrayList;

import fishels.soft.fishels.ruffinthefish.Core.Labels.GameOver;
import fishels.soft.fishels.ruffinthefish.Entity.Background;
import fishels.soft.fishels.ruffinthefish.Entity.Bubble;
import fishels.soft.fishels.ruffinthefish.Entity.Joystick;
import fishels.soft.fishels.ruffinthefish.Entity.ProgressBar;
import fishels.soft.fishels.ruffinthefish.Entity.ScoreContainer;
import fishels.soft.fishels.ruffinthefish.Entity.ShardsContainer;
import fishels.soft.fishels.ruffinthefish.Factory.BubbleFactory;
import fishels.soft.fishels.ruffinthefish.Factory.EnemyFishFactory;
import fishels.soft.fishels.ruffinthefish.Factory.EventFactory;
import fishels.soft.fishels.ruffinthefish.GameObjects.Event.Event;
import fishels.soft.fishels.ruffinthefish.GameObjects.Event.Goldfish;
import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Enemy;
import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Player;
import fishels.soft.fishels.ruffinthefish.Music.SoundManager;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    private static int WIDTH;
    private static int HEIGHT;

    private MainThread thread;
    private Background bg;
    private Background bgFront;
    private Player player;
    private ProgressBar progress;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bubble> bubbles;
    private Joystick joystick;
    private Event event;
    private boolean joystickLeft;
    private boolean gameOver;
    private boolean alreadyEnded;

    public GamePanel(Context context)
    {
        super(context);

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        //make gamePanel focusable so it can handle events
        setFocusable(true);
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry)
        {
            try{
                thread.setRunning(false);
                thread.join();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        this.bg = new Background(Data.getImage(Data.BACKGROUND));
        this.bg.setVector(-1);
        this.bgFront = new Background(Data.getImage(Data.FRONTGROUND));
        this.bgFront.setVector(-5);
        this.joystickLeft = this.readSettings("left");
        this.joystick = new Joystick(Data.getImage(Data.JOYSTICK_INNER),
                Data.getImage(Data.JOYSTICK_OUTER),this.joystickLeft);
        this.enemies = new ArrayList<>();
        this.bubbles = new ArrayList<>();
        this.initPlayerFeatures();
        this.initFish();

        //we can safely start the game loop
        this.thread = new MainThread(getHolder(), this);
        this.thread.setRunning(true);
        this.thread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()&MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                if(this.gameOver){
                    GameOver.onTouch(event);
                }
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                joystick.onTouch(event);
                this.player.setSpeedX(joystick.calculateFishSpeedX());
                this.player.setSpeedY(joystick.calculateFishSpeedY());
                break;
            }
            case MotionEvent.ACTION_UP:{
                if(this.gameOver){
                    if(GameOver.onTouch(event)==1){
                        this.initPlayerFeatures();
                    }
                    else if(GameOver.onTouch(event)==2){
                        if(ShardsContainer.getShards()>=3) {
                            ShardsContainer.remove(3);
                            this.setGameOver(false);
                            this.player.setDead(false);
                            this.alreadyEnded = false;
                        }else{
                            Toast.makeText(this.getContext(), "Not enough shards", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                this.player.setSpeedX(0);
                this.player.setSpeedY(0);
                joystick.resetPosition();
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    public void update() {
        this.bg.update();
        this.bgFront.update();
        this.player.update();
        this.progress.update(this.player.getScore());

        if(this.event != null) {
            if (!this.event.isOnScreen()) {
                this.event =null;
            }
            else {
                this.event.update();
                if(this.event.intersects(this.player,40,50)){
                    this.event.executeEvent(this.player);
                    if(this.event.getClass()== Goldfish.class){
                        this.event=null;
                    }
                }
            }
        }
        else{
            this.event=EventFactory.Create();
        }

        for (int i = 0; i < this.bubbles.size(); i++) {
            Bubble currentBubble = this.bubbles.get(i);
            if(currentBubble.isOutsideScreen()){
                this.bubbles.remove(i);
            }
            currentBubble.update();
        }
        for (int i = 0; i < this.enemies.size(); i++) {
            Enemy currentEnemy = this.enemies.get(i);
            if (currentEnemy.isDead()) {
                this.enemies.remove(i);
                continue;
            }

            currentEnemy.update();

            if (this.player.intersects(currentEnemy,120,50)) {
                this.player.tryEat(currentEnemy);
            }
        }

        if (this.enemies.size() <= 10) {
            initFish();
        }

        if(this.player.isDead()&&!this.alreadyEnded){
            this.alreadyEnded=true;
            Thread thr = new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(2000);
                    setGameOver(true);
                    SoundManager.playSound(SoundManager.GAME_OVER);
                }
            });
            thr.start();
        }
    }

    @Override
    public void draw(Canvas canvas)
    {
        final float scaleFactorX = getWidth()/(WIDTH*1.f);
        final float scaleFactorY = getHeight()/(HEIGHT*1.f);
        if(canvas!=null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            this.bg.draw(canvas);
            this.bgFront.draw(canvas);
            this.progress.draw(canvas);

            //draw bubbles
            for(Bubble b: this.bubbles){
                b.draw(canvas);
            }
            //draw enemies
            for(Enemy e: this.enemies)
            {
                e.draw(canvas);
            }
            this.player.draw(canvas);

            if(this.event != null) {
                this.event.draw(canvas);
            }

            if(!this.gameOver){
                this.joystick.draw(canvas);
            }
            else {
                GameOver.draw(canvas);
            }
            canvas.restoreToCount(savedState);
        }
    }

    public void initFish() {
        this.enemies.add(EnemyFishFactory.Create());
    }

    public void initBubbles() {
        this.bubbles.add(BubbleFactory.Create());
    }

    public static void setProportions(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        WIDTH = metrics.widthPixels;
        HEIGHT = metrics.heightPixels;
    }

    private boolean readSettings(String setting) {
        SharedPreferences prefs = getContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
        boolean current = prefs.getBoolean(setting, true); //true is the default value
        return current;
    }

    private void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    private void initPlayerFeatures(){
        this.player = new Player(Data.getImage(Data.PLAYER));
        this.progress = new ProgressBar(Data.getImage(Data.PROGRESS_FRAME),
                Data.getImage(Data.PROGRESS_FILL),this.player);
        this.setGameOver(false);
        this.alreadyEnded=false;
        ScoreContainer.resetGlobalScore();
    }
}
