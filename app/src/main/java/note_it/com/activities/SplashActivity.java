package note_it.com.activities;

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

public class SplashActivity extends AppCompatActivity {

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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 2000);

    }
}