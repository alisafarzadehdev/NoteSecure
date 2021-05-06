package com.alisafarzadeh.notetask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    public final String name = "com.alisafarzadeh.notetask";
    TextInputEditText Pass, PassAgain;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;



    @Override
    protected void onStart() {

        String str = sharedPreferences.getString("PASSWORD", "");
        Log.d("oop", str);
        if (str != "") {

            Intent i = new Intent(LoginActivity.this, StartPageActivity.class);
            i.putExtra("PasswordNote", str);
            startActivity(i);


        }
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        sharedPreferences = getSharedPreferences(name, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Pass = findViewById(R.id.PassText);
        PassAgain = findViewById(R.id.PassAgainText);
        Button btn = findViewById(R.id.BtnLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Pass.getText().length() > 5 && PassAgain.getText().length() > 5) {
                    if (Pass.getText().toString().equals(PassAgain.getText().toString())) {
                        shared(Pass);
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(LoginActivity.this, "رمز تطابق ندارد", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "رمز عبور باید بیشتر از 5 رقم باشد.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
    }


    public void shared(TextInputEditText pass)
    {
        editor.putString("PASSWORD",pass.getText().toString());
        editor.commit();
    }
}