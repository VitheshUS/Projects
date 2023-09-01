package com.example.medic.DATABASE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    private static final String dbname="userdata";
    public database( Context context) {
        super(context, dbname, null, 1);
    }

    @Override // This method is accessed when we try to access the database for the first time
    public void onCreate(SQLiteDatabase db) {
        String query="create table users(id integer primary key autoincrement,username Text,email Text,password Text);";
        db.execSQL(query);
        System.out.println("Database created");
    }

      //this method is accessed when we try  to change the version of the table
      //when we try to alter the table or add tables
      @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean checkduplicateuser(String uname)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor c= db.rawQuery("select * from users where username=?",new String[]{uname});
        if(c.moveToFirst())return true;
        return false;

    }
    public boolean checkuser(String name,String pass)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor c= db.rawQuery("select * from users where username=? and password=?",new String[]{name,pass});
        if(c.moveToFirst())return true;
        return false;
    }
    public boolean register(String uname,String email,String pass)
    {
        ContentValues cv=new ContentValues();
        cv.put("username",uname);
        cv.put("email",email);
        cv.put("password",pass);
        SQLiteDatabase db=getWritableDatabase();
        long l=db.insert("users",null,cv);  //return -1 on failure
        System.out.println("operation complete with "+l);
        return l >= 0;
    }
}
