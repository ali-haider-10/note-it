package note_it.com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import note_it.com.R;
import note_it.com.util.PrefManager;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText inputNewPassword, inputConfirmPassword;
    private Button btnReset;
    private PrefManager prefManager;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        prefManager = new PrefManager(this);

        inputNewPassword = findViewById(R.id.inputNewPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        btnReset = findViewById(R.id.btnReset);

        email = getIntent().getStringExtra("email");

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPass = inputNewPassword.getText().toString();
                String confirm = inputConfirmPassword.getText().toString();

                if (TextUtils.isEmpty(newPass) || newPass.length() < 4) {
                    inputNewPassword.setError("Password must be at least 4 characters");
                    return;
                }
                if (!newPass.equals(confirm)) {
                    inputConfirmPassword.setError("Passwords do not match");
                    return;
                }

                boolean ok = prefManager.updatePassword(email, newPass);
                if (ok) {
                    Toast.makeText(ResetPasswordActivity.this, "Password updated. Please login.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(ResetPasswordActivity.this, "Failed to update password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

