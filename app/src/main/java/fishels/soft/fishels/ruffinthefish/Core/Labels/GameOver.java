package fishels.soft.fishels.ruffinthefish.Core.Labels;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import fishels.soft.fishels.ruffinthefish.Core.Data;
import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.Core.Labels.Elements.GameButton;

public class GameOver {
    private static GameButton playAgainBtn;
    private static Bitmap gameOverLbl;
    private static int x;
    private static int y;
    private static boolean pressed = false;

    public static void draw(Canvas canvas)
    {
        canvas.drawBitmap(gameOverLbl,x,y,null);
        playAgainBtn.draw(canvas);
    }

    public static void loadGameOverContent(){
        gameOverLbl = Data.getImage(8);
        x = GamePanel.getWIDTH()/2-gameOverLbl.getWidth()/2;
        y = GamePanel.getHEIGHT()/2-gameOverLbl.getHeight();
        int btnY = y+gameOverLbl.getHeight();
        playAgainBtn = new GameButton(Data.getImage(Data.PLAY_AGAIN_BTN),btnY);
    }

    public static boolean onTouch(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (playAgainBtn.btn_rect.contains(x, y)||(event.getActionMasked()== MotionEvent.ACTION_UP&&pressed))
        {
            pressed = !pressed;
            return playAgainBtn.onTouch(event);
        }
        return false;
    }
}
