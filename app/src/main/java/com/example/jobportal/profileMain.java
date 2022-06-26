package com.example.jobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class profileMain extends AppCompatActivity {
    ImageButton home,profile,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_main);
        getSupportActionBar().hide();
        home=findViewById(R.id.homebtn);
        profile=findViewById(R.id.profilebtn);
        logout=findViewById(R.id.logoutbtn);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(profileMain.this,homeMain.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            }
        });
        /*profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(profileMain.this,profileMain.class);
                startActivity(intent);
            }
        });*/
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent=new Intent(profileMain.this,login.class);
                startActivity(intent);

            }
        });

    }
}