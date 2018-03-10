package com.ryanweddle.winterfell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SparkCall extends AppCompatActivity {


    public static final String CLASS_TAG = "SparkCall";
    public static final String INTENT_CALLEE = "com.ryanweddle.winterfell.CALLEE_ID";

    private SparkModel mSparkModel;
    private View mRemoteView;
    private TextView mCallLabel;
    private String mCallString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        Log.v(CLASS_TAG, "onCreate");

        mSparkModel = SparkModel.getInstance();

        mRemoteView = findViewById(R.id.view_callremote);
        mCallLabel = findViewById(R.id.text_call_label);

        handleIntent();

        mCallLabel.setText(mCallString);

    }

    protected void handleIntent() {
        Intent intent = getIntent();
        mCallString = intent.getStringExtra(INTENT_CALLEE);
    }
}
