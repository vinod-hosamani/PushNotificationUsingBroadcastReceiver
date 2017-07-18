package com.example.mgs1982.broadcasereceiver3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatEditText editTextUserName;
    AppCompatEditText editTextPass;
    AppCompatButton btnLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    public void initView() {
        editTextUserName = (AppCompatEditText) findViewById(R.id.editTextLogUserName);
        editTextPass = (AppCompatEditText) findViewById(R.id.editTextLogPassword);
        btnLog = (AppCompatButton) findViewById(R.id.btnLogin);
        btnLog.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btnLogin:
                String username = editTextUserName.getText().toString();
                String password = editTextPass.getText().toString();
                if (username.equals("user") && password.equals("user"))
                {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(this, "         Login success         ", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }

    }
}
