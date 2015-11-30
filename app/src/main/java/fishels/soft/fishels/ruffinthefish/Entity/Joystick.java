package fishels.soft.fishels.ruffinthefish.Entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class Joystick {
    // coordinates
    private int outerX = 500;
    private int outerY = 500;
    private int innerX;
    private int innerY;
    private boolean isClicked = false;

    // data
    private Bitmap outerCircle;
    private Bitmap innerCircle;

    public Joystick(Bitmap inner, Bitmap outer){
        this.outerCircle = outer;
        this.innerCircle = inner;
        this.innerX=outerX+outerCircle.getWidth()/2-innerCircle.getWidth()/2;
        this.innerY=outerY+outerCircle.getHeight()/2-innerCircle.getHeight()/2;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(outerCircle,outerX,outerY,null);
        canvas.drawBitmap(innerCircle,innerX,innerY,null);
    }

    public boolean isClicked() {
        return this.isClicked;
    }

    public void setIsClicked(boolean isClicked) {
        this.isClicked = isClicked;
    }

    public boolean clickOver(MotionEvent event){

        double clickedX = event.getX();
        double clickedY = event.getY();

        if(clickedX>=this.innerX&& clickedX<=this.innerX + this.innerCircle.getWidth()){
            if(clickedY>=this.innerY&&clickedY<=this.innerY+this.innerCircle.getHeight()){
                this.update((int)clickedX,(int)clickedY);
                this.isClicked=true;
            }
        }
        return this.isClicked;
    }

    private void update(int clickedX,int clickedY){
        if(isInsideOuterCircle(clickedX,clickedY)){
            this.innerX =clickedX-this.innerCircle.getWidth()/2;
            this.innerY =clickedY-this.innerCircle.getHeight()/2;
        }
        else{
            if(isClicked) {
                double radius = this.outerCircle.getWidth() / 2;
                int outerCenterX = (int) (outerX + radius);
                int outerCenterY = (int) (outerY + radius);
                int dx = clickedX-outerCenterX;
                int dy = clickedY - outerCenterY;
                double distance = Math.sqrt(dx * dx - dy * dy);
                double angle = Math.atan(Math.abs(dy/dx));
                if(dx>0&&dy>0) {
                    this.innerX = (int) (outerCenterX+(radius*Math.cos(angle)));
                    this.innerY = (int) (outerCenterY+(radius*Math.sin(angle)));
                }
                else if(dx>0&&dy<0){
                    this.innerX = (int) (outerCenterX+(radius*Math.cos(angle)));
                    this.innerY = (int) (outerCenterY-(radius*Math.sin(angle)));
                }
                else if(dx<0&&dy<0){
                    this.innerX = (int) (outerCenterX-(radius*Math.cos(angle)));
                    this.innerY = (int) (outerCenterY-(radius*Math.sin(angle)));
                }
                else if(dx<0&&dy>0){
                    this.innerX = (int) (outerCenterX-(radius*Math.cos(angle)));
                    this.innerY = (int) (outerCenterY+(radius*Math.sin(angle)));
                }

            }
        }
    }
    private boolean isInsideOuterCircle(int x, int y){
        double radius = this.outerCircle.getWidth()/2;
        int outerCenterX = (int) (outerX+radius);
        int outerCenterY = (int) (outerY+radius);
        double distance = Math.sqrt((outerCenterX-x)*(outerCenterX-x)+(outerCenterY-y)*(outerCenterY-y));
        if(distance<=radius){
            return true;
        }
        return false;
    }
}
