package com.example.artgallery;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends baseActivity {
    private EditText email;
    private Button restPwdbtn, sgnbtn, lgbtn;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);


        email = (EditText) findViewById(R.id.email);
        restPwdbtn = (Button) findViewById(R.id.restPwdbtn);
        sgnbtn = (Button) findViewById(R.id.sgnbtn);
        lgbtn = (Button) findViewById(R.id.lgbtn);

        auth = FirebaseAuth.getInstance();

        restPwdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getText().toString().trim();

                if(TextUtils.isEmpty(e)){
                    toastMessage("Enter registered email");
                    return;
                }else {
                    showProgressDialog();
                    auth.sendPasswordResetEmail(e)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        toastMessage("We have sent you a recovery email!");
                                    } else {
                                        toastMessage("Failed to send recovery email!");
                                    }

                                    hideProgressDialog();
                                }
                            });
                }
            }
        });
        sgnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgetPassword.this, registeration.class));
                finish();

            }
        });
        lgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgetPassword.this, LoginPage.class));
                finish();
            }
        });
    }
}
