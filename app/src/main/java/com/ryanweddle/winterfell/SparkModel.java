package com.ryanweddle.winterfell;

import android.util.Log;

import com.ciscospark.androidsdk.CompletionHandler;
import com.ciscospark.androidsdk.Result;
import com.ciscospark.androidsdk.Spark;
import com.ciscospark.androidsdk.auth.Authenticator;
import com.ciscospark.androidsdk.auth.JWTAuthenticator;

/**
 * Created by ryweddle on 3/10/18.
 */

class SparkModel {

    public static final String CLASS_TAG = "SparkModel";


    private static final SparkModel ourInstance = new SparkModel();

    private Spark mSpark;
    private Authenticator mAuthenticator;


    static SparkModel getInstance() {
        return ourInstance;
    }

    private SparkModel() {
    }

    public void dial(String callee) {
    }

    public void hangup() {
    }

    public void register() {
    }

    public void deRegister() {
    }

    public boolean isRegistered() {
        return false;
    }

    public boolean isAuthenticated() {
        return false;
    }

    public void authenticateJWT(String token, CompletionHandler<String> resultHandler) {

        JWTAuthenticator jwta = new JWTAuthenticator();
        jwta.authorize(token);
        jwta.getToken(r -> {
            if(r.isSuccessful())
                Log.i(CLASS_TAG, "successful token auth");
            else
                Log.i(CLASS_TAG, "failed token auth");
            resultHandler.onComplete(r);
        });
    }

    public void authenticateJWT(String token) {
        authenticateJWT(token, r->{});
    }


}
