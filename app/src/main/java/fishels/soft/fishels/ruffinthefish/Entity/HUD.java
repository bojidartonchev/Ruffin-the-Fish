package fishels.soft.fishels.ruffinthefish.Entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import fishels.soft.fishels.ruffinthefish.Core.GamePanel;

public class HUD {

    private Bitmap image;
    private int score=0,x,y;

    public HUD(Bitmap res)
    {
        this.image = res;
        this.x = res.getWidth() - 10*res.getWidth()/15;
        this.y = res.getHeight() - 3* res.getHeight()/7;
    }
    public void update(int score)
    {
        this.score=score;
    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(image, 0, 0, null);
        Paint paint = new Paint();
        paint.setTextSize(21);
        canvas.drawText(Integer.toString(score), x, y, paint);

    }
}

