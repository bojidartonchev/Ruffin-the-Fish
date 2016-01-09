package fishels.soft.fishels.ruffinthefish.Entity;

import android.content.Context;
import android.content.SharedPreferences;

public class ShardsContainer {

    private static int shards;
    private static Context context;

    public static void save(){;
        SharedPreferences settings = context.getApplicationContext().getSharedPreferences("shards", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("shards", shards);
        editor.apply();
    }

    public static void load(Context ctx) {
        context = ctx;
        shards=0;
        try {
            SharedPreferences sharedPref = context.getSharedPreferences("shards", 0);
            shards = sharedPref.getInt("shards", 0);
        } catch (NullPointerException ex){
            shards=0;
        }
    }
    public static void add(int count){
        shards+=count;
        save();
    }

    public static void remove(int count){
        shards-=count;
        save();
    }

    public static int getShards(){
        return shards;
    }



}
