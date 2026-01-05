package note_it.com.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    private static final String PREF_NAME = "noteit_prefs";
    private static final String KEY_NAME = "key_name";
    private static final String KEY_EMAIL = "key_email";
    private static final String KEY_PASSWORD = "key_password";
    private static final String KEY_IS_LOGGED_IN = "key_is_logged_in";

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public PrefManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public boolean registerUser(String name, String email, String password) {
        String storedEmail = prefs.getString(KEY_EMAIL, null);
        if (storedEmail != null && storedEmail.equals(email)) {
            return false; // already registered
        }
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.putBoolean(KEY_IS_LOGGED_IN, false);
        editor.apply();
        return true;
    }

    public boolean login(String email, String password) {
        String storedEmail = prefs.getString(KEY_EMAIL, null);
        String storedPassword = prefs.getString(KEY_PASSWORD, null);
        if (storedEmail == null) return false;
        if (storedEmail.equals(email) && storedPassword != null && storedPassword.equals(password)) {
            editor.putBoolean(KEY_IS_LOGGED_IN, true);
            editor.apply();
            return true;
        }
        return false;
    }

    public void logout() {
        editor.putBoolean(KEY_IS_LOGGED_IN, false);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public boolean isEmailRegistered(String email) {
        String storedEmail = prefs.getString(KEY_EMAIL, null);
        return storedEmail != null && storedEmail.equals(email);
    }

    public boolean updatePassword(String email, String newPassword) {
        String storedEmail = prefs.getString(KEY_EMAIL, null);
        if (storedEmail != null && storedEmail.equals(email)) {
            editor.putString(KEY_PASSWORD, newPassword);
            editor.apply();
            return true;
        }
        return false;
    }

    public String getName() {
        return prefs.getString(KEY_NAME, null);
    }

    public String getEmail() {
        return prefs.getString(KEY_EMAIL, null);
    }
}

