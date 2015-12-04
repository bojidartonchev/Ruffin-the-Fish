package fishels.soft.fishels.ruffinthefish.Factory;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.fishels.ruffinthefish.R;

import fishels.soft.fishels.ruffinthefish.Enums.Level;
import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Enemy;

public class EnemyFishFactory {
    public static Enemy Create(Context context){
        return new Enemy(BitmapFactory.decodeResource(context
        .getResources(), R.drawable.fishenemy1), Level.ONE);
    }

    //TODO auto generate fish level
}
