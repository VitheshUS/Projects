package PG_DETAILS.fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.RecoverySystem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dormease.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import DOB_class.owner_dob;
import adapters.gallary_adapter;
import interfaces.photo_click;
import interfaces.refresh_after_delete;

public class pg_photos extends Fragment {


String name;
RecyclerView recyclerView;
    List<Uri> li;
    owner_dob owner_dob;
    StorageReference storageReference;
    ProgressBar progressBar;

    FirebaseStorage firebaseStorage;

    private FirebaseAuth firebaseAuth;

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://dormease-a30ae-default-rtdb.firebaseio.com/");
    public pg_photos(String name) {
        // Required empty public constructor
        this.name=name;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_pg_photos, container, false);
       // Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();

        recyclerView=view.findViewById(R.id.image_recycler_view);

        progressBar=view.findViewById(R.id.progress_bar);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference(name);


        progressBar.setVisibility(View.VISIBLE);

        storageReference.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                li = new ArrayList<>();

                int totalItems = listResult.getItems().size();

                for (StorageReference st : listResult.getItems()) {
                    st.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            li.add(uri);
                            if (li.size() == totalItems) {
                                // All download URLs retrieved, call display(li)
                                display(li);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Handle failure
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle failure
            }
        });
        return view;
    }

    private void display(List<Uri> li) {

        progressBar.setVisibility(View.GONE);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        gallary_adapter gallaryAdapter=new gallary_adapter(li, getContext(), name, new photo_click() {
            @Override
            public void on_photo_click(String uri) {               //// method not used

            }
        },false);
        recyclerView.setAdapter(gallaryAdapter);
    }
}