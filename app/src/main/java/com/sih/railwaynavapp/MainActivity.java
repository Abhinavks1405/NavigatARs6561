package com.sih.railwaynavapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.sih.railwaynavapp.NavScreen.NavScreen;
import com.sih.railwaynavapp.SignInPage.SignInPage;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }
    protected void init(){
        mAuth = FirebaseAuth.getInstance();
        initObserver();
    }
    protected void initObserver(){
        if(mAuth.getCurrentUser() == null){
            startActivity(new Intent(MainActivity.this , SignInPage.class));
        }else{
            startActivity(new Intent(MainActivity.this , NavScreen.class));
        }
    }
}