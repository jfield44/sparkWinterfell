package com.ryanweddle.winterfell;

import com.ciscospark.androidsdk.Spark;
import com.ciscospark.androidsdk.auth.Authenticator;

/**
 * Created by ryweddle on 3/10/18.
 */

class SparkModel {
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

    private void authenticateJWT(String token) {

    }
}
