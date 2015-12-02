package fishels.soft.fishels.ruffinthefish.Core;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.fishels.ruffinthefish.R;

import fishels.soft.fishels.ruffinthefish.Entity.Background;
import fishels.soft.fishels.ruffinthefish.Entity.Joystick;
import fishels.soft.fishels.ruffinthefish.GameObjects.Fish;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    private static int WIDTH;
    private static int HEIGHT;

    private MainThread thread;
    private Background bg;
    private Fish ruffin;
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

        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.background));
        bg.setVector(-5);
        this.joystick = new Joystick(BitmapFactory.decodeResource(getResources(), R.drawable.inner),
                BitmapFactory.decodeResource(getResources(), R.drawable.outer));

        this.ruffin = new Fish(BitmapFactory.decodeResource(getResources(), R.drawable.fish5));
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
                this.ruffin.setSpeedX(joystick.generateFishPositionX());
                this.ruffin.setSpeedY(joystick.generateFishPositionY());
                break;
            }
            case MotionEvent.ACTION_UP:{
                this.ruffin.setSpeedX(0);
                this.ruffin.setSpeedY(0);
                joystick.resetPosition();
                return true;
            }

        }
        return super.onTouchEvent(event);
    }

    public void update()
    {
        bg.update();
        ruffin.update();
    }
    @Override
    public void draw(Canvas canvas)
    {
        final float scaleFactorX = getWidth()/(WIDTH*1.f);
        final float scaleFactorY = getHeight()/(HEIGHT*1.f);
        if(canvas!=null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            ruffin.draw(canvas);
            joystick.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }

    private void setProportions(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        this.WIDTH = metrics.widthPixels;
        this.HEIGHT = metrics.heightPixels;
    }

}
