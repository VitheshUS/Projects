package com.example.myweather;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<hour> ah;
    private TextView chosen_city;
    private TextView temperature;
    private TextView Condition;
    private ImageView condition_img;
    private ImageView presimag;
    private EditText select_city;
    private ProgressBar pb;
    private RelativeLayout rv;
    private weather_adapter adp;
    private TextView datetime;


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_main);


        pb=findViewById(R.id.progress_bar);
        datetime=findViewById(R.id.date_and_time);
        rv=findViewById(R.id.relative_layout);
        chosen_city=findViewById(R.id.city_name);
        temperature=findViewById(R.id.temperature);
        Condition=findViewById(R.id.condition);
        condition_img=findViewById(R.id.condition_img);
        ImageView city_search = findViewById(R.id.city_search_button);
        select_city=findViewById(R.id.Enter_city);
        presimag=findViewById(R.id.image_present);
        RecyclerView rva = findViewById(R.id.recycler_view);


        AtomicReference<InputMethodManager> imm = new AtomicReference<>((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        imm.get().hideSoftInputFromWindow(select_city.getWindowToken(), 0);



        city_search.setOnClickListener(v -> {
            String  cityname=select_city.getText().toString();
            imm.set((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
            imm.get().hideSoftInputFromWindow(select_city.getWindowToken(), 0);
            getweather(cityname);
        });




        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                // Do something with the latitude and longitude values
                Toast.makeText(MainActivity.this, " got", Toast.LENGTH_SHORT).show();
            }
        };







        getweather("mangalore");

        ah=new ArrayList<>();

        adp=new weather_adapter(ah);
        rva.setAdapter(adp);
        adp.notifyDataSetChanged();


    }

    private void getweather(String city)
    {
        pb.setVisibility(View.VISIBLE);
        String url="https://api.weatherapi.com/v1/forecast.json?key=7e85bfc55e534a459b9125013230303&q="+city+"&days=1&aqi=yes&alerts=yes";

        Retrofit_interface retro = Retrofit_instance.getRetrofit().create(Retrofit_interface.class);


        Call<WeatherModel> call;
        call= retro.getWeatherDetails(url);

        call.enqueue(new Callback<WeatherModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<WeatherModel> call, @NonNull Response<WeatherModel> response) {

                //Toast.makeText(MainActivity.this, "recieved", Toast.LENGTH_SHORT).show();
                WeatherModel we=response.body();

                Current cu=null;
                Forecast fo=null;
                if (we != null) {
                    cu = we.getCurrent();
                    fo = we.getForecast();
                    System.out.println("++++++++=Value is not null");
                    if(cu!=null) {
                        System.out.println("current is not null");

                    }
                    if(fo==null)
                        System.out.println("forecast is null");


                } else {
                    Toast.makeText(MainActivity.this, "Invalid City", Toast.LENGTH_SHORT).show();
                    System.out.println("++++++++=Value is null");
                    return;
                }

                try {
                    assert cu != null;
                    printObjectValues(cu);
                } catch (IllegalAccessException e) {
                    System.out.println("Not possible");
                    throw new RuntimeException(e);

                }

                pb.setVisibility(View.GONE);
                rv.setVisibility(View.VISIBLE);

                chosen_city.setText(city);
                temperature.setText(cu.getTemp_c().concat("°C"));
                Condition.setText(cu.getCondition().getText());
                int a=Integer.parseInt(cu.getIs_day());
                System.out.println("-----------------------------");
                System.out.println(a);

                String dat=cu.getLast_updated();



                String[] sunnyQuotes = {
                        "Hello, sunshine!",
                        "Soak up the sun.",
                        "Sun's out, fun's out.",
                        "You are my sunshine.",
                        "Chase the sun.",
                        "Here comes the sun.",
                        "Life is better with sunshine.",
                        "Good vibes happen on the tides.",
                        "Sun-kissed.",
                        "I need vitamin SEA.",
                };

                String[] nightQuotes = {
                        "Night night.",
                        "Sweet dreams.",
                        "Stars in my eyes.",
                        "The night is magic.",
                        "All is calm, all is bright.",
                        "In the still of the night.",
                        "Darkness falls.",
                        "The moon is my guide.",
                        "Sail away into the night sky."
                };













String[] ab;
                presimag.setVisibility(View.VISIBLE);
                if(a>0)
                {
                    ab=sunnyQuotes;
                    Picasso.get().load("https://cdn.wallpapersafari.com/25/42/RtFUyC.jpg").into(presimag);
                }
                else{
                    ab=nightQuotes;
                    Picasso.get().load("https://w0.peakpx.com/wallpaper/704/199/HD-wallpaper-night-sky-background-forest-iphone-night-sky-stars.jpg").into(presimag);
                }

                Random r=new Random();
                int k=r.nextInt(ab.length-1);
                String st=ab[k];



                Thread t=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        String set="";
                        for(int i=0;i<st.length()-1;i++){
                            set=set.concat(String.valueOf(st.charAt(i)));
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                datetime.setText("");
                                Thread.sleep(20);

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            datetime.setText("");
                            datetime.setText(set);

                        }

                    }
                });
                t.start();








                Picasso.get().load("https:"+cu.getCondition().getIcon()).into(condition_img);


                assert fo != null;
                ArrayList<forecastday> fr=fo.getForecastday();
                forecastday fa=fr.get(fr.size()-1);
                ArrayList<hour>ho=fa.getHour();

                try {
                    printObjectValues(ho.get(ho.size()-1));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                ah.clear();

                ah.addAll(ho);

                adp.notifyDataSetChanged();


            }

            @Override
            public void onFailure(@NonNull Call<WeatherModel> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();

                Toast.makeText(MainActivity.this, "Invalid City", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void printObjectValues(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(obj);
            System.out.println(field.getName() + ": " + value);
        }
    }
}