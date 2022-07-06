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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
     Button loginbtn,signupbtn;
    EditText memail,mpassword,mname,mphone,mdob,mgender;
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
        mphone=findViewById(R.id.editTextno3);
        mdob=findViewById(R.id.editTextdob3);
        mgender=findViewById(R.id.editTextgender3);

        mAuth=FirebaseAuth.getInstance();



        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=mname.getText().toString().trim();
                String email=memail.getText().toString().trim();
                String password=mpassword.getText().toString().trim();
                String dob=mdob.getText().toString().trim();
                String gender=mgender.getText().toString().trim();
                String phone=mphone.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    mpassword.setError("Required Field...");
                    return;
                }

                else if(TextUtils.isEmpty(password)){
                    mpassword.setError("Required Field...");
                    return;
                }
                else if(TextUtils.isEmpty(email)){
                    memail.setError("Required Field...");
                    return;
                }
                else if(TextUtils.isEmpty(dob)){
                    mdob.setError("Required Field...");
                    return;
                }
                else if(TextUtils.isEmpty(gender)){
                    mgender.setError("Required Field...");
                    return;
                }
                else if(TextUtils.isEmpty(phone)){
                    mphone.setError("Required Field...");
                    return;
                }
                else if(phone.length()!=10){
                    Toast.makeText(signup.this,"Invalid Phone Number",Toast.LENGTH_SHORT).show();
                }

                else if(password.length()<6){
                    mpassword.setError("Password must be atleast 6 characters");
                    return;
                }
                progress.setVisibility(View.VISIBLE);
                registerUser(name,email,password,phone,gender,dob);

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

    private void registerUser(String name, String email, String password, String phone, String gender, String dob) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(signup.this, "Account Created", Toast.LENGTH_SHORT).show();

                    //userdata into database
                    readWriteUserDetails writeUserDetails = new readWriteUserDetails(name,email,phone,gender,dob);

                    //database input
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("user details");

                    referenceProfile.child(firebaseUser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            finish();
                            startActivity(new Intent(getApplicationContext(),loginpage.class));
                        }
                    });

                }else{
                    Toast.makeText(signup.this, "Error!!!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progress.setVisibility(View.GONE);
                }
            }
        });
    }

}