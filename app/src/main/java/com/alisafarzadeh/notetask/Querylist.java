package com.alisafarzadeh.notetask;

import androidx.room.Dao;
import androidx.room.Query;

import com.alisafarzadeh.notetask.Room.NoteItem;

import java.util.List;

@Dao
public interface Querylist {

    @Query("se")
    public List<NoteItem> gets();

}
