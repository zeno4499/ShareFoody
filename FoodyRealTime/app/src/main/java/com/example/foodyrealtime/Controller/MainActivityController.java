package com.example.foodyrealtime.Controller;

import com.example.foodyrealtime.Model.User;

public class MainActivityController {
    com.example.foodyrealtime.Model.User user;

    public MainActivityController() {
        user = new User();
    }

    public String getInforUser() {
        return user.getInforUser();
    }
}
