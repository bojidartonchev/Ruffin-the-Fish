package fishels.soft.fishels.ruffinthefish.Core;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.fishels.ruffinthefish.R;

import java.util.ArrayList;

import fishels.soft.fishels.ruffinthefish.Entity.Background;
import fishels.soft.fishels.ruffinthefish.Entity.HUD;
import fishels.soft.fishels.ruffinthefish.Entity.Joystick;
import fishels.soft.fishels.ruffinthefish.Factory.EnemyFishFactory;
import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Enemy;
import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Player;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    private static int WIDTH;
    private static int HEIGHT;

    private MainThread thread;
    private Background bg;
    private HUD hud;
    private Player player;
    private ArrayList<Enemy> enemies;
    private Joystick joystick;

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
        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.background22));
        bg.setVector(-5);
        hud = new HUD(BitmapFactory.decodeResource(getResources(), R.drawable.hud));
        this.joystick = new Joystick(BitmapFactory.decodeResource(getResources(), R.drawable.inner),
                BitmapFactory.decodeResource(getResources(), R.drawable.outer));
        this.enemies = new ArrayList<>();
        this.player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.fishka));

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
                this.player.setSpeedX(joystick.calculateFishSpeedX());
                this.player.setSpeedY(joystick.calculateFishSpeedY());
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

        this.bg.update();
        this.player.update();
        this.hud.update(this.player.getScore());

        for (int i = 0; i < this.enemies.size(); i++) {
            Enemy currentEnemy = this.enemies.get(i);
            if(currentEnemy.isDead()){
                this.enemies.remove(i);
                continue;
            }
            currentEnemy.update();
            if (this.player.intersects(currentEnemy))
            {
                this.player.tryEat(currentEnemy);
            }

        }

        //add enemy fish if needed
        if(this.enemies.size() < 4) {
            this.enemies.add(EnemyFishFactory.Create(getContext()));
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
            this.player.draw(canvas);
            this.hud.draw(canvas);

            //draw enemies
            for(Enemy e: this.enemies)
            {
               e.draw(canvas);
            }
            this.joystick.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }

    private void setProportions(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        this.WIDTH = metrics.widthPixels;
        this.HEIGHT = metrics.heightPixels;
    }

}
