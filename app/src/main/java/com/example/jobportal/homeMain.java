package com.example.jobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class homeMain extends AppCompatActivity {
        ImageButton home,profile,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);

        getSupportActionBar().hide();
        home=findViewById(R.id.homebtn);
        profile=findViewById(R.id.profilebtn);
        logout=findViewById(R.id.logoutbtn);

        /*home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(homeMain.this,homeMain.class);
                startActivity(intent);
            }
        });*/
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(homeMain.this,profileMain.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(homeMain.this,login.class);
                startActivity(intent);
                finish();

            }
        });
       
    }
}