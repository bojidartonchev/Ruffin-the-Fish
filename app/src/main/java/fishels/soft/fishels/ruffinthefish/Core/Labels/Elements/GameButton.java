package fishels.soft.fishels.ruffinthefish.Core.Labels.Elements;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.view.MotionEvent;

public class GameButton {
    public Matrix btn_matrix = new Matrix();
    public RectF btn_rect;
    float width;
    float height;
    static Bitmap bg;
    private static Paint paint;

    public GameButton(Bitmap bg)
    {
        this.width = bg.getWidth();
        this.height = bg.getHeight();
        this.bg = bg;

        btn_rect = new RectF(0, 0, width, height);
        this.setPosition(500,500);
    }

    public void setPosition(float x, float y)
    {
        btn_matrix.setTranslate(x, y);
        btn_matrix.mapRect(btn_rect);
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(bg, btn_matrix, paint);
    }

    public static boolean onTouch(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: {
                paint = new Paint();
                paint.setColorFilter(new PorterDuffColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP));
                break;
            }
            case MotionEvent.ACTION_UP:
                System.out.println("RESTART GAME");

            case MotionEvent.ACTION_CANCEL: {
                paint = null;
                break;
            }
        }
        return true;
    }
}
