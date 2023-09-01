package com.example.medic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.medic.adapters.health_news_adapter;
import com.example.medic.api_and_model.NewsModel;
import com.example.medic.api_and_model.Retrofit_instance;
import com.example.medic.api_and_model.Retrofit_interface;
import com.example.medic.api_and_model.articles;
import com.example.medic.sidemenu.Notes;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private ImageView menu;
    private NavigationView navigation_menu;
    private DrawerLayout drawerLayout;

    private Retrofit_interface retro_interface;

    private ArrayList<articles> news;
    private health_news_adapter ha;
    private RecyclerView rc;
    private ProgressBar pb;
    private Button notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        menu=findViewById(R.id.menu_side);
        navigation_menu=findViewById(R.id.navigation_menu);
        drawerLayout=findViewById(R.id.drawer);
        //notes=findViewById();

        rc=findViewById(R.id.recycler);
        pb=findViewById(R.id.progress_bar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigation_menu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.menu_notes) {
                    //Toast.makeText(HomeActivity.this, "pressed", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeActivity.this, Notes.class);
                    startActivity(intent);
                }
                return true;
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        news=new ArrayList<>();

        ha=new health_news_adapter(news,this);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(ha);
        ha.notifyDataSetChanged();
        display_health_news();
        rc.setVisibility(View.VISIBLE);
    }
    public void display_health_news()
    {

        pb.setVisibility(View.VISIBLE);
        String url="https://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=99701a83819b4675af200302f724fc20";

        retro_interface=Retrofit_instance.getRetrofit().create(Retrofit_interface.class);
        Call<NewsModel> call;
        call=retro_interface.getNews(url);

        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {

                //Toast.makeText(HomeActivity.this, ".....", Toast.LENGTH_SHORT).show();  news recieved


                NewsModel nw=response.body();
                ArrayList<articles>al=nw.getArticles();
                for(articles a:al)
                {
                    news.add(new articles(a.getTitle(),a.getDescription(),a.getUrl(),a.getUrlToImage(),a.getContent()));
                }
                ha.notifyDataSetChanged();
                pb.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Service Unavailable", Toast.LENGTH_SHORT).show();
            }
        });
ha.notifyDataSetChanged();
    }


}