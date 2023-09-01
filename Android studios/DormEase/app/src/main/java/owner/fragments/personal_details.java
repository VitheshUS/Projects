package owner.fragments;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dormease.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class personal_details extends Fragment {
private TextView owner_name;
private TextInputLayout pgn,ph,em,de;
private EditText phone,email,description,pg_name;
ExtendedFloatingActionButton save1;

    private FirebaseAuth firebaseAuth;

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://dormease-a30ae-default-rtdb.firebaseio.com/");
String name;
    public personal_details(String name) {
        // Required empty public constructor
        this.name=name;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_personal_details, container, false);
        owner_name=view.findViewById(R.id.owner_name);
        phone=view.findViewById(R.id.phone);
        email=view.findViewById(R.id.email);
        pg_name=view.findViewById(R.id.pgname);
        description=view.findViewById(R.id.description);
        save1=view.findViewById(R.id.save);

        pgn=view.findViewById(R.id.layout_pgname);
        ph=view.findViewById(R.id.layout_phone);
        em=view.findViewById(R.id.layout_email);
        de=view.findViewById(R.id.layout_description);



        pgn.setHintTextColor(getResources().getColorStateList(R.color.hint_color_selector));
        ph.setHintTextColor(getResources().getColorStateList(R.color.hint_color_selector));
        em.setHintTextColor(getResources().getColorStateList(R.color.hint_color_selector));
        de.setHintTextColor(getResources().getColorStateList(R.color.hint_color_selector));

        firebaseAuth=FirebaseAuth.getInstance();

        owner_name.setText(name);

        save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone1 = phone.getText().toString();
                String email1 = email.getText().toString();
                String pg_name1 = pg_name.getText().toString();
                String des = description.getText().toString();

                if ( phone1.length() != 0 && email1.length() != 0 && pg_name1.length() != 0 && des.length() != 0)
                {

                    databaseReference.child("owners").child(name).child("pg").child("description").setValue(des);
                    databaseReference.child("owners").child(name).child("pg").child("pgname").setValue(pg_name1);
                    databaseReference.child("owners").child(name).child("email").setValue(email1);
                    databaseReference.child("owners").child(name).child("phone").setValue(phone1);

                    Toast.makeText(getContext(), "saved", Toast.LENGTH_SHORT).show();

                }

            }
        });


        databaseReference.child("owners").child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String email2=snapshot.child("email").getValue(String.class);
                String phone2=snapshot.child("phone").getValue(String.class);

                DataSnapshot usersnap=snapshot.child("pg");

                String pgname=usersnap.child("pgname").getValue(String.class);
                String des=usersnap.child("description").getValue(String.class);

                phone.setText(phone2);
                email.setText(email2);
                pg_name.setText(pgname);
                description.setText(des);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }
}