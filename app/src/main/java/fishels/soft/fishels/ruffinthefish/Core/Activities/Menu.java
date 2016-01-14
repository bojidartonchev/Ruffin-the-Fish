/*
 * Copyright © 2015 Ruffin the Fish
 *
 * This file is part of "Ruffin the Fish".
 *
 * "Ruffin the Fish" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "Ruffin the Fish" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with "Ruffin the Fish".  If not, see <http://www.gnu.org/licenses/>.
 */

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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

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
    private AdView mAdView;

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

        mAdView = (AdView) findViewById(R.id.ad_view);
        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);


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
        if(!continuePlaying) {
            MusicManager.pause();
        }
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.continuePlaying=false;
        MusicManager.start(this, MusicManager.MUSIC_MENU);
        ShardsContainer.load(getBaseContext());
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        SoundManager.release();
        MusicManager.release();
        super.onDestroy();
    }
}
