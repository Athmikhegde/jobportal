package com.example.jobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jobportal.Model.Data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class postjob extends AppCompatActivity {
               private FloatingActionButton mdone;
               private EditText mtitle,mdesc,msalary,mskill,mlink;

               //firebase
                private FirebaseAuth mAuth;
                private DatabaseReference mjobpost;
                private DatabaseReference publicdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postjob);
        getSupportActionBar().hide();

        //database
        mAuth=FirebaseAuth.getInstance();

        FirebaseUser mUser=mAuth.getCurrentUser();
        String uId=mUser.getUid();

        mjobpost= FirebaseDatabase.getInstance().getReference().child("Job Post").child(uId);
        publicdatabase =FirebaseDatabase.getInstance().getReference().child("public data");


        insertJob();
    }

    private void insertJob() {
        mtitle=findViewById(R.id.titletext);
        mdesc=findViewById(R.id.desctext);
        msalary=findViewById(R.id.salarytxt);
        mskill=findViewById(R.id.skilltxt);
        mlink=findViewById(R.id.linktxt);
        mdone=findViewById(R.id.submitjob);

        mdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jobTitle = mtitle.getText().toString().trim();
                String jobDesc = mdesc.getText().toString().trim();
                String jobSalary = msalary.getText().toString().trim();
                String jobSkill = mskill.getText().toString().trim();
                String jobLink = mlink.getText().toString().trim();

                if (TextUtils.isEmpty(jobTitle)) {
                    mtitle.setError("Required Field...");
                    return;
                }
                if (TextUtils.isEmpty(jobDesc)) {
                    mdesc.setError("Required Field...");
                    return;
                }
                if (TextUtils.isEmpty(jobSalary)) {
                    msalary.setError("Required Field...");
                    return;
                }
                if (TextUtils.isEmpty(jobSkill)) {
                    mskill.setError("Required Field...");
                    return;
                }
                if (TextUtils.isEmpty(jobLink)) {
                    mlink.setError("Required Field...");
                    return;
                }
                String id=mjobpost.push().getKey();

                String date= DateFormat.getDateInstance().format(new Date());

                Data data =new Data(jobTitle,jobDesc,jobSalary,jobSkill,jobLink,id,date);
                mjobpost.child(id).setValue(data);
                publicdatabase.child(id).setValue(data);
                Toast.makeText(getApplicationContext(),"Successfull",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),hirerMain.class));
            }
        });
    }
}

