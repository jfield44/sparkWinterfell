package com.ryanweddle.winterfell;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OauthLogin extends AppCompatActivity {

    private SparkModel mSparkModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth_login);

        mSparkModel = SparkModel.getInstance();
    }
}
