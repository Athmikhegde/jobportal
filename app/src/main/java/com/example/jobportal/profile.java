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
    //private TextView xname, xemail, xno, xgender, xdob;
    private ImageView dp, home;
    private FirebaseAuth mauth;
    private FirebaseUser user;
    private String userID;
    private DatabaseReference reference;
    private FirebaseDatabase firebaseDatabase;
    private ImageView dpbtn;
    private Uri imageuri;
    private TextView uploadimg;
    private ProgressBar progress;
    //private String mname,memail,mno,mgender,mdob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        dpbtn=findViewById(R.id.dpbtn);
        //xname = findViewById(R.id.editTextName);
        //xemail = findViewById(R.id.editTextemail);
        //xno = findViewById(R.id.editTextno);
        //xgender = findViewById(R.id.editTextgender);
        //xdob = findViewById(R.id.editTextdob);

        uploadimg=findViewById(R.id.imageupld);
        uploadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(profile.this,uploadimg.class);
                startActivity(intent);

            }
        });
        home = findViewById(R.id.homebuttton);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, homeMain.class);
                startActivity(intent);
                finish();
            }
        });
        //mauth =FirebaseAuth.getInstance();
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

   /* private void showUserProfile(FirebaseUser firebaseUser) {
        String uId=firebaseUser.getUid();

        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("user details");
        referenceProfile.child(uId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                readWriteUserDetails readUserDetails= snapshot.getValue(readWriteUserDetails.class);
                if(readUserDetails!=null){
                    mname=readUserDetails.name;
                    memail=readUserDetails.email;
                    mdob=readUserDetails.dob;
                    mgender=readUserDetails.gender;
                    mno=readUserDetails.phone;

                    xname.setText(mname);
                    xemail.setText(memail);
                    xdob.setText(mdob);
                    xgender.setText(mgender);
                    xno.setText(mno);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(profile.this,"somethis went wrong",Toast.LENGTH_SHORT).show();

            }
        });
*/



    /*private void UploadFiles(Uri data) {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Uploading....");
        progressDialog.show();

        StorageReference reference = storageReference.child("uploadPDF"+System.currentTimeMillis()+".pdf");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri>uriTask=taskSnapshot.getStorage().getDownloadUrl();
                        while(!uriTask.isComplete());
                        Uri uri=uriTask.getResult();

                        putPDF putPDF =new putPDF(name.getText().toString(),uri.toString());
                        databaseReference.child(databaseReference.push().getKey()).setValue(putPDF);


                        Toast.makeText(fillprofile.this,"File Uploaded!!",Toast.LENGTH_SHORT);

                        progressDialog.dismiss();;
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progress=(100.0*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                        progressDialog.setMessage("uploaded:"+(int)progress+"%");

                    }
                });*/






    /*private void selectFiles() {
        Intent intent =new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select PDF Files..."),1);
    }*/




