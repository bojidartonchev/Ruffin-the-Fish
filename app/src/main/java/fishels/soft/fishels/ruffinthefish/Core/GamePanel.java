package fishels.soft.fishels.ruffinthefish.Core;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.fishels.ruffinthefish.R;

import java.util.ArrayList;

import fishels.soft.fishels.ruffinthefish.Entity.Background;
import fishels.soft.fishels.ruffinthefish.Entity.Joystick;
import fishels.soft.fishels.ruffinthefish.Entity.ProgressBar;
import fishels.soft.fishels.ruffinthefish.Factory.EnemyFishFactory;
import fishels.soft.fishels.ruffinthefish.Factory.EventFactory;
import fishels.soft.fishels.ruffinthefish.GameObjects.Event.Event;
import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Enemy;
import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Player;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    private static int WIDTH;
    private static int HEIGHT;

    private MainThread thread;
    private SecondThread secThread;
    private Background bg;
    private Background bgFront;
    private Player player;
    private ProgressBar progres;
    private ArrayList<Enemy> enemies;
    private Joystick joystick;
    //private Event event;
    private boolean joystickLeft;
   // long startSpawnTime;
    // ... the code being measured ...

    public GamePanel(Context context)
    {
        super(context);
        setProportions(context);

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
            try{thread.setRunning(false);
                thread.join();
                secThread.setRunning(false);
                secThread.join();

            }catch(InterruptedException e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        EnemyFishFactory.LoadImages(getContext());
        this.bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.background));
        this.bg.setVector(-1);
        this.bgFront = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.frontground));
        this.bgFront.setVector(-5);
        this.joystickLeft = this.readSettings("left");
        this.joystick = new Joystick(BitmapFactory.decodeResource(getResources(), R.drawable.inner),
                BitmapFactory.decodeResource(getResources(), R.drawable.outer),this.joystickLeft);
        this.enemies = new ArrayList<>();
        this.player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.player));
        this.progres = new ProgressBar(BitmapFactory.decodeResource(getResources(),R.drawable.frame),
                BitmapFactory.decodeResource(getResources(), R.drawable.fillbar),this.player);
        this.initFish();

        //we can safely start the game loop
        this.thread = new MainThread(getHolder(), this);
        this.thread.setRunning(true);
        this.thread.setPriority(10);
        this.thread.start();

        //this.event = EventFactory.Create(getContext());
        //this.startSpawnTime = System.nanoTime();
        this.secThread= new SecondThread(this);
        this.secThread.setRunning(true);
        this.secThread.start();
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

        //this.spawnEventOnTime();
        this.bg.update();
        this.bgFront.update();
        this.player.update();
        this.progres.update(this.player.getScore());

        /*
        if(this.event != null) {
            if (!this.event.isOnScreen()) {
                this.event = null;
            }
            else {
                this.event.update();
                if(this.event.intersects(this.player)){
                    this.event.eat(this.player);
                }
            }

        }
        */

        for (int i = 0; i < this.enemies.size(); i++) {
            Enemy currentEnemy = this.enemies.get(i);
            if (currentEnemy.isDead()) {
                this.enemies.remove(i);
                continue;
            }

            currentEnemy.update();

            if (this.player.intersects(currentEnemy)) {
                this.player.tryEat(currentEnemy);
            }

            if (this.enemies.size() <= 10) {
                initFish();
            }
        }

    }
    /*
    private void spawnEventOnTime() {
        long elapsedSpawnTime = (System.nanoTime() - startSpawnTime) / 10000000;
        // TODO: make to spawn on random time
        if(elapsedSpawnTime > 2000)
        {
            this.startSpawnTime = System.nanoTime();
            this.event = EventFactory.Create(getContext());
        }
    }
    */

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
            this.progres.draw(canvas);

            //draw enemies
            for(Enemy e: this.enemies)
            {
               e.draw(canvas);
            }
            this.player.draw(canvas);
/*
            if(this.event != null) {
                this.event.draw(canvas);
            }
            */

            this.joystick.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }

    public void initFish() {
        //add enemy fish if needed
        while(this.enemies.size() <= 10){
            this.enemies.add(EnemyFishFactory.Create());
        }

    }

    private void setProportions(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        this.WIDTH = metrics.widthPixels;
        this.HEIGHT = metrics.heightPixels;
    }

    private boolean readSettings(String setting) {
        SharedPreferences prefs = getContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
        boolean current = prefs.getBoolean(setting, true); //true is the default value
        return current;
    }
}
