package cat.app.tts;

import android.content.Context;
import android.content.SharedPreferences;

public class CatAppConfig {

    public static int tts_tone;

    public static int tts_speed;

    public static boolean tts_reader;

    public static String tts_engine;

    public static String tts_lang;

    public static String save_path;

    public static String out_format;

    private static String config_name = "cat.app.tts_preferences";

    public CatAppConfig (Context context) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences(config_name,Context.MODE_PRIVATE);
        tts_tone = mSharedPreferences.getInt("tts_tone",10);
        tts_speed = mSharedPreferences.getInt("tts_speed",10);
        tts_reader = mSharedPreferences.getBoolean("tts_reader",false);
        tts_lang = mSharedPreferences.getString("tts_lang","English");
        tts_engine = mSharedPreferences.getString("tts_engine","Auto");
        save_path = mSharedPreferences.getString("save","///");
        out_format = mSharedPreferences.getString("format","mp3");
    }

    public static void refreshData(Context context) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences(config_name,Context.MODE_PRIVATE);
        tts_tone = mSharedPreferences.getInt("tts_tone",10);
        tts_speed = mSharedPreferences.getInt("tts_speed",10);
        tts_reader = mSharedPreferences.getBoolean("tts_reader",false);
        tts_lang = mSharedPreferences.getString("tts_lang","English");
        tts_engine = mSharedPreferences.getString("tts_engine","Auto");
        save_path = mSharedPreferences.getString("save_path","///");
        out_format = mSharedPreferences.getString("format","mp3");
    }

}
