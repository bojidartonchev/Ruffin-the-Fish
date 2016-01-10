package fishels.soft.fishels.ruffinthefish.Core.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fishels.ruffinthefish.R;

import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.Entity.ShardsContainer;
import fishels.soft.fishels.ruffinthefish.Music.MusicManager;
import fishels.soft.fishels.ruffinthefish.Music.SoundManager;

public class Menu extends Activity {
    RelativeLayout layout;
    ImageButton startBtn;
    Button settingsBtn;
    ImageView shardIcon;
    TextView shardText;

    private boolean continuePlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);
        this.continuePlaying = false;
        MusicManager.start(getApplicationContext(),MusicManager.MUSIC_MENU);
        ShardsContainer.load(getBaseContext());

        this.startBtn = (ImageButton) findViewById(R.id.start_btn);
        this.settingsBtn = (Button) findViewById(R.id.settings_btn);
        this.layout = (RelativeLayout) findViewById(R.id.layout);
        this.shardIcon =(ImageView)findViewById(R.id.shardIcon);
        this.shardIcon.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.shard));

        this.shardText = (TextView) findViewById(R.id.shardText);
        this.shardText.setText(": " + ShardsContainer.getShards());
        this.shardText.setTextSize(GamePanel.getHEIGHT()/36);
        Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/SeaTurtle.ttf");
        this.shardText.setTypeface(typeFace);

        Drawable bg = ContextCompat.getDrawable(getApplicationContext(), R.drawable.menubackground);
        this.layout.setBackground(bg);
        Drawable setbg = ContextCompat.getDrawable(getApplicationContext(), R.drawable.settingicon);
        this.settingsBtn.setBackground(setbg);
        Drawable playbg = ContextCompat.getDrawable(getApplicationContext(), R.drawable.woodenlabel);
        this.startBtn.setBackground(playbg);


        this.startBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        Intent i = new Intent(getBaseContext(), Game.class);
                        startActivity(i);

                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        this.settingsBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN: {
                        Button view = (Button) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        continuePlaying = true;
                        Intent i = new Intent(getBaseContext(), Settings.class);
                        startActivity(i);

                    case MotionEvent.ACTION_CANCEL: {
                        Button view = (Button) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(!continuePlaying) {
            MusicManager.pause();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.continuePlaying=false;
        MusicManager.start(this, MusicManager.MUSIC_MENU);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SoundManager.release();
        MusicManager.release();
    }
}
