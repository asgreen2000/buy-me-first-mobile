package com.example.buymefirst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText emailInput, passwordInput;
    TextView toRegister;
    AppCompatButton btnLogin;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // email input
        emailInput = findViewById(R.id.idETEmail);
        // password input
        passwordInput = findViewById(R.id.idETPassword);
        // login button
        btnLogin = findViewById(R.id.idBtnLogin);
        // button used for redirecting to register page
        toRegister = findViewById(R.id.idTVToRegister);
        // firebase authentication
        fAuth = FirebaseAuth.getInstance();

        toRegister.setOnClickListener(view -> redirectToRegister());
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogin();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = fAuth.getCurrentUser();

        // check if user is already logged
        if (user != null) {
            redirectToMain();
        }
    }

    void handleLogin() {

        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (TextUtils.isEmpty(email)) {
            emailInput.setError(getString(R.string.warning_on_email));
        }
        else if (TextUtils.isEmpty(password)) {
            passwordInput.setError(getString(R.string.warning_on_password));
        }
        else {
            fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    redirectToMain();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    void redirectToMain() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        this.finish();
    }

    void redirectToRegister() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}