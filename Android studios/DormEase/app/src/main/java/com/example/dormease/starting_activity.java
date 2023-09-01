package com.example.dormease;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.MotionButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import Maps.login_and_registration.admin_login;
import Maps.login_and_registration.owner_login;
import Maps.login_and_registration.user_login;
import users.user_main;

public class starting_activity extends AppCompatActivity {
    private MotionButton admin,owner,user;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        admin=findViewById(R.id.admin);
        owner=findViewById(R.id.owner);
        user=findViewById(R.id.user);

        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

        if(firebaseUser!=null)
        {
            startActivity(new Intent(starting_activity.this, user_main.class));
            finish();
        }

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(starting_activity.this, user_login.class);
                startActivity(intent);
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(starting_activity.this, admin_login.class);
                startActivity(intent);
            }
        });

        owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(starting_activity.this, owner_login.class);
                startActivity(intent);
            }
        });
    }
}