package com.bullova.mc.hithere.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.bullova.mc.hithere.R;

/**
 * Created by Michal on 1/15/2015.
 */
public class LoginDetails {
    private String name;
    private boolean loggedIn;
    SharedPreferences sp;

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }


}
