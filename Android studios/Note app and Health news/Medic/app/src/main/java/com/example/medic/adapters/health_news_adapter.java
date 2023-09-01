package com.example.medic.adapters;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.R;
import com.example.medic.api_and_model.NewsModel;
import com.example.medic.api_and_model.articles;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class health_news_adapter extends RecyclerView.Adapter<health_news_adapter.viewholder>{
    private ArrayList<articles> news=new ArrayList<articles>();
    private final Context context;

    public health_news_adapter(ArrayList<articles> news, Context context) {
        System.out.println("------------------constructor called---------------------------");
        this.news = news;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        System.out.println("--------------------------onCreateViewHolder-------------------");

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.health_news_adapter,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        System.out.println("--------------------------onBindViewHolder-------------------");

        int p=position;
        holder.title.setText(news.get(p).getTitle());
        holder.content.setText(news.get(p).getDescription());
        Picasso.get().load(news.get(p).getUrlToImage()).into(holder.img);

        holder.visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(news.get(p).getUrl()));
                startActivity(context,i,null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return news.size();
    }  //very important ,Your app won't display any result if this line is missed

    public static class viewholder extends RecyclerView.ViewHolder{
        private Button visit;
        private TextView title;
        private TextView content;
        private ImageView img;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            visit=itemView.findViewById(R.id.visit);
            title=itemView.findViewById(R.id.title);
            content=itemView.findViewById(R.id.content);
            img=itemView.findViewById(R.id.news_image);
        }
    }
}
