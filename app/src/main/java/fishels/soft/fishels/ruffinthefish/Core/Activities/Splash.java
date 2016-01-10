package fishels.soft.fishels.ruffinthefish.Core.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.fishels.ruffinthefish.R;

import fishels.soft.fishels.ruffinthefish.Core.Data;
import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.Entity.ShardsContainer;
import fishels.soft.fishels.ruffinthefish.Entity.Vibration;
import fishels.soft.fishels.ruffinthefish.Factory.EnemyFishFactory;
import fishels.soft.fishels.ruffinthefish.Factory.EventFactory;
import fishels.soft.fishels.ruffinthefish.Music.SoundManager;

public class Splash extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);

        TextView tv = (TextView)findViewById(R.id.textView);

        final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.blink_animation);
        tv.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Context base = getBaseContext();
                EnemyFishFactory.LoadImages(base);
                SoundManager.loadSoundManager();
                SoundManager.loadSounds(base);
                EventFactory.loadContent(base);
                Data.loadContent(base);
                Vibration.loadVibrator(base);
                GamePanel.setProportions(base);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                Intent i = new Intent(getBaseContext(),Menu.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
