package com.example.jobportal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class profile extends AppCompatActivity {

    private ImageView dp, home;
    private FirebaseAuth mauth;
    private FirebaseUser user;
    private String userID;
    private DatabaseReference reference;
    private FirebaseDatabase firebaseDatabase;
    //private ImageView dpbtn;
    private Uri imageuri;
    private TextView uploadimg;
    private ProgressBar progress;
    StorageReference storageReference;
    String url;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        //dpbtn=findViewById(R.id.dpbtn);

        /*uploadimg=findViewById(R.id.imageupld);
        uploadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(profile.this,uploadimg.class);
                startActivity(intent);

            }
        });*/
        home = findViewById(R.id.homebuttton);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, homeMain.class);
                startActivity(intent);
                finish();
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("user details");
        userID = user.getUid();

        final TextView textViewname = (TextView) findViewById(R.id.editTextname1);
        final TextView email = (TextView) findViewById(R.id.editTextemail);
        final TextView no = (TextView) findViewById(R.id.editTextno);
        final TextView dob = (TextView) findViewById(R.id.editTextdob);
        final TextView gender = (TextView) findViewById(R.id.editTextgender);
        //image upload
       

        reference.child(userID).

                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        readWriteUserDetails write = snapshot.getValue(readWriteUserDetails.class);
                        if (write != null) {
                            String mname = write.name;
                            String memail = write.email;
                            String mno = write.phone;
                            String mgender = write.gender;
                            String mdob = write.dob;


                            textViewname.setText(mname);
                            email.setText(memail);
                            no.setText(mno);
                            gender.setText(mgender);
                            dob.setText(mdob);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(profile.this, "somethis went wrong", Toast.LENGTH_SHORT).show();
                    }
                });


    }
}





