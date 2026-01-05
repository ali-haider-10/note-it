package note_it.com.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import note_it.com.R;
import note_it.com.util.PrefManager;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    // New: public key to request logout when launching SplashActivity
    public static final String EXTRA_LOGOUT = "extra_logout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        // Set the status bar color
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorDefaultNoteColor));

        // Set the navigation bar color
        window.setNavigationBarColor(getResources().getColor(R.color.colorDefaultNoteColor));

        // Ensure the status bar icons are light (i.e., white) on dark background
        int flags = window.getDecorView().getSystemUiVisibility();
        flags &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; // clear the flag if it was set
        window.getDecorView().setSystemUiVisibility(flags);

        final PrefManager prefManager = new PrefManager(this);

        // New: if started with the logout extra, clear saved login state immediately.
        if (getIntent() != null && getIntent().getBooleanExtra(EXTRA_LOGOUT, false)) {
            // PrefManager is expected to provide a logout/clear method.
            // If your PrefManager method name differs, update the call below accordingly.
            prefManager.logout();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (prefManager.isLoggedIn()) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
                finish();
            }
        }, 2000);

    }
}