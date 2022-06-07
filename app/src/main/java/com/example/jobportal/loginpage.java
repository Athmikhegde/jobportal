package com.example.jobportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginpage extends AppCompatActivity {
    private Button signup;
    private EditText nEmail;
    private EditText nPassword;
    private Button login;
    ProgressBar progress;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        getSupportActionBar().hide();
        nEmail = findViewById(R.id.editTextTextEmailAddress);
        nPassword = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.loginbtn2);
        progress=findViewById(R.id.progressBar);
        mAuth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=nEmail.getText().toString().trim();
                String password=nPassword.getText().toString().trim();
                if(TextUtils.isEmpty(password)){
                    nPassword.setError("Required Field...");
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    nEmail.setError("Required Field...");
                    return;
                }


                progress.setVisibility(View.VISIBLE);
                //user autentication
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(loginpage.this, "Login successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),homeactivity.class));
                        }else {
                            Toast.makeText(loginpage.this, "Wrong Details!!!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progress.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        signup = findViewById(R.id.signupbtn2);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginpage.this, signup.class);
                startActivity(intent);
            }
        });


    }
}



