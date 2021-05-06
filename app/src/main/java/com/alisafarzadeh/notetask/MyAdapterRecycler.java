package com.alisafarzadeh.notetask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alisafarzadeh.notetask.Room.NoteHolder;
import com.alisafarzadeh.notetask.Room.NoteItem;
import com.alisafarzadeh.notetask.Room.Notes;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyAdapterRecycler extends RecyclerView.Adapter<MyAdapterRecycler.MyRecyclerHolder> {

    /*
    List<Notes> notesList = new ArrayList<>();
    Context context;
    public MyAdapterRecycler(List<Notes> noteItem, Context context) {
        notesList = noteItem;
        this.context = context;
    }
    */

    List<NoteItem> notesList = new ArrayList<>();
    Context context;
    NoteHolder noteHolder;

    public MyAdapterRecycler(List<NoteItem> noteItem, Context context) {
        notesList = noteItem;
        this.context = context;
        noteHolder=NoteHolder.getInstance(context);
    }


    @NonNull
    @Override
    public MyRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout,null);
        v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new MyRecyclerHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerHolder holder, int position)
    {
        holder.time.setText(notesList.get(position).getTime());
        holder.title.setText(notesList.get(position).getTitle());
        holder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(G.context,InsertActivity.class);
                i.putExtra("title",notesList.get(position).getTitle());
                i.putExtra("content",notesList.get(position).getContent());
                i.putExtra("time",notesList.get(position).getTime());
                i.putExtra("id",notesList.get(position).getUid());
                i.setAction("Update");
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                G.context.startActivity(i);

                /*
                NoteItem noteItem = new NoteItem();
                noteItem.setTitle();
                noteItem.setTime();
                noteItem.setContent();
                noteHolder.getNotedao().EditNote();
                 */
            }
        });
        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                noteHolder.getNotedao().deleteNote(notesList.get(position));
                //notifyItemRemoved(position);
                //notifyDataSetChanged();

                notesList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, notesList.size());

            }
        });
        holder.parentlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(G.context,ShowNoteActivity.class);
                i.putExtra("title",notesList.get(position).getTitle());
                i.putExtra("content",notesList.get(position).getContent());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                G.context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return notesList.size();
    }


    public static class MyRecyclerHolder extends RecyclerView.ViewHolder {
        TextView title,time;
        ImageView imgdelete,imgedit;
        LinearLayout parentlin;
        public MyRecyclerHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.TitleTXT);
            time = itemView.findViewById(R.id.TimeTXT);
            imgdelete = itemView.findViewById(R.id.deletebtn);
            imgedit = itemView.findViewById(R.id.editbtn);

            parentlin = itemView.findViewById(R.id.parent_layout);
        }
    }
}
