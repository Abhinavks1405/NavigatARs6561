package com.sih.railwaynavapp;

import android.app.Application;

import androidx.room.Room;

import com.sih.railwaynavapp.data.UserDatabase;

public class NavApp extends Application{
        private UserDatabase database;

        @Override
        public void onCreate() {
            super.onCreate();
            database = Room.databaseBuilder(this, UserDatabase.class, "UserDatabase")
                    .build();
        }

        public UserDatabase getDatabase() {
            return database;
        }

}
