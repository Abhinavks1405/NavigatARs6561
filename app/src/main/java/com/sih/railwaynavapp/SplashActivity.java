package com.sih.railwaynavapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.sih.railwaynavapp.LanguageScreen.LanguageSelectScreen;
import com.sih.railwaynavapp.NavScreen.NavScreen;
import com.sih.railwaynavapp.R;

public class SplashActivity extends AppCompatActivity {
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
            startActivity(new Intent(SplashActivity.this , LanguageSelectScreen.class));
        }else{
            startActivity(new Intent(SplashActivity.this , NavScreen.class));
        }
    }
}