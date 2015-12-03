package fishels.soft.fishels.ruffinthefish.Factories;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.fishels.ruffinthefish.R;

import fishels.soft.fishels.ruffinthefish.Enums.Level;
import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Enemy;

public class LevelTwoFishFactory {
    public static Enemy Create(Context context){
        return new Enemy(BitmapFactory.decodeResource(context
                .getResources(), R.drawable.fishenemy1), Level.TWO);
    }
}
