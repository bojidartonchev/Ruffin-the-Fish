package fishels.soft.fishels.ruffinthefish.Core.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.fishels.ruffinthefish.R;

import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.Core.Labels.GameOver;
import fishels.soft.fishels.ruffinthefish.Music.MusicManager;
import fishels.soft.fishels.ruffinthefish.Music.SoundManager;

public class Game extends Activity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new GamePanel(this));

        MusicManager.start(getApplicationContext(), MusicManager.MUSIC_GAME);
        SoundManager.setSoundOn(getBaseContext());
        GameOver.loadGameOverContent();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MusicManager.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MusicManager.start(this, MusicManager.MUSIC_GAME);
    }
}
