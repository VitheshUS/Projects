package com.example.ult_news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.preference.PreferenceGroup;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements catagory_adapter.Catagory_click_interface{
    @Override
    public void catagory_click(int position) {

        get_news(news_catagory.get(position).getC_name());

    }
    public void bbc()
    {
        Toast.makeText(this, "=======================", Toast.LENGTH_SHORT).show();
    }
    //    99701a83819b4675af200302f724fc20   key

    private RecyclerView catagories;
    private RecyclerView news;
    private ProgressBar pb;
   private ArrayList<articles> news_article;
   private ArrayList<catagory_model> news_catagory;
   private catagory_adapter cd;
   private news_adapter nd;
private RetrofitAPI retro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        catagories=findViewById(R.id.catagory);
        news=findViewById(R.id.allnews);
        pb=findViewById(R.id.progressbar);

        news_catagory=new ArrayList<>();
        news_article=new ArrayList<>();



        cd=new catagory_adapter(news_catagory,this,this::catagory_click);
        nd=new news_adapter(news_article,this);

        //  set this adapter to our recycler view

        news.setLayoutManager(new LinearLayoutManager(this));
        news.setAdapter(nd);
        catagories.setAdapter(cd);

get_catagory();
get_news("All");
        nd.notifyDataSetChanged();
    }
    public void hey()
    {
        Toast.makeText(this, "successfull----------------", Toast.LENGTH_SHORT).show();
    }

    private void get_catagory()
    {

       news_catagory.add(new catagory_model("All","https://images.unsplash.com/photo-1499714608240-22fc6ad53fb2?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80"));
        news_catagory.add(new catagory_model("Science","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwwZT81QhJqlZUP84iVz2rvYmZLETtLoRIaA&usqp=CAU"));
        news_catagory.add(new catagory_model("Business","https://images.unsplash.com/photo-1517048676732-d65bc937f952?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"));
        news_catagory.add(new catagory_model("Entertainment","https://www.analyticsinsight.net/wp-content/uploads/2021/01/Media-and-Entertainment.jpg"));
        news_catagory.add(new catagory_model("General","https://images.unsplash.com/photo-1494059980473-813e73ee784b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1169&q=80"));
        news_catagory.add(new catagory_model("Health","https://images.unsplash.com/photo-1526256262350-7da7584cf5eb?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"));
        news_catagory.add(new catagory_model("Sports","https://images.unsplash.com/photo-1612872087720-bb876e2e67d1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1307&q=80"));
        news_catagory.add(new catagory_model("Technology","https://images.unsplash.com/photo-1531297484001-80022131f5a1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=820&q=80"));
cd.notifyDataSetChanged();

    }

    private void get_news(String cat) {

        pb.setVisibility(View.VISIBLE);
        news_article.clear();

        String url = "https://newsapi.org/v2/top-headlines?country=in&category="+cat+"&apiKey=99701a83819b4675af200302f724fc20";
        String allnews = "https://newsapi.org/v2/top-headlines?country=in&apiKey=99701a83819b4675af200302f724fc20";

        retro=Retrofitinstance.getRetrofit().create(RetrofitAPI.class);

        Call<NewsModel> call;

        call=cat.equals("All")?retro.getallnews(allnews):retro.getallnews(url);


        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {

                Toast.makeText(MainActivity.this, cat, Toast.LENGTH_SHORT).show();



                NewsModel newsModel=response.body();
                pb.setVisibility(View.GONE);


                ArrayList<articles> art=newsModel.getarticles();

               for(int i=0;i<art.size();i++)
                        news_article.add(new articles(
        art.get(i).getTitle(),
        art.get(i).getDescription(),
        art.get(i).getUrlToImage(),
        art.get(i).getUrl(),
        art.get(i).getContent()
        ));
nd.notifyDataSetChanged();
            }


            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

}












