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

public class signup extends AppCompatActivity {
     Button loginbtn,signupbtn;
    EditText memail,mpassword,mname;
    ProgressBar progress;
    FirebaseAuth mAuth;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        mname=findViewById(R.id.editTextName);
        memail=findViewById(R.id.editTextTextEmailAddress2);
        mpassword=findViewById(R.id.editTextTextPassword2);
        signupbtn=findViewById(R.id.signupbtn3);
        progress=findViewById(R.id.progressBar2);

        mAuth=FirebaseAuth.getInstance();



        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=memail.getText().toString().trim();
                String password=mpassword.getText().toString().trim();
                String name=mname.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    mpassword.setError("Required Field...");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mpassword.setError("Required Field...");
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    memail.setError("Required Field...");
                    return;
                }

                if(password.length()<6){
                    mpassword.setError("Password must be atleast 6 characters");
                    return;
                }
                progress.setVisibility(View.VISIBLE);
                //firebase registration
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful()){
                          Toast.makeText(signup.this, "Account Created", Toast.LENGTH_SHORT).show();
                          startActivity(new Intent(getApplicationContext(),loginpage.class));
                      }else{
                          Toast.makeText(signup.this, "Error!!!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                          progress.setVisibility(View.GONE);
                      }
                    }
                });
            }
        });


        loginbtn=findViewById(R.id.loginbtn4);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(signup.this,loginpage.class);
                startActivity(intent);
            }
        });
    }

}