package com.sih.railwaynavapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sih.railwaynavapp.data.UserEntity;
import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM userentity WHERE email = :email")
    UserEntity getUserByEmail(String email);
    @Query("SELECT * FROM userentity")
    List<UserEntity> getAllUsers();
    @Update
    void updateUser(UserEntity user);
    @Delete
    void deleteUser(UserEntity user);
    @Insert
    void insertUser(UserEntity user);
}
