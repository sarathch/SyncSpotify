package com.example.syennamani.syncspotify.JSON;

import com.google.gson.annotations.SerializedName;

/**
 * Created by syennamani on 2/8/2018.
 */

public class Token {

    @SerializedName("access_token")
    private String mAccessToken;

    @SerializedName("token_type")
    private String mTokenType;

    @SerializedName("expires_in")
    private int mExpiresIn;

    @SerializedName("scope")
    private String mScope;

    public Token(){

    }

    public String getmAccessToken() {
        return mAccessToken;
    }

    public void setmAccessToken(String mAccessToken) {
        this.mAccessToken = mAccessToken;
    }
}
