package com.laporwarga.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.laporwarga.R;
import com.laporwarga.database.UserPreferences;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername;
    private Button btnLogin;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userPreferences = new UserPreferences(this);

        // Jika sudah login, langsung ke MainActivity
        if (userPreferences.isLoggedIn()) {
            goToMainActivity();
            return;
        }

        etUsername = findViewById(R.id.et_username);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(v -> handleLogin());
    }

    private void handleLogin() {
        String username = etUsername.getText().toString().trim();

        if (username.isEmpty()) {
            Toast.makeText(this, "Username tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        userPreferences.saveUsername(username);
        goToMainActivity();
    }

    private void goToMainActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}
