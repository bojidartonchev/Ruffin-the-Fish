package fishels.soft.fishels.ruffinthefish.Core.Labels.Elements;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.view.MotionEvent;

import fishels.soft.fishels.ruffinthefish.Core.GamePanel;

public class GameButton {
    public Matrix btn_matrix;
    public RectF btn_rect;
    public float width;
    float height;
    private Bitmap bg;
    private Paint paint;

    public GameButton(Bitmap bg,int x,int y){
        this.btn_matrix = new Matrix();
        this.width = bg.getWidth();
        this.height = bg.getHeight();
        this.bg = bg;

        this.btn_rect = new RectF(0, 0, width, height);
        y+=this.bg.getHeight()/2;
        this.setPosition(x,y);
    }

    public void setPosition(float x, float y){
        btn_matrix.setTranslate(x, y);
        btn_matrix.mapRect(btn_rect);
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bg, btn_matrix, paint);
    }

    public boolean onTouch(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: {
                this.paint = new Paint();
                paint.setColorFilter(new PorterDuffColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP));
                break;
            }
            case MotionEvent.ACTION_UP:{

            }
            case MotionEvent.ACTION_CANCEL: {
                this.paint = null;
                break;
            }
        }
        return true;
    }
}
