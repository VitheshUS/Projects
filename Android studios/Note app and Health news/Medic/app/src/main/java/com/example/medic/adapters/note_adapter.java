package com.example.medic.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.R;
import com.example.medic.api_and_model.NotesClickListener;
import com.example.medic.api_and_model.note_model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class note_adapter extends RecyclerView.Adapter<note_adapter.viewholder>{
private Context context;
private List<note_model> noteList;
private NotesClickListener listener;

    public note_adapter(Context context, List<note_model> noteList, NotesClickListener listener) {
        this.context = context;
        this.noteList = noteList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notesdetails,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.title.setText(noteList.get(position).getTitle());
        holder.title.setSelected(true);

        holder.content.setText(noteList.get(position).getNotes());
        holder.date.setText(noteList.get(position).getDate());

        boolean pinned=noteList.get(position).isPinned();
        if(pinned) {
            //Toast.makeText(context, "pinned", Toast.LENGTH_SHORT).show();
            holder.pin.setVisibility(View.VISIBLE);
        }
        else {
            //Toast.makeText(context, "nothing is pinned", Toast.LENGTH_SHORT).show();
            holder.pin.setVisibility(View.GONE);
        }


        int color=randomColor();
        holder.backlayout.setBackgroundColor(holder.itemView.getResources().getColor(color,null));

        holder.notescardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(noteList.get(holder.getAdapterPosition()));
            }
        });

        holder.notescardview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongClick(noteList.get(holder.getAdapterPosition()),holder.notescardview);
                return true;
            }
        });

    }

    private int randomColor()
    {
        List<Integer>color=new ArrayList<>();
       // color.add(R.color.light_blue);
        color.add(R.color.silver);
        color.add(R.color.lightgrey);
        Random rand=new Random();
        int randomcolor=rand.nextInt(color.size());
        return color.get(randomcolor);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void filterLst(List<note_model> al)
    {
        noteList=al;
        notifyDataSetChanged();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView content;
        private ImageView pin;
        private TextView date;
        private LinearLayout backlayout;
        private CardView notescardview;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            content=itemView.findViewById(R.id.content);
            date=itemView.findViewById(R.id.date);
            pin=itemView.findViewById(R.id.pin);
            backlayout=itemView.findViewById(R.id.noteslayout);
            notescardview=itemView.findViewById(R.id.notes_container);
        }
    }
}
