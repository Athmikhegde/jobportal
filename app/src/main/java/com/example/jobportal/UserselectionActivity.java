package com.example.jobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserselectionActivity extends AppCompatActivity {
    Button hirer,seeker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userselection);
        getSupportActionBar().hide();
        seeker=findViewById(R.id.seekerbtn);
        hirer=findViewById(R.id.hirerbtn);
        seeker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(UserselectionActivity.this,homeMain.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();

            }
        });
    }
}