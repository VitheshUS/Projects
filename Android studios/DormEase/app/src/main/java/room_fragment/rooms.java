package room_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dormease.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DOB_class.owner_dob;
import adapters.owner_adapter;
import adapters.pg_adapter;
import admin.fragments.owners;
import interfaces.on_navigate_click;

public class rooms extends Fragment {

    private RecyclerView recyclerView;

    List<DOB_class.owner_dob> owner_dob;

    private FirebaseAuth firebaseAuth;
    ProgressBar pg;

    View v1;

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://dormease-a30ae-default-rtdb.firebaseio.com/");

    public rooms() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_rooms, container, false);

        pg=view.findViewById(R.id.progress_bar);
        pg.setVisibility(View.VISIBLE);




        recyclerView=view.findViewById(R.id.rooms_recycler_view);

        firebaseAuth=FirebaseAuth.getInstance();

        owner_dob=new ArrayList<>();

        databaseReference.child("owners").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                owner_dob.clear();
                for(DataSnapshot datasnap:snapshot.getChildren()) {
                    DataSnapshot pgsnap = datasnap.child("pg");

                    Map<String, String> mp = pgsnap.getValue(new GenericTypeIndicator<Map<String, String>>() {});

                    if (pgsnap.exists()) {

                        owner_dob.add(new owner_dob
                                (datasnap.getKey(),
                                        datasnap.child("email").getValue(String.class),
                                        datasnap.child("phone").getValue(String.class),
                                        datasnap.child("verification")
                                                .getValue(String.class)!=null?datasnap
                                                .child("verification").getValue(String.class):"",
                                        mp
                                ));
                    }
                }
                showpg(owner_dob);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return view;
    }

    public void showpg(List<owner_dob> ol)
    {

        pg.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        pg_adapter pg_adapter=new pg_adapter( owner_dob,getContext(), new on_navigate_click() {
            @Override
            public void navigate(String longitude, String latitude) {

                double lon=Double.parseDouble(longitude);
                double lat=Double.parseDouble(latitude);

            }
        });
        recyclerView.setAdapter(pg_adapter);
    }
//    public void change()
//    {
//        View view=v1;
//        Fragment fragment=new owners();
//        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.framelayout, fragment);
//        fragmentTransaction.commit();
//    }
}