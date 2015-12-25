package fishels.soft.fishels.ruffinthefish.Core.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import com.example.fishels.ruffinthefish.R;

import fishels.soft.fishels.ruffinthefish.Music.MusicManager;

public class Settings extends Activity {
    RadioButton rbleft;
    RadioButton rbright;
    RadioGroup rg;
    ToggleButton tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);

        this.rg = (RadioGroup) findViewById(R.id.radioGroup);
        this.tb = (ToggleButton) findViewById(R.id.music_tbtn);
        this.rbleft = (RadioButton) findViewById(R.id.left);
        this.rbright = (RadioButton) findViewById(R.id.right);
        this.rbright.setChecked(!this.readSettings("left"));

        this.tb.setChecked(this.readSettings("music"));

        this.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rbleft.getId()) {
                    saveSettings("left", true);
                } else {
                    saveSettings("left", false);
                }
            }
        });

        this.tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveSettings("music", isChecked);
                if (isChecked) {
                    MusicManager.start(getBaseContext(), MusicManager.MUSIC_MENU);
                } else {
                    MusicManager.pause();
                }
            }
        });
    }

    private void saveSettings(String setting, boolean value){
        SharedPreferences keyValues = this.getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = keyValues.edit();
        editor.putBoolean(setting, value);
        editor.commit();
    }

    private boolean readSettings(String setting) {
        SharedPreferences prefs = this.getSharedPreferences("settings", Context.MODE_PRIVATE);
        boolean current = prefs.getBoolean(setting, true); //true is the default value
        return current;
    }
}
