package com.callor.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView txt_message = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txt_message = findViewById(R.id.txt_join_msg);
        Intent intent = getIntent();

        String userid = intent.getExtras().getString("user_id");
        String password = intent.getExtras().getString("password");
        String name = intent.getExtras().getString("name");
        String tel = intent.getExtras().getString("tel");
        String addr = intent.getExtras().getString("addr");

        String msg = String.format("id: %s\npassword: %s\nname: %s\ntel: %s\naddr: %s",
                userid, password, name, tel, addr);

        txt_message.setText(msg);
    }
}