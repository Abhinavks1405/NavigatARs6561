package com.sih.railwaynavapp.data;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserEntity {
    @PrimaryKey
    public String email;
    @ColumnInfo(name = "phone_number")
    public String phone;

    public UserEntity(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }
}
