package com.example.medic.sidemenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.medic.R;
import com.example.medic.api_and_model.note_model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class addnotesActivity extends AppCompatActivity {
private EditText title,inputnotes;
private Toolbar toolbar;
private ImageView save;

private note_model notes;

private boolean isoldnotes=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnotes);
        title=findViewById(R.id.input_title);
        inputnotes=findViewById(R.id.input_notes);
        toolbar=findViewById(R.id.toolbar_notes);
        save=findViewById(R.id.view_save);



//////////////////////////////////////////////////////////////////
        try {
            notes = (note_model) getIntent().getSerializableExtra("old_note");
            title.setText(notes.getTitle());
            inputnotes.setText(notes.getNotes());

            if(notes!=null)
             isoldnotes=true;
        }
        catch (Exception e)
        {
           // Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
////////////////////////////////////////////////////////////////


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tl=title.getText().toString();
                String des=inputnotes.getText().toString();
                SimpleDateFormat formatter=new SimpleDateFormat("EEE, d MMM yyyy HH:mm a");   // date format
                Date date=new Date();
                if(tl.isEmpty() || des.isEmpty())
                    Toast.makeText(addnotesActivity.this, "invalid input", Toast.LENGTH_SHORT).show();
                else {

                    if(!isoldnotes)
                       notes=new note_model();

                    if(notes==null)
                        Toast.makeText(addnotesActivity.this, "notes object is null", Toast.LENGTH_SHORT).show();

                    notes.setTitle(tl);
                    notes.setNotes(des);
                    notes.setDate(formatter.format(date));

                    Intent intent=new Intent(addnotesActivity.this,Notes.class);
                    intent.putExtra("note",notes);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }
            }
        });
    }
}