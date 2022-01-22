package com.example.buymefirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    EditText emailInput, passwordInput, rePasswordInput;
    TextView backToLogin;
    AppCompatButton btnRegister;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // email input
        emailInput = findViewById(R.id.idETEmail);
        // password input
        passwordInput = findViewById(R.id.idETPassword);
        // re-password input
        rePasswordInput = findViewById(R.id.idETRePassword);
        // login button
        btnRegister = findViewById(R.id.idBtnRegister);
        // button used for redirecting to login page
        backToLogin = findViewById(R.id.idTVBack);

        // firebase authentication
        fAuth = FirebaseAuth.getInstance();

        backToLogin.setOnClickListener(view -> redirectToLogin());
        btnRegister.setOnClickListener(view -> handleRegister());
        /* rePasswordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/
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

    void handleRegister() {

        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String rePassword = rePasswordInput.getText().toString();

        if (TextUtils.isEmpty(email)) {
            emailInput.setError(getString(R.string.warning_on_email));
        }
        else if (TextUtils.isEmpty(password)) {
            passwordInput.setError(getString(R.string.warning_on_password));
        }
        else if (TextUtils.isEmpty(rePassword)) {
            rePasswordInput.setError(getString(R.string.warning_on_re_password));
        }
        else if (!password.equals(rePassword)) {
            rePasswordInput.setError(getString(R.string.password_not_match));
        }
        else {
            fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    redirectToMain();
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    void redirectToLogin() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        this.finish();
    }

    void redirectToMain() {
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        this.finish();
    }
}