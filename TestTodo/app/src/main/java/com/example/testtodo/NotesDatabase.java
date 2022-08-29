package com.example.testtodo;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class},version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    private static NotesDatabase instance=null;
    private static final String dbName="notes.db";
    public static NotesDatabase getInstance(Application application){
        if (instance==null){
            instance= Room.databaseBuilder(application,NotesDatabase.class,dbName).build();
        }
        return instance;
    }

    public abstract NotesDao notesDao();
}
