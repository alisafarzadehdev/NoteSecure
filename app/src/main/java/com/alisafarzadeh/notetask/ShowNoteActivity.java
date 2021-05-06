package com.alisafarzadeh.notetask;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);

        ((TextView)findViewById(R.id.txFinalTitle)).setText(getIntent().getStringExtra("title"));
        ((TextView)findViewById(R.id.txFinalContent)).setText("یادداشت: "+"\n"+getIntent().getStringExtra("content"));

    }
}