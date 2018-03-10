package com.ryanweddle.winterfell;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ciscospark.androidsdk.phone.MediaOption;

public class SparkCall extends AppCompatActivity {


    public static final String CLASS_TAG = "SparkCall";
    public static final String INTENT_CALLEE = "com.ryanweddle.winterfell.CALLEE_ID";

    private SparkModel mSparkModel;
    private View mRemoteView;
    private TextView mCallLabel;
    private String mCallString;
    private MediaOption mMedia;

    private ProgressDialog mProgress = null;


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
        mMedia = MediaOption.audioOnly();

        // permission request code
        String[] permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
        };

        this.requestPermissions(permissions,0);


        showBusyIndicator("Dialing", "Please wait...");
        mSparkModel.dial(mCallString, mMedia, r -> {
            if(r.isSuccessful()) {
                dismissWithToast("Connected");
            }
            else {
                dismissWithToast("Call Failed");
                Log.e(CLASS_TAG, r.getError().toString());
            }
        });

    }

    protected void handleIntent() {
        Intent intent = getIntent();
        mCallString = intent.getStringExtra(INTENT_CALLEE);
    }




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

    public void dismissWithToast(String message) {
        Log.i(CLASS_TAG, message);
        dismissBusyIndicator();
        toast(message);
    }
}
