package com.callor.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;

import com.callor.hello.model.UserDTO;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button btn_save = null;
    private TextInputEditText input_userid = null;
    private TextInputEditText input_password = null;
    private TextInputEditText input_name = null;
    private TextInputEditText input_tel = null;
    private TextInputEditText input_addr = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_userid = findViewById(R.id.input_userid);
        input_password = findViewById(R.id.input_password);
        input_name = findViewById(R.id.input_name);
        input_tel = findViewById(R.id.input_tel);
        input_addr = findViewById(R.id.input_addr);
        btn_save = findViewById(R.id.btn_join);

        btn_save.setOnClickListener( view->{

            String user_id = input_userid.getText().toString();
            String password = input_password.getText().toString();
            String name = input_name.getText().toString();
            String tel = input_tel.getText().toString();
            String addr = input_addr.getText().toString();

            // 2개 이상을 전달해야하므로 DTO 생성후 담음
            UserDTO user = new UserDTO();
            user.user_id = user_id;
            user.password = password;
            user.tel = tel;
            user.addr = addr;

            // 부르는쪽 this, 불리는쪽 class
//            Intent join_intent = new Intent(MainActivity.this, SecondActivity.class);
            Intent join_intent2 = new Intent(MainActivity.this, JoinActivity.class);

            // Extra에 객체도 담을 수 있다
//            join_intent.putExtra("USER", (Parcelable)user); // 시리얼라이징 !!
            join_intent2.putExtra("USER", (Parcelable)user); // 시리얼라이징 !!
//            join_intent.putExtra("user_id", user_id);
//            join_intent.putExtra("password", password);
//            join_intent.putExtra("name", name);
//            join_intent.putExtra("tel", tel);
//            join_intent.putExtra("addr", addr);

//            startActivity(join_intent);
            startActivity(join_intent2);
        });


    }
}