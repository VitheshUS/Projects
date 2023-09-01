package view_photo_intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dormease.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import DOB_class.owner_dob;
import PG_DETAILS.all_pg_details;
import interfaces.refresh_after_delete;

public class view_photo extends AppCompatActivity {

    ImageView imageView;
    Button delete;
    String name="";
    refresh_after_delete refresh_after_delete;
    owner_dob owner_dob;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photo);

        imageView=findViewById(R.id.image);
        delete=findViewById(R.id.delete);

        name=getIntent().getStringExtra("name");

        int mode=getIntent().getIntExtra("mode",0);

        if(mode!=2)delete.setVisibility(View.GONE);

       // refresh_after_delete=getIntent().getSerializableExtra("delete");

        String uri1=getIntent().getStringExtra("uri");
        Glide.with(this)
                .load(uri1)
                .into(imageView);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteImage(uri1);
            }
        });




    }



    private void deleteImage(String imageUri) {
        // Convert the image URI to a Firebase Storage reference
        StorageReference photoRef = FirebaseStorage.getInstance().getReferenceFromUrl(imageUri);

        // Delete the image
        photoRef.delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                       // Toast.makeText(view_photo.this, "Image deleted successfully", Toast.LENGTH_SHORT).show();
                          setResult(1);
                          finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(view_photo.this, "Failed to delete image", Toast.LENGTH_SHORT).show();
                        Log.e("DeleteImage", "Error deleting image", e);
                    }
                });


    }



}