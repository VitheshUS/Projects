package owner.fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dormease.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import DOB_class.owner_dob;
import adapters.gallary_adapter;
import admin.fragments.owners;
import interfaces.photo_click;
import interfaces.refresh_after_delete;
import view_photo_intent.view_photo;

public class gallary extends Fragment {

    private ExtendedFloatingActionButton add,select;
    ImageView img;
    RecyclerView recyclerView;
    Uri imageuri;
    int id=0;

    List<Uri>li;
    StorageReference storageReference;
    ProgressBar progressBar;
    owner_dob owner_dob;

    FirebaseStorage firebaseStorage;

    private FirebaseAuth firebaseAuth;

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://dormease-a30ae-default-rtdb.firebaseio.com/");



    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int READ_EXTERNAL_STORAGE_PERMISSION_REQUEST = 2;
    String name;
    public gallary(String name) {
        // Required empty public constructor
        this.name=name;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_gallary, container, false);

        add=view.findViewById(R.id.add_photo);
        select=view.findViewById(R.id.select_photo);
        img=view.findViewById(R.id.selected_image);

        recyclerView=view.findViewById(R.id.photo_recyclerview);

        progressBar=view.findViewById(R.id.progress_bar);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference(name);


        progressBar.setVisibility(View.VISIBLE);


////////////////////////////////////////////////////////////////////////////////////////////////////////  Retrieve data from cloud storage

        storageReference.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                li = new ArrayList<>();

                int totalItems = listResult.getItems().size();
                if(totalItems==0)progressBar.setVisibility(View.GONE);

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
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });



        databaseReference.child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageuri==null)
                    Toast.makeText(getContext(), "Please select an Image", Toast.LENGTH_SHORT).show();
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    img.setVisibility(View.GONE);
                    uploadimage();

                }
            }
        });



        // Inflate the layout for this fragment
        return view;
    }

    private void display(List<Uri> li) {

        progressBar.setVisibility(View.GONE);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        gallary_adapter gallaryAdapter=new gallary_adapter(li, getContext(), name, new photo_click() {
            @Override
            public void on_photo_click(String uri) {
                //Toast.makeText(getContext(), uri, Toast.LENGTH_SHORT).show();//// method not used
                Intent intent=new Intent(getContext(), view_photo.class);
                intent.putExtra("uri",uri.toString());
                intent.putExtra("name",name);
                intent.putExtra("mode",2);
                startActivityForResult(intent,101);
            }

        },true);
        recyclerView.setAdapter(gallaryAdapter);
    }


    private void uploadimage() {

        SimpleDateFormat format=new SimpleDateFormat("yyyy_MM_dd_HH__mm__ss", Locale.US);
        Date now=new Date();
        String filename=format.format(now);

        storageReference= FirebaseStorage.getInstance().getReference(name+"/"+filename);
        storageReference.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getContext(), "upload successfull", Toast.LENGTH_SHORT).show();
                 progressBar.setVisibility(View.GONE);
                change();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "upload failed", Toast.LENGTH_SHORT).show();

                System.out.println(e);
                 progressBar.setVisibility(View.GONE);

            }
        });
    }

    private void selectImage() {

        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100 && data!=null && data.getData()!=null)
        {
            imageuri=data.getData();
            img.setImageURI(imageuri);
        }
        if(requestCode==101&&resultCode==1)
        {
            Toast.makeText(getContext(), "Image deleted successfully", Toast.LENGTH_SHORT).show();
            change();
        }
    }

    public void change()
    {
        Fragment fragment=new gallary(name);
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.commit();
    }
}