package fishels.soft.fishels.ruffinthefish.Core;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.fishels.ruffinthefish.R;

import java.util.ArrayList;

import fishels.soft.fishels.ruffinthefish.Entity.Background;
import fishels.soft.fishels.ruffinthefish.Entity.Joystick;
import fishels.soft.fishels.ruffinthefish.Factories.LevelZeroFishFactory;
import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Enemy;
import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Player;
import fishels.soft.fishels.ruffinthefish.GameObjects.GameObject;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    private static int WIDTH;
    private static int HEIGHT;

    private MainThread thread;
    private Background bg;
    private Player player;
    private ArrayList<Enemy> enemys;
    private Joystick joystick;
    private long enemyStartTime;

    public GamePanel(Context context)
    {
        super(context);
        setProportions(context);

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

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
            try{thread.setRunning(false);
                thread.join();

            }catch(InterruptedException e){e.printStackTrace();}
            retry = false;
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.background));
        bg.setVector(-5);
        this.joystick = new Joystick(BitmapFactory.decodeResource(getResources(), R.drawable.inner),
                BitmapFactory.decodeResource(getResources(), R.drawable.outer));
        this.enemys = new ArrayList<>();
        this.enemyStartTime = System.nanoTime();
        this.player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.fish5));

        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()&MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                joystick.onTouch(event);
                this.player.setSpeedX(joystick.generateFishPositionX());
                this.player.setSpeedY(joystick.generateFishPositionY());
                break;
            }
            case MotionEvent.ACTION_UP:{
                this.player.setSpeedX(0);
                this.player.setSpeedY(0);
                joystick.resetPosition();
                return true;
            }

        }
        return super.onTouchEvent(event);
    }

    public void update() {

        // TODO : Add "GAME OVER" inscription if hero is dead

        this.bg.update();
        this.player.update();

        long enemyElapsed = (System.nanoTime() - this.enemyStartTime) / 1000000;
        //TODO : check if the spawn time is good
        //add enemy fish on time if the fishs are not too many
        if(this.enemys.size() < 2) {
            this.enemys.add(LevelZeroFishFactory.Create(getContext()));
        }

        //loop through every missile and check collision
        for (int i = 0; i < this.enemys.size(); i++) {
            if (collision(this.player, this.enemys.get(i)))
            {
                // Update every enemy
                this.enemys.get(i).update();

                // Checks if the player's level is bigger or equals to enemy's level.
                // And if is true the enemy is removed and player's points are increased.
                // Otherwise the hero dies and the game ends.
                if (this.player.getCurrentLevel().isBiggerThanOrEqual(this.enemys.get(i).getCurrentLevel()))
                {
                    System.out.println("EAT THAT FISH");
                    this.enemys.remove(i);
                    this.player.setScore(this.player.getScore() + 10);
                }
                else
                {
                    this.player.setDead(true);
                    this.thread.setRunning(false);
                    System.out.println("GAME OVER");
                }
            }
        }
        //reset the timer
        this.enemyStartTime = System.nanoTime();

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
            this.player.draw(canvas);
            this.joystick.draw(canvas);
            canvas.restoreToCount(savedState);

            //draw enemys
            for(Enemy e: this.enemys)
            {
               e.draw(canvas);
            }
        }
    }

    private boolean collision(GameObject obj1, GameObject obj2){
        if(Rect.intersects(obj1.getRectangle(), obj2.getRectangle())){
            return true;
        }
        return false;
    }
    private void setProportions(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        this.WIDTH = metrics.widthPixels;
        this.HEIGHT = metrics.heightPixels;
    }

}
