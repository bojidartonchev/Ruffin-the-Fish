package fishels.soft.fishels.ruffinthefish.Core.Labels;

import android.graphics.Canvas;
import android.view.MotionEvent;

import fishels.soft.fishels.ruffinthefish.Core.Data;
import fishels.soft.fishels.ruffinthefish.Core.Labels.Elements.GameButton;

public class GameOver {
    private static GameButton playAgainBtn = new GameButton(Data.getImage(Data.PLAY_AGAIN_BTN));

    public static void draw(Canvas canvas)
    {
        playAgainBtn.draw(canvas);
    }

    public static boolean onTouch(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (playAgainBtn.btn_rect.contains(x, y)||event.getActionMasked()== MotionEvent.ACTION_UP)
        {
            playAgainBtn.onTouch(event);
            return true;
        }
        return false;
    }
}
