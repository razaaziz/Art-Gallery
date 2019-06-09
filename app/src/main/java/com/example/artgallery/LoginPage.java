package com.example.artgallery;

import android.app.AppComponentFactory;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginPage extends baseActivity implements View.OnClickListener {
    private static final String TAG = "LoginPage";

    private EditText paswd, email;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        paswd = findViewById(R.id.paswd);
        email = findViewById(R.id.email);


        findViewById(R.id.lgbtn).setOnClickListener(this);
        findViewById(R.id.sgnbtn).setOnClickListener(this);
        findViewById(R.id.frpwdbtn).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }


    private void signIn(String e, String p) {
        Log.d(TAG, "signIn:" + e);
        if (validateForm()) {
            return;
        }
        showProgressDialog();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(e, p)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginPage.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }


    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }


    private void sendEmailVerification() {
        // Disable button
        findViewById(R.id.lgbtn).setEnabled(false);

        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
                        findViewById(R.id.lgbtn).setEnabled(true);

                        if (task.isSuccessful()) {
                            toastMessage("Verification email sent to " + user.getEmail());
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            toastMessage("Failed to send verification email.");
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }

    private boolean validateForm() {
        boolean valid = true;

        String e = email.getText().toString();
        if (TextUtils.isEmpty(e)) {
            email.setError("Required.");
            valid = false;
        } else {
            email.setError(null);
        }

        String p= paswd.getText().toString();
        if (TextUtils.isEmpty(p)) {
            paswd.setError("Required.");
            valid = false;
        } else {
            paswd.setError(null);
        }

        return !valid;
    }


    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {
            toastMessage("Sign in");
        } else {
            toastMessage("Something went wrong!");
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.sgnbtn) {
            startActivity(new Intent(LoginPage.this, registeration.class));
            finish();
        } else if (i == R.id.lgbtn) {
            signIn(email.getText().toString(), paswd.getText().toString());
            startActivity(new Intent(LoginPage.this, HomePage.class));
            finish();
        } else if (i == R.id.frpwdbtn) {
            startActivity(new Intent(LoginPage.this, ForgetPassword.class));
            finish();

        } /*else if (i == R.id.verbtn) {
            sendEmailVerification();
        }*/
    }



}