package adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dormease.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import DOB_class.owner_dob;
import PG_DETAILS.all_pg_details;
import interfaces.on_navigate_click;

public class pg_adapter extends RecyclerView.Adapter<pg_adapter.viewholder>{

    private RecyclerView recyclerView;

    List<DOB_class.owner_dob> owner_dob;
    Context context;
    on_navigate_click navigate_click;

    public pg_adapter(List<DOB_class.owner_dob> owner_dob, Context context, on_navigate_click navigate_click) {
        this.owner_dob = owner_dob;
        this.context = context;
        this.navigate_click = navigate_click;
    }

    private FirebaseAuth firebaseAuth;

    View v1;

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://dormease-a30ae-default-rtdb.firebaseio.com/");

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.pg_model_view,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        int pos=position;
        Map<String,String> mp=owner_dob.get(position).getMp();

        String name=owner_dob.get(position).getName();
        String phone=owner_dob.get(position).getPhone();
        String email=owner_dob.get(position).getEmail();
        String cost=mp.get("rent");
        String location=mp.get("address");
        String available=mp.get("available");

        holder.oname.setText("Owner name :"+name);
       // holder.ophone.setText("Phone :"+phone);
        //holder.oemail.setText("Email :"+email);
        holder.ocost.setText("Rent :"+cost);
        holder.olocation.setText("Location :"+location);
        holder.oavailable.setText("Rooms Available :"+available);
        holder.pgname.setText(mp.get("pgname"));


        if (mp.get("profile")!=null)
        {
        Uri uri= Uri.parse(mp.get("profile"));
        Glide.with(holder.itemView.getContext()).load(uri).into(holder.profile);

//            try {
//                InputStream inputStream = getContentResolver().openInputStream(uri);
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                // Optionally, you can resize the bitmap if needed
//                // bitmap = resizeBitmap(bitmap, desiredWidth, desiredHeight);
//                // Set the bitmap to the ImageView
//                holder.profile.setImageBitmap(bitmap);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
        }



        System.out.println(mp);

        holder.more_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "pressed", Toast.LENGTH_SHORT).show();

               Intent intent=new Intent(context, PG_DETAILS.all_pg_details.class);
               intent.putExtra("owner_dob",owner_dob.get(pos));
               context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return owner_dob.size();
    }


    public class viewholder extends RecyclerView.ViewHolder{
        TextView oname,ocost,olocation,oavailable,pgname;
        Button more_details;
        ImageView profile;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            oname=itemView.findViewById(R.id.owner_name);

            ocost=itemView.findViewById(R.id.pg_cost);
            olocation=itemView.findViewById(R.id.pg_location);
            oavailable=itemView.findViewById(R.id.available_rooms);
            more_details=itemView.findViewById(R.id.more_details);
            pgname=itemView.findViewById(R.id.pg_name);
            profile=itemView.findViewById(R.id.profile);
        }
    }
}
