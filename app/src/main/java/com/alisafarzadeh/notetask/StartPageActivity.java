package com.alisafarzadeh.notetask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class StartPageActivity extends AppCompatActivity {

    TextInputEditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        text = findViewById(R.id.PassStart);

       // Toast.makeText(this,  getIntent().getStringExtra("PasswordNote")+"", Toast.LENGTH_SHORT).show();

        ((Button)findViewById(R.id.BtnStart)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = getIntent().getStringExtra("PasswordNote").trim();
                String temp2 = text.getText().toString().trim();
                Log.d("TEMP",temp+":"+temp2);
                if (text.getText().toString().trim().equals(temp))
                {
                    Intent ii = new Intent(StartPageActivity.this,MainActivity.class);
                    startActivity(ii);
                    finish();
                }
                else
                {
                    Toast.makeText(StartPageActivity.this, "رمز اشتباه است!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}