package adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;
import com.bumptech.glide.Glide;
import com.example.dormease.R;

import java.util.List;

import DOB_class.owner_dob;
import view_photo_intent.view_photo;

public class gallary_adapter extends RecyclerView.Adapter<gallary_adapter.viewholder1>{

    List<Uri>li;
    Context context;
    String name;
    int id=0;
    interfaces.photo_click photo_click;
    owner_dob owner_dob;
    boolean access;

    public gallary_adapter(List<Uri> li, Context context,String name,interfaces.photo_click photo_click,boolean access) {
        this.li = li;
        this.context = context;
        this.name = name;
        this.photo_click=photo_click;
        this.access=access;
    }

    @NonNull
    @Override
    public viewholder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.gallary_model_view,parent,false);
        return new viewholder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder1 holder, int position) {


        Uri uri=li.get(position);
        Glide.with(holder.itemView.getContext()).load(uri).into(holder.image);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, view_photo.class);
                intent.putExtra("uri",uri.toString());
                intent.putExtra("name",name);

                if(access)
                {
                    photo_click.on_photo_click(uri.toString());
                }
                else
                 context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return li.size()

                ;
    }

    public class viewholder1 extends RecyclerView.ViewHolder{
        ImageView image;
        CardView cardView;

        public viewholder1(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.image);
            cardView=itemView.findViewById(R.id.gallary_cardview);

        }
    }
}
