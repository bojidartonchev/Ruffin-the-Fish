package fishels.soft.fishels.ruffinthefish.Entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.FloatMath;
import android.view.MotionEvent;

import fishels.soft.fishels.ruffinthefish.Core.GamePanel;

public class Joystick {
    // coordinates

    private int zeroX;
    private int zeroY;
    private float clickedX=0;
    private float clickedY=0;
    private float dx=0;
    private float dy=0;
    private float radius;
    private float angle;
    private float c;

    // data
    private Bitmap outerCircle;
    private Bitmap innerCircle;

    public Joystick(Bitmap inner, Bitmap outer){
        this.outerCircle = outer;
        this.innerCircle = inner;
        this.zeroX= GamePanel.WIDTH/2;
        this.zeroY= GamePanel.HEIGHT/2;
        this.radius =outerCircle.getWidth()/2;
    }

    public void draw(Canvas canvas){


        canvas.drawBitmap(outerCircle,zeroX-outerCircle.getWidth()/2,zeroY-outerCircle.getHeight()/2,null);
        if(dx==0&&dy==0){
            canvas.drawBitmap(innerCircle,
                    canvas.getWidth()/2-innerCircle.getWidth()/2,
                    canvas.getHeight()/2-innerCircle.getHeight()/2,null);
        }
        else{
            canvas.drawBitmap(innerCircle,
                    clickedX-innerCircle.getWidth()/2,
                    clickedY-innerCircle.getHeight()/2,null);
        }

    }
    public void update(){
        dx=clickedX-zeroX;
        dy=clickedY-zeroY;
        this.angle = (float)Math.atan(Math.abs(dy / dx));
        this.c = (float)Math.sqrt(dx*dx+dy*dy);
        if(c>radius){
            if(dx>0&&dy>0) {//bottom right
                clickedX = zeroX + (radius * (float) Math.cos(angle));
                clickedY = zeroY + (radius * (float) Math.sin(angle));
            }
            else if(dx>0&&dy<0){//top right
                clickedX = zeroX + (radius * (float) Math.cos(angle));
                clickedY = zeroY - (radius * (float) Math.sin(angle));
            }
            else if(dx<0&&dy<0){//top left
                clickedX = zeroX - (radius * (float) Math.cos(angle));
                clickedY = zeroY - (radius * (float) Math.sin(angle));
            }
            else{//bottom left
                clickedX = zeroX - (radius * (float) Math.cos(angle));
                clickedY = zeroY + (radius * (float) Math.sin(angle));
            }
        }
        else{
            clickedX = zeroX+dx;
            clickedY = zeroY+dy;
        }

    }

    public void onTouch(MotionEvent event){

        clickedX = event.getX();
        clickedY = event.getY();

        update();


    }
    public void resetPossition(){
        this.clickedX=0;
        this.clickedY=0;
        this.dx=0;
        this.dy=0;
    }

}
