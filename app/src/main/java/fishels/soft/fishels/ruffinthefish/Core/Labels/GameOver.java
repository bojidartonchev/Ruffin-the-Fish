package fishels.soft.fishels.ruffinthefish.Core.Labels;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import fishels.soft.fishels.ruffinthefish.Core.Data;
import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.Core.Labels.Elements.GameButton;

public class GameOver {
    private static GameButton playAgainBtn;
    private static GameButton continueBtn;
    private static Bitmap gameOverLbl;
    private static int x;
    private static int y;
    private static boolean againPressed = false;
    private static boolean continuePressed = false;

    public static void draw(Canvas canvas)
    {
        canvas.drawBitmap(gameOverLbl,x,y,null);
        playAgainBtn.draw(canvas);
        continueBtn.draw(canvas);
    }

    public static void loadGameOverContent(){
        gameOverLbl = Data.getImage(Data.GAMEOVER_LABEL);
        x = GamePanel.getWIDTH()/2-gameOverLbl.getWidth()/2;
        y = GamePanel.getHEIGHT()/2-gameOverLbl.getHeight();
        int btnY = y+gameOverLbl.getHeight();
        playAgainBtn = new GameButton(Data.getImage(Data.PLAY_AGAIN_BTN),x,btnY);
        int btnX= (int) (x+playAgainBtn.width);
        continueBtn = new GameButton(Data.getImage(Data.CONTINUE_BTN),btnX,btnY);
    }

    public static int onTouch(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (playAgainBtn.btn_rect.contains(x, y)||(event.getActionMasked()== MotionEvent.ACTION_UP&&againPressed))
        {
            againPressed = !againPressed;
            playAgainBtn.onTouch(event);
            return 1;
        }
        else if(continueBtn.btn_rect.contains(x, y)||(event.getActionMasked()== MotionEvent.ACTION_UP&&continuePressed)){
            continuePressed = !continuePressed;
            continueBtn.onTouch(event);
            return 2;
        }
        return 0;
    }
}
