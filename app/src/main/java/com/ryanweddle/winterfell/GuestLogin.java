package com.ryanweddle.winterfell;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GuestLogin extends AppCompatActivity {

    public static final String CLASS_TAG = "GuestLoginActivity";
    private static final String EXAMPLE_JWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJIZWxsb1NESy0wMDEiLCJuYW1lIjoiR3Vlc3QgMDAxIiwiaXNzIjoiWTJselkyOXpjR0Z5YXpvdkwzVnpMMDlTUjBGT1NWcEJWRWxQVGk4eE1UVXpOR1UyWmkweE1qY3dMVFJqWlRjdFltUTJNQzA1TnpWaVkyUmxZV1JtTldNIiwiaWF0IjoxNTIwNjM1OTQ4LCJleHAiOjE1MjEwNjc5NDh9.j-lnPJm0iC53sCg7FsjNWFfhuz_2LZuolEbjf1IkD5I";

    private SparkModel mSparkModel;
    private Button mButtonLoginJWT;
    private Button mRegisterButton;
    private Button mDeregisterButton;
    private EditText mTokenEntry;


    private ProgressDialog mProgress = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_login);

        Log.v(CLASS_TAG, "onCreate");

        mSparkModel = SparkModel.getInstance();

        mButtonLoginJWT = findViewById(R.id.button_jwtlogin);
        mRegisterButton = findViewById(R.id.button_registerphone);
        mDeregisterButton = findViewById(R.id.button_deregisterphone);
        mTokenEntry = findViewById(R.id.text_jwtentry);

        // temporary setup sample JWT
        mTokenEntry.setText(EXAMPLE_JWT);

        mButtonLoginJWT.setOnClickListener(view -> {
            Log.i(CLASS_TAG, "JWT Login Button Pressed");

            showBusyIndicator("Login", "Waiting for login...");
            String token = mTokenEntry.getText().toString();

            // need to add exception handling

            mSparkModel.authenticateJWT(token, r -> {
                if(r.isSuccessful()) {
                    Log.i(CLASS_TAG, "JWT auth successful");
                    dismissBusyIndicator();
                    toast("Login Successful");
                }
                else {
                    Log.i(CLASS_TAG, "JWT auth failed");
                    dismissBusyIndicator();
                    toast("Login failed");
                }
            });

        });


        mRegisterButton.setOnClickListener(view -> {
            Log.i(CLASS_TAG, "Register Button Pressed");

            if(mSparkModel.isRegistered()) {
                toast("Already registered");
            } else if(!mSparkModel.isAuthenticated()) {
                toast("Must Auth before registering");
            }
            else {
                showBusyIndicator("Registering", "Waiting for device registration...");

                mSparkModel.register(r -> {
                    if(r.isSuccessful()) {
                        Log.i(CLASS_TAG, "Registration successful");
                        dismissBusyIndicator();
                        toast("Registration Successful");
                    }
                    else {
                        Log.i(CLASS_TAG, "Registration failed");
                        dismissBusyIndicator();
                        toast("Registration Failed");
                    }
                });
            }
        });


        mDeregisterButton.setOnClickListener(view -> {
            Log.i(CLASS_TAG, "Deregister Button Pressed");

            if(!mSparkModel.isRegistered()) {
                toast("Not registered yet");
            } else if(!mSparkModel.isAuthenticated()) {
                toast("Must auth before registering");
            } else {
                showBusyIndicator("Deregistering", "Waiting for device deregistration...");

                mSparkModel.deRegister(r -> {
                    if(r.isSuccessful()) {
                        Log.i(CLASS_TAG, "Deregistration successful");
                        dismissBusyIndicator();
                        toast("Deregistration Successful");
                    }
                    else {
                        Log.i(CLASS_TAG, "Deregistration failed");
                        dismissBusyIndicator();
                        toast("Deregistration Failed");
                    }
                });
            }
        });
    }


    // User Interface helper functions

    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void showBusyIndicator(String title, String message) {
        mProgress = ProgressDialog.show(this, title, message);
    }

    public void dismissBusyIndicator() {
        if (mProgress != null && mProgress.isShowing()) {
            mProgress.dismiss();
            mProgress = null;
        }
    }
}
