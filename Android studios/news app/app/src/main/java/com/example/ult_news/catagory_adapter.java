package com.example.ult_news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class catagory_adapter extends RecyclerView.Adapter<catagory_adapter.ViewHolder> {
    private ArrayList<catagory_model> articles;
    private Context cont;
    Catagory_click_interface ca;



    public catagory_adapter(ArrayList<catagory_model> articles, Context cont, Catagory_click_interface ca) {
        this.articles = articles;
        this.cont = cont;
        this.ca = ca;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.catagory_news,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        catagory_model ml=articles.get(position);
        int pos=position;
holder.c_name.setText(ml.getC_name());

        Picasso
                .get()
                .load(ml.getImg_url())
                .into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ca.catagory_click(pos);
            }
        });


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }




    public interface Catagory_click_interface{
        void catagory_click(int position);
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView c_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img= itemView.findViewById(R.id.catagory_Image);
            c_name= itemView.findViewById(R.id.catagory_name);
        }
    }


}
