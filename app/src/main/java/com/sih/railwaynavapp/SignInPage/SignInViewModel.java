package com.sih.railwaynavapp.SignInPage;

import androidx.lifecycle.ViewModel;

public class SignInViewModel extends ViewModel {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
}
