package com.alisafarzadeh.notetask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alisafarzadeh.notetask.Room.NoteDao;
import com.alisafarzadeh.notetask.Room.NoteHolder;
import com.alisafarzadeh.notetask.Room.NoteItem;
import com.alisafarzadeh.notetask.Room.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.transition.Hold;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    NoteHolder holder;
    MyAdapterRecycler adapterRecycler;
    RecyclerView recyclerView;
    List<NoteItem> noteItems = new ArrayList<>();
    //List<NoteItem> noteItems = new ArrayList<>();

    NoteHolder noteHolder;

    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = findViewById(R.id.fabid);
        noteHolder = NoteHolder.getInstance(getApplicationContext());

        recyclerView = findViewById(R.id.recID);
        adapterRecycler = new MyAdapterRecycler(noteItems,MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapterRecycler);

        DividerItemDecoration decoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);

        for (int i = 0; i < noteHolder.getNotedao().getNotes().size(); i++) {
            noteItems.add(noteHolder.getNotedao().getNotes().get(i));
        }
        adapterRecycler.notifyDataSetChanged();


        Log.d("Size",noteHolder.getNotedao().getNotes().size()+"");

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,InsertActivity.class);
                startActivityForResult(i,202);

            }
        });

        //setFromDBtoItem();
        /*
        NoteItem noteItem = new NoteItem();
        noteItem.setTitle("last note");
        noteItem.setTime("1400/9/15");
        Toast.makeText(this, noteHolder.getWord().getNotes().size()+"", Toast.LENGTH_SHORT).show();

         */

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String _time="",_title="",_content="";
        try {
            _time = data.getStringExtra("time");
            _title = data.getStringExtra("title");
            _content = data.getStringExtra("content");
        }catch (Exception e)
        {

        }

        if (requestCode == 202 && !_content.equals(""))
        {
            NoteItem noteItem1 = new NoteItem();
            noteItem1.setTime(data.getStringExtra("time"));
            noteItem1.setTitle(data.getStringExtra("title"));
            noteItem1.setContent(data.getStringExtra("content"));
            Log.d("ffff",noteItem1.getContent()+noteItem1.getTitle());
            noteHolder.getNotedao().insertToNote(noteItem1);
            noteItems.add(noteItem1);
            adapterRecycler.notifyDataSetChanged();

        }
        /*
        if (requestCode == 203)
        {
            NoteItem noteItem = new NoteItem();
            noteItem.setTitle(data.getStringExtra("titleUpdate"));
            noteItem.setContent(data.getStringExtra("contentUpdate"));
            noteItem.setTime(data.getStringExtra("timeUpdate"));
            noteItem.setUid(data.getIntExtra("idUpdate",0));
            Log.d("dddddd",
                    noteItem.getTitle()+
                    noteItem.getContent()+
                    noteItem.getTime()+
                    noteItem.getUid());

            holder.getNotedao().EditNote(noteItem);
            adapterRecycler.notifyDataSetChanged();

        }
        */
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        noteItems.clear();
        for (int i = 0; i < noteHolder.getNotedao().getNotes().size(); i++) {
            noteItems.add(noteHolder.getNotedao().getNotes().get(i));
        }
        adapterRecycler.notifyDataSetChanged();
        super.onResume();
    }

    private void setFromDBtoItem()
    {
        /*
        for (int i = 0; i < noteHolder.getWord().getNotes().size(); i++) {
            //noteHolder.getWord().insertToNote(noteItem);
            NoteItem n = noteHolder.getWord().getNotes().get(i);
            Log.d("items",n.getTitle()+"    :   "+n.getContent()+"  :   "+n.getTime()+"\n");
            //noteItems.add(new Notes(n.getTitle(),n.getContent(),n.getTime()));
        }

         */
        adapterRecycler.notifyDataSetChanged();

    }
}