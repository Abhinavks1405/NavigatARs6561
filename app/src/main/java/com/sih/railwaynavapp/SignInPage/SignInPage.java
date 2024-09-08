package com.sih.railwaynavapp.SignInPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.sih.railwaynavapp.OTPScreen.OTPScreen;
import com.sih.railwaynavapp.R;
import com.sih.railwaynavapp.databinding.ActivitySigninBinding;

public class SignInPage  extends AppCompatActivity {
    private ActivitySigninBinding binding;
    private SignInViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setLifecycleOwner(this);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signin);
        init();
    }

    protected void init() {
        initListener();
    }
   protected void initListener() {
        binding.continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInPage.this, OTPScreen.class);
                intent.putExtra("email",binding.emailInput.getText());
                intent.putExtra("phone",binding.phoneInput.getText());
            }
        });
    }
}
