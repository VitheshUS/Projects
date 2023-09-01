package admin.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dormease.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DOB_class.owner_dob;
import DOB_class.pg_dob;
import adapters.owner_adapter;
import interfaces.refresh_after_delete;

public class owners extends Fragment {
    private RecyclerView recyclerView;

    List<DOB_class.owner_dob>owner_dob;

    private FirebaseAuth firebaseAuth;

    View v1;

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://dormease-a30ae-default-rtdb.firebaseio.com/");
    public owners() {/* Required empty public constructor*/}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_owners, container, false);
        v1=view;

        recyclerView=view.findViewById(R.id.owners_recycler_view);

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
         "",
         mp
        ));
                    }
                    else{
                        owner_dob.add(new owner_dob
                                (datasnap.getKey(),
                                        datasnap.child("email").getValue(String.class),
                                        datasnap.child("phone").getValue(String.class),
                                        "",
                                        new HashMap<String,String>()
                                ));
                    }
                }
                showOwner(owner_dob);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;

    }

    public void showOwner(List<owner_dob> ol)
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Toast.makeText(getContext(), String.valueOf(owner_dob.size()), Toast.LENGTH_SHORT).show();

        System.out.println(owner_dob);

        owner_adapter owner_adapter=new owner_adapter( getContext(), new refresh_after_delete() {
            @Override
            public void ondelete() {
                change();
            }
        },owner_dob);
        recyclerView.setAdapter(owner_adapter);
    }

    public void change()
    {
      //  View view=v1;
        Fragment fragment=new owners();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.commit();
    }
}