package com.ryanweddle.winterfell;

import android.app.Application;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ciscospark.androidsdk.CompletionHandler;
import com.ciscospark.androidsdk.Result;
import com.ciscospark.androidsdk.Spark;
import com.ciscospark.androidsdk.SparkError;
import com.ciscospark.androidsdk.auth.Authenticator;
import com.ciscospark.androidsdk.auth.JWTAuthenticator;
import com.ciscospark.androidsdk.phone.Phone;

/**
 * Created by ryweddle on 3/10/18.
 */

class SparkModel {

    public static final String CLASS_TAG = "SparkModel";


    private static final SparkModel ourInstance = new SparkModel();

    private Spark mSpark;
    private Phone mPhone;
    private Authenticator mAuthenticator;
    private Application mApp;


    private boolean mRegState = false;

    static SparkModel getInstance() {
        return ourInstance;
    }

    private SparkModel() {
    }

    public void setApp(Application app) {
        mApp = app;
    }

    public void dial(String callee) {
    }

    public void hangup() {
    }

    public void register(CompletionHandler<Void> callback) {

        if(!isInitSpark())
            initSpark();

        mPhone = mSpark.phone();
        mPhone.register(r-> {
            if(r.isSuccessful()) {
                Log.i(CLASS_TAG, "phone registration successful");
                mRegState = true;
            } else {
                Log.i(CLASS_TAG, "phone registration failed");
                mRegState = false;
            }
            callback.onComplete(r);
        });
    }

    public void deRegister(CompletionHandler<Void> callback) {
        if(mRegState) {
            mPhone.deregister(r -> {
               if(r.isSuccessful()) {
                   Log.i(CLASS_TAG, "phone deregistration successful");
               } else {
                   Log.i(CLASS_TAG, "phone deregistration failed");
               }
               callback.onComplete(r);
            });
        }
    }

    private void initSpark() {
        if(mAuthenticator == null || mApp == null) {
            Log.e(CLASS_TAG, "initSpark called without Authenticator or App");
        } else {
            mSpark = new Spark(mApp, mAuthenticator);
        }
    }

    private boolean isInitSpark() {
        return mSpark != null;
    }



    public boolean isRegistered() {
        return mRegState;
    }

    public boolean isAuthenticated() {
        if(mAuthenticator != null)
            return mAuthenticator.isAuthorized();
        else
            return false;
    }

    public void authenticateJWT(String token, CompletionHandler<String> resultHandler) {

        JWTAuthenticator jwta = new JWTAuthenticator();
        jwta.authorize(token);
        jwta.getToken(r -> {
            if(r.isSuccessful()) {
                Log.i(CLASS_TAG, "successful token auth");
                mAuthenticator = jwta;
            }
            else
                Log.i(CLASS_TAG, "failed token auth");
            resultHandler.onComplete(r);
        });
    }

    public void authenticateJWT(String token) {
        authenticateJWT(token, r->{});
    }


}
