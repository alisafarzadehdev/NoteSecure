package com.alisafarzadeh.notetask.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {NoteItem.class}, version = 2)
public abstract class NoteHolder extends RoomDatabase {

    static NoteHolder instance;
    public abstract NoteDao getNotedao();

    public synchronized static NoteHolder getInstance(Context context)
    {
        if (instance==null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(), NoteHolder.class,"note-database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

}
