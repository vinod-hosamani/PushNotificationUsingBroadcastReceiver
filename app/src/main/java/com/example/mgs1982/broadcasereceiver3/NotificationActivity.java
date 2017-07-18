package com.example.mgs1982.broadcasereceiver3;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;


public class NotificationActivity extends Activity implements View.OnClickListener {
    AppCompatButton btn1;
    AppCompatButton btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);
        initView();
        this.setFinishOnTouchOutside(false);
    }

    public void initView() {
        btn1 = (AppCompatButton) findViewById(R.id.closebtnid1);
        btn2 = (AppCompatButton) findViewById(R.id.closebtnid2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.closebtnid1:
                Toast.makeText(this, "you selected YES", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.closebtnid2:
                // finish();
                Toast.makeText(this, "you selected NO", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
