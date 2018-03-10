package com.ryanweddle.winterfell;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class GuestLogin extends AppCompatActivity {

    public static final String CLASS_TAG = "GuestLoginActivity";

    private SparkModel mSparkModel;
    private Button mButtonLoginJWT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_login);

        Log.v(CLASS_TAG, "onCreate");

        mSparkModel = SparkModel.getInstance();

        mButtonLoginJWT = findViewById(R.id.button_jwtlogin);

        mButtonLoginJWT.setOnClickListener(view -> {
            Log.i(CLASS_TAG, "JWT Login Button Pressed");
        });
    }
}
