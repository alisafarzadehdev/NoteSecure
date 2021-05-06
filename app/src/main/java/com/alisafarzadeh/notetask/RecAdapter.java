package com.alisafarzadeh.notetask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alisafarzadeh.notetask.Room.Notes;

import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.HolderView> {

    List<Notes> notes;
    Context context;

    public RecAdapter(List<Notes> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }


    @NonNull
    @Override
    public HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout,null);
        v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new HolderView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderView holder, int position) {
        holder.title.setText(notes.get(position).getTitle());
        holder.time.setText(notes.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class HolderView extends RecyclerView.ViewHolder
    {
        TextView title,time;
        ImageView imgdelete,imgedit;
        LinearLayout parentlin;
        public HolderView(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.TitleTXT);
            time = itemView.findViewById(R.id.TimeTXT);
            imgdelete = itemView.findViewById(R.id.deletebtn);
            imgedit = itemView.findViewById(R.id.editbtn);
            parentlin = itemView.findViewById(R.id.parent_layout);
        }
    }
}
