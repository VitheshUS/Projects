package adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dormease.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Map;

import DOB_class.owner_dob;
import interfaces.refresh_after_delete;


public class owner_adapter extends RecyclerView.Adapter<owner_adapter.viewholder>{
//List<owners> ownersList;
Context context;

interfaces.refresh_after_delete refresh;

List<DOB_class.owner_dob>ownersList;

    FirebaseAuth firebaseAuth;

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://dormease-a30ae-default-rtdb.firebaseio.com/");

    public owner_adapter(Context context, refresh_after_delete refresh, List<owner_dob> ownersList) {
        this.context = context;
        this.refresh = refresh;
        this.ownersList = ownersList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.owner_details_model_view,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        firebaseAuth=FirebaseAuth.getInstance();

        String oname=ownersList.get(position).getName();
        String ophone= String.valueOf(ownersList.get(position).getPhone());
        String oemail=ownersList.get(position).getEmail();

        holder.oname.setText(oname);
        holder.ophone.setText(ophone);
        holder.oemail.setText(oemail);

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_dailog_before_delete(oname);
            }
        });

    }
    public void show_dailog_before_delete(String oname)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete??");
        builder.setMessage("Do you really want to delete this owner?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseReference childRef=databaseReference.child("owners").child(oname);

                childRef.removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(context, "Owner Deleted", Toast.LENGTH_SHORT).show();
                                refresh.ondelete();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        builder.setNegativeButton("No", null);

        AlertDialog dialog = builder.create();
        dialog.show();


    }

    @Override
    public int getItemCount() {
        return ownersList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView oname,ophone,oemail;
        Button remove;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            oname=itemView.findViewById(R.id.oname);
            ophone=itemView.findViewById(R.id.ophone);
            oemail=itemView.findViewById(R.id.oemail);
            remove=itemView.findViewById(R.id.remove_owner);
        }
    }
}
