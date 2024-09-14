package com.sih.railwaynavapp.StationSelectScreen;

import android.content.Intent;
import android.os.Bundle;

import com.sih.railwaynavapp.NavScreen.NavScreen;
import com.sih.railwaynavapp.databinding.ActivityStationSelectBinding;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.sih.railwaynavapp.R;


public class StationSelectActivity extends AppCompatActivity {
    private ActivityStationSelectBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityStationSelectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setLifecycleOwner(this);
        init();
    }
    protected void init(){
        initListener();
    }
    protected void initListener(){
       String station = binding.spinnerStation.getSelectedItem().toString();
       binding.buttonNavigate.setOnClickListener(view -> {
             Intent intent = new Intent(StationSelectActivity.this, NavScreen.class);
             intent.putExtra("station",station);
             startActivity(intent);
       });

    }



}
