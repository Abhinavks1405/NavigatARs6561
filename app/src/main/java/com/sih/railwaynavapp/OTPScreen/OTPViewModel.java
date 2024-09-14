package com.sih.railwaynavapp.OTPScreen;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.sih.railwaynavapp.NavApp;
import com.sih.railwaynavapp.data.UserDatabase;
import com.sih.railwaynavapp.data.UserEntity;

public class OTPViewModel extends ViewModel {
    private UserDatabase db;

    public OTPViewModel(Application application) {
        db = ((NavApp) application).getDatabase();
    }

    public void saveUser(UserEntity user){
        db.userDao().insertUser(user);
    }

}
