package fishels.soft.fishels.ruffinthefish.Entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import fishels.soft.fishels.ruffinthefish.Core.GamePanel;

public class ProgressBar {

    private Bitmap frame;
    private Bitmap barImage;
    private Bitmap outputBar;
    private int fill;

    public ProgressBar(Bitmap frame, Bitmap bar)
    {
        this.frame = Bitmap.createScaledBitmap(frame, GamePanel.getWIDTH(),frame.getHeight(),true);
        this.barImage= bar;
    }
    public void update(int score)
    {
        int width = 1+score*10;
        if(width>barImage.getWidth()){

        }
        outputBar = Bitmap.createBitmap(barImage, 0, 0,
                width, barImage.getHeight());
    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(outputBar, 0,0, null);
        canvas.drawBitmap(frame,0,0,null);

    }
}

