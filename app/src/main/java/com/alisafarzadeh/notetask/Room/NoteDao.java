package com.alisafarzadeh.notetask.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM notedb")
    List<NoteItem> getNotes();

    @Query("SELECT * FROM notedb where title LIKE  :title")
    NoteItem findByName(String title);

    @Query("SELECT COUNT(*) from notedb")
    int countNote();

    @Query("DELETE FROM notedb")
    void deleteNote();

    @Update
    void EditNote(NoteItem users);

    @Insert
    void insertToNote(NoteItem users);

    @Delete
    void deleteNote(NoteItem user);

}
