package com.ryanweddle.winterfell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String CLASS_TAG = "MainActivity";

    private SparkModel mSparkModel;
    private Button mLoginButtonJWT;
    private Button mLoginButtonOauth;
    private Button mCallButton;
    private EditText mCallEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v(CLASS_TAG, "onCreate");

        mSparkModel = SparkModel.getInstance();
        mSparkModel.setApp(this.getApplication());


        mLoginButtonJWT = findViewById(R.id.button_main_jwt);
        mLoginButtonOauth = findViewById(R.id.button_main_oauth);
        mCallButton = findViewById(R.id.button_main_call);
        mCallEdit = findViewById(R.id.text_main_callee);

        mCallEdit.setText("ryweddle@cisco.com");

        mLoginButtonOauth.setOnClickListener(view -> {
            Log.i(CLASS_TAG, "Oauth Login Button Pressed");

            Intent intent = new Intent(MainActivity.this, OauthLogin.class);
            startActivity(intent);
        });

        mLoginButtonJWT.setOnClickListener(view -> {
            Log.i(CLASS_TAG, "JWT Login Button Pressed");

            Intent intent = new Intent(MainActivity.this, GuestLogin.class);
            startActivity(intent);
        });

        mCallButton.setOnClickListener(view -> {
            Log.i(CLASS_TAG, "Test Call Button Pressed");

            Intent intent = new Intent(MainActivity.this, SparkCall.class);
            intent.putExtra(SparkCall.INTENT_CALLEE, mCallEdit.getText().toString());

            startActivity(intent);
        });
    }


}
