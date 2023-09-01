package com.example.medic.sidemenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.medic.DATABASE.RoomDB;
import com.example.medic.R;
import com.example.medic.adapters.note_adapter;
import com.example.medic.api_and_model.NotesClickListener;
import com.example.medic.api_and_model.note_model;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;
public class Notes extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
private RecyclerView recyclerView;
private com.example.medic.adapters.note_adapter note_adapter;
private List<note_model>notes=new ArrayList<>();
private RoomDB roomDB;                                          //instance to access the room database
    private note_model longclicknotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        ExtendedFloatingActionButton addnote = findViewById(R.id.extendedFloatingActionButton);
        SearchView search = findViewById(R.id.searchnotes);
        recyclerView=findViewById(R.id.note_recycler);

        roomDB=RoomDB.getInstance(Notes.this);
        notes=roomDB.mainDAO().getAll();
        updateReycler(notes);

        addnote.setOnClickListener(new View.OnClickListener() {                 //method to add notes
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Notes.this,addnotesActivity.class);
                startActivityForResult(intent,101);

                //CustomIntent.customType(Notes.this,"right-to-left");
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filternotes(query);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                filternotes(newText);
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==101)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                note_model new_notes= (note_model) data.getSerializableExtra("note");
                roomDB.mainDAO().insert(new_notes);
                notes.clear();                       //clear the recycler view
                notes.addAll(roomDB.mainDAO().getAll());
                note_adapter.notifyDataSetChanged();

            }
            else Toast.makeText(this, "there was a problem while adding notes", Toast.LENGTH_SHORT).show();
        }
        else if(requestCode==102)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                note_model new_notes= (note_model) data.getSerializableExtra("note");
                roomDB.mainDAO().update(new_notes.getId(),new_notes.getTitle(),new_notes.getNotes());
                notes.clear();                       //clear the recycler view
                notes.addAll(roomDB.mainDAO().getAll());
                note_adapter.notifyDataSetChanged();

            }
        }
    }
    private void filternotes(String newText) {
        //System.out.println("---------------------------------------------------------------------");
        List<note_model>filteredList=new ArrayList<>();
        for(note_model singlenotes:notes)
        {
            if(singlenotes.getTitle().toLowerCase().contains(newText.toLowerCase()))
            {
                //Toast.makeText(this, "found", Toast.LENGTH_SHORT).show();
                filteredList.add(singlenotes);
            }
            //else Toast.makeText(this, "not found", Toast.LENGTH_SHORT).show();
        }
        note_adapter.filterLst(filteredList);
    }
    private void updateReycler(List<note_model> notes) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));

        note_adapter=new note_adapter(Notes.this, notes, new NotesClickListener() {
            @Override
            public void onClick(note_model notes) {

                //on click notes

                Intent intent=new Intent(Notes.this,addnotesActivity.class);
                intent.putExtra("old_note",notes);
                startActivityForResult(intent,102);
            }
            @Override
            public void onLongClick(note_model notes, CardView cardView) {                      //on long click

            longclicknotes=new note_model();
            longclicknotes=notes;
            showpopup(cardView);
            }
        });
        recyclerView.setAdapter(note_adapter);
    }

    private void showpopup(CardView cardView) {
        PopupMenu popupMenu=new PopupMenu(this,cardView);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.pinnote:
                //clear the recycler view
                if(longclicknotes.isPinned())
                {
                    roomDB.mainDAO().pin(longclicknotes.getId(),false);
                }
                else{
                    roomDB.mainDAO().pin(longclicknotes.getId(),true);
                }
                notes.clear();                       //clear the recycler view
                notes.addAll(roomDB.mainDAO().getAll());
                note_adapter.notifyDataSetChanged();
                return true;

            case R.id.delete:
                roomDB.mainDAO().delete(longclicknotes);
                notes.clear();
                notes.addAll(roomDB.mainDAO().getAll());
                note_adapter.notifyDataSetChanged();
                return true;

            default:return false;
        }
    }
}