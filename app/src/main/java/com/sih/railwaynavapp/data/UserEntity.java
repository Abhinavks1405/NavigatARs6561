package com.sih.railwaynavapp.data;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserEntity {
    @PrimaryKey
    @NonNull
    public String email;
    @ColumnInfo(name = "phone_number")
    public String phone;

    public UserEntity(@NonNull String email, String phone) {
        this.email = email;
        this.phone = phone;
    }
}
