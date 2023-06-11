package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.test.myrequest.MyRequest;

public class MainActivity extends Activity {
    private Button btn_enter, leader_board;
    private EditText userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        leader_board = findViewById(R.id.leader_board);
        btn_enter = (Button) findViewById(R.id.btn_submit);
        userName = (EditText) findViewById(R.id.user_name);
        //userNumber = (EditText) findViewById(R.id.user_number);



        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = userName.getText().toString().trim();
                //int number = Integer.parseInt(String.valueOf(userNumber.getText()));

                if(!TextUtils.isEmpty(userName.getText())) {
                    Intent i = new Intent(getApplicationContext(),GameActivity.class);
                    i.putExtra("keypseudo",name);
                    startActivity(i);
                    finish();
                }
            }
        });

        leader_board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),OtherActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}