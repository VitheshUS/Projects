package com.example.ult_news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class News_Details extends AppCompatActivity {

    private String title;
    private String imgurl;
    private String content;
    private String description;
    private String site_url;

    private ImageView img;
    private TextView tlt,des,con;
    private FloatingActionButton b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        title=getIntent().getStringExtra("title");
        imgurl=getIntent().getStringExtra("image");
        content=getIntent().getStringExtra("content");
        description=getIntent().getStringExtra("description");
        site_url=getIntent().getStringExtra("site_url");
        tlt=findViewById(R.id.title);
        des=findViewById(R.id.description);
        con=findViewById(R.id.content);
        img= (ImageView) findViewById(R.id.imgs);
        tlt.setText(title);
        des.setText(description);
        con.setText(content);
        Picasso.get().load(imgurl).into(img);

        b1=findViewById(R.id.visit);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(site_url));
                startActivity(i);
            }
        });
    }
}