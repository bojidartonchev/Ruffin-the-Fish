package fishels.soft.fishels.ruffinthefish.Core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.fishels.ruffinthefish.R;

public class Menu extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);
    }

    public void startGame(View view) {
        Intent i = new Intent(getBaseContext(),Game.class);
        startActivity(i);
    }

    public void quit(View view) {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
