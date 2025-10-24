package com.dssmv.guardian;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword, etConfirmPassword;
    private Button btnRegister;
    private TextView tvBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this); // Variável 'splashScreen' removida pois não era usada
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername_register);
        etEmail = findViewById(R.id.etEmail_register);
        etPassword = findViewById(R.id.etPassword_register);
        etConfirmPassword = findViewById(R.id.etConfirmPassword_register);
        btnRegister = findViewById(R.id.btnRegister);
        tvBackToLogin = findViewById(R.id.tvBackToLogin);

        btnRegister.setOnClickListener(v -> {
            attemptRegistration();
        });

        tvBackToLogin.setOnClickListener(v -> {
            finish();
        });
    }

    private void attemptRegistration() {
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, getString(R.string.login_error_empty_fields), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError(getString(R.string.register_error_password_mismatch));
            etConfirmPassword.requestFocus();
            return;
        }

        Toast.makeText(this, getString(R.string.register_success), Toast.LENGTH_LONG).show();
        finish();
    }
}