package com.sih.railwaynavapp.data;

import android.app.Application;

import com.sih.railwaynavapp.NavApp;

import java.util.List;
import java.util.concurrent.Flow;

public class UserRepository {
    private final UserDao userDao;

    public UserRepository(Application application) {
        UserDatabase db = ((NavApp) application).getDatabase();
        userDao = db.userDao();
    }

    // Use the DAO for database operations
    public void addUser(UserEntity user) {
        userDao.insertUser(user);
    }

    public List<UserEntity> getUsers() {
        return userDao.getAllUsers();
    }

    public UserEntity getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public void updateAWish(UserEntity user) {
        userDao.updateUser(user);
    }

    public void deleteUser(UserEntity user) {
        userDao.deleteUser(user);
    }
}