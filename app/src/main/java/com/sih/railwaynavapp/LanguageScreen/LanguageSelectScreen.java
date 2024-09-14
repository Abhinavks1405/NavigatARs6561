package com.sih.railwaynavapp.LanguageScreen;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.sih.railwaynavapp.CommonCodes;
import com.sih.railwaynavapp.R;
import com.sih.railwaynavapp.SignInPage.SignInPage;
import com.sih.railwaynavapp.databinding.ActivityLanguageBinding;
import com.sih.railwaynavapp.databinding.ActivitySigninBinding;

public class LanguageSelectScreen  extends AppCompatActivity {
    private ActivityLanguageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLanguageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setLifecycleOwner(this);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otp);
        init();
    }
    protected void init(){
         initListners();
    }
    protected void initListners(){
        Intent intent = new Intent(LanguageSelectScreen.this, SignInPage.class);
        binding.buttonHindi.setOnClickListener(view -> {
            binding.buttonHindi.setSelected(true);
            binding.buttonEnglish.setSelected(false);
            CommonCodes.isHindi = true;
            startActivity(intent);
        });
        binding.buttonEnglish.setOnClickListener(view -> {
            binding.buttonHindi.setSelected(false);
            binding.buttonEnglish.setSelected(true);
            CommonCodes.isHindi = false;
            startActivity(intent);
        });
    }
}
