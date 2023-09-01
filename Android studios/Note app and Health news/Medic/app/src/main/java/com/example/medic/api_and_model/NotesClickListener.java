package com.example.medic.api_and_model;

import androidx.cardview.widget.CardView;

public interface NotesClickListener {
    void onClick(note_model notes);
    void onLongClick(note_model notes, CardView cardView);
}
