package com.megatap.themovies.model;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 1/2/16.
 */
public class User {
    private String mEmail;
    private boolean mAuthenticated;

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public boolean isAuthenticated() {
        return mAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        mAuthenticated = authenticated;
    }
}
