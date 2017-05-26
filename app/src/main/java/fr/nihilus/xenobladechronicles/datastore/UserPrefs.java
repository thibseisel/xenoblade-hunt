package fr.nihilus.xenobladechronicles.datastore;

import android.content.SharedPreferences;

public final class UserPrefs {

    private static final String KEY_USER_LEVEL = "user_level";

    public static int getUserLevel(SharedPreferences prefs) {
        return prefs.getInt(KEY_USER_LEVEL, 1);
    }

    public static void setUserLevel(SharedPreferences prefs, int level) {
        prefs.edit().putInt(KEY_USER_LEVEL, level).apply();
    }
}
