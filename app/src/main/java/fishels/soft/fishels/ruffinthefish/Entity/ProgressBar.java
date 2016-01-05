package fishels.soft.fishels.ruffinthefish.Entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Player;

public class ProgressBar {

    private Bitmap frame;
    private Bitmap barImage;
    private Bitmap outputBar;
    private double scoreWidth;
    private Player player;
    private int scoreToLevelUp;

    public ProgressBar(Bitmap frame, Bitmap bar,Player player)
    {
        this.frame = Bitmap.createScaledBitmap(frame, GamePanel.getWIDTH(),frame.getHeight(),true);
        this.barImage= bar;
        this.player=player;
    }

    public void update(int score)
    {
        if(score==0){
            this.scoreToLevelUp = (int) Math.pow(10, this.player.getCurrentLevel().getValue());
            this.scoreWidth = GamePanel.getWIDTH()/this.scoreToLevelUp;
        }
        int width = 1+(int) (score*this.scoreWidth);
        outputBar = Bitmap.createBitmap(barImage, 0, 0,
                width, barImage.getHeight());
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(outputBar, 0,0, null);
        canvas.drawBitmap(frame,0,0,null);
    }
}

