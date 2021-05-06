package com.alisafarzadeh.notetask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alisafarzadeh.notetask.Room.NoteHolder;
import com.alisafarzadeh.notetask.Room.NoteItem;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class InsertActivity extends AppCompatActivity {

    Button btninsert;
    EditText title,content;
    NoteHolder holder;
    String act = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        holder = NoteHolder.getInstance(this);

        title = findViewById(R.id.TitleEdit);
        content = findViewById(R.id.contentEdit);
        btninsert = findViewById(R.id.addbtntoNote);

        if (getIntent().getAction()=="Update")
        {
            act = "Update";
            btninsert.setText("بروزرسانی");
            title.setText(getIntent().getStringExtra("title"));
            content.setText(getIntent().getStringExtra("content"));
        }
        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (title.getText().length()>1 && content.getText().length()>1)
                {
                if (act == "Update") {
                    NoteItem noteItem = new NoteItem();
                    noteItem.setTitle(title.getText().toString());
                    noteItem.setContent(content.getText().toString());
                    noteItem.setTime(getIntent().getStringExtra("time"));
                    noteItem.setUid(getIntent().getIntExtra("id", 0));
                    holder.getNotedao().EditNote(noteItem);
                    finish();
                    /*
                    Intent intent=new Intent();
                    intent.putExtra("titleUpdate",title.getText().toString());
                    intent.putExtra("contentUpdate",content.getText().toString());
                    intent.putExtra("timeUpdate",getIntent().getStringExtra("time"));
                    intent.putExtra("idUpdate",getIntent().getIntExtra("id",0));
                    setResult(203,intent);
                    finish();

                     */
                } else {
                    try {
                        Intent intent = new Intent();
                        intent.putExtra("title", title.getText().toString());
                        intent.putExtra("content", content.getText().toString());
                        intent.putExtra("time", ToIranDate());
                        setResult(202, intent);
                        finish();
                    } catch (Exception e) {

                    }
                }
             }
                else {
                    Toast.makeText(InsertActivity.this, "یادداشت یا عنوانی نوشته نشده است.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String ToIranDate() {

        Calendar calendar = Calendar.getInstance();
        JalaliCalendar.gDate miladdi = new JalaliCalendar.gDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        return JalaliCalendar.MiladiToJalali(miladdi).toString();



        /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            DateTimeFormatter dataformat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            now.format(dataformat);
            DateConverter converter = new DateConverter();
            converter.gregorianToPersian(now.getYear(), now.getMonth().getValue(), now.getDayOfMonth());
            return converter.getYear() + "/" + converter.getMonth() + "/" + converter.getDay();
        }
        else {
            //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            //holder.time.setText(formatter.format(date));
            DateConverter converter = new DateConverter();
            converter.gregorianToPersian(date.getYear(), date.getMonth(), date.getDay());
            return converter.getYear() + "/" + converter.getMonth() + "/" + converter.getDay();
        }

         */

    }
}