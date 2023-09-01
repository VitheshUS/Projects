package com.example.myweather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class weather_adapter extends RecyclerView.Adapter<weather_adapter.ViewHolder> {

    private final ArrayList<hour> we;

    public weather_adapter(ArrayList<hour> we) {
        this.we = we;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_adapter_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String tme = we.get(position).getTime().substring(11);
        holder.time.setText(tme);
        holder.temperature.setText(we.get(position).getTemp_c().concat("°C"));
        holder.wind_s.setText("".concat(we.get(position).getWind_kph().concat(" Kph")));


        String url = we.get(position).getCondition().getIcon();

        Picasso
                .get()
                .load("https:" + url)
                .into(holder.cond);

        int c=Integer.parseInt(we.get(position).getIs_day());
        if(c>0)
        {
            Picasso
                    .get()
                    .load("https://www.teahub.io/photos/full/61-617747_above-blue-sky-iphone-wallpaper-blue-sky-wallpaper.jpg")
                    .into(holder.backg);
        }
        else{

            Picasso
                    .get()
                    .load("https://edge.mwallpapers.com/photos/celebrities/space/night-sky-android-iphone-desktop-hd-backgrounds-wallpapers-1080p-4khd-wallpapers-desktop-background-android-iphone-1080p-4k-9i2o1.jpg")
                    .into(holder.backg);
        }

    }

    @Override
    public int getItemCount() {
        return we.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView time;
        private final TextView temperature;
        private final TextView wind_s;
        private final ImageView cond;
        private final ImageView backg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time1);
            temperature = itemView.findViewById(R.id.ad_temperature);
            wind_s = itemView.findViewById(R.id.wind_speed);
            cond = itemView.findViewById(R.id.condition_image);
            backg=itemView.findViewById(R.id.image_adp);

        }
    }


}
