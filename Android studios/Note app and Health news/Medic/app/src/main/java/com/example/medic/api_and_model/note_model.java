package com.example.medic.api_and_model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "notes")

public class note_model implements Serializable {

    @PrimaryKey(autoGenerate = true)          //make id as primary key and id will be auto generated
    @ColumnInfo(name = "id")
    int id=0;

    @ColumnInfo(name = "title")              //set column name
    String title="";

    @ColumnInfo(name = "mynotes")             //set column name
    String notes="";

    @ColumnInfo(name = "date")                //set column name
    String date="";

    @ColumnInfo(name="pinned")                //set column name
    boolean pinned=false;





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }
}
