package com.example.jobportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jobportal.Model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Locale;

public class hirerMain extends AppCompatActivity {
    private FloatingActionButton postjobBtn;
    private ImageButton profile, logout;
    //recycler view..
    private RecyclerView recyclerView;

    //firebase
    private FirebaseAuth mauth;
    private DatabaseReference mdatabase;
    private PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hirer_main);
        getSupportActionBar().hide();

        profile = findViewById(R.id.profilebtn);
        logout = findViewById(R.id.logoutbtn);
        postjobBtn = findViewById(R.id.postjob);
        recyclerView = findViewById(R.id.recyclerid);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mauth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mauth.getCurrentUser();
        String uId = mUser.getUid();
        FirebaseRecyclerOptions<Data> options = new FirebaseRecyclerOptions.Builder<Data>().setQuery(FirebaseDatabase.getInstance().getReference().child("Job Post").child(uId), Data.class).build();


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(hirerMain.this, login.class);
                startActivity(intent);
                finish();

            }
        });

        postjobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(hirerMain.this, postjob.class);
                startActivity(intent);
            }
        });

        adapter = new PostAdapter(options,this);
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
