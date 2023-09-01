package com.example.medic.DATABASE;

import static androidx.room.OnConflictStrategy.REPLACE;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.medic.api_and_model.note_model;

import java.util.List;

@Dao
public interface mainDAO {

    @Insert(onConflict = REPLACE)      //on conflicts replaces an existing row with a new row. ie on Conflicts
    void insert(note_model note);

    @Delete
    void delete(note_model note);                   //delete note

    @Query("SELECT * FROM notes ORDER BY id DESC")              //get notes
    List<note_model>getAll();

    @Query("UPDATE notes SET title=:title,mynotes=:notes WHERE id=:id")   //update table
    void update(int id,String title,String notes);

    @Query("UPDATE notes SET pinned=:pin WHERE id=:id")
    void pin(int id,boolean pin);

}
