package com.example.medic.DATABASE;
import android.content.Context;

import com.example.medic.api_and_model.note_model;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = note_model.class, version = 1,exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static RoomDB database;
    private static String DATABASE_NAME= "NoteApp" ;

    public synchronized static RoomDB getInstance(Context context)
    {
        if(database==null)
            database= Room.databaseBuilder(context.getApplicationContext(),
                    RoomDB.class,
                    DATABASE_NAME
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        return database;
    }
    public abstract mainDAO mainDAO();
}
