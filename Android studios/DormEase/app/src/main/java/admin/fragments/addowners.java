package admin.fragments;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

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
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;


public class addowners extends Fragment {
private EditText name,phone,email,password,confirm_password;
private Button select_file;
private ExtendedFloatingActionButton add;
private ImageView file;
private Uri filepath;
ProgressBar pb;
StorageReference storageReference;


private FirebaseAuth firebaseAuth;

DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://dormease-a30ae-default-rtdb.firebaseio.com/");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        
        View root= inflater.inflate(R.layout.fragment_addowners, container, false);

       // Toast.makeText(getActivity(), "adding owners", Toast.LENGTH_SHORT).show();

//        roomDB=RoomDB.getInstance(getActivity());

        name=root.findViewById(R.id.username);
        phone=root.findViewById(R.id.phone);
        email=root.findViewById(R.id.email);
        password=root.findViewById(R.id.password);
        confirm_password=root.findViewById(R.id.confirmpassword);
        select_file=root.findViewById(R.id.select_file);

        file=root.findViewById(R.id.pdf);

        pb=root.findViewById(R.id.pb);


        add=root.findViewById(R.id.add_owner);
        firebaseAuth=FirebaseAuth.getInstance();



        select_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_pdf();
            }
        });

add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        pb.setVisibility(View.VISIBLE);


                String name1 = name.getText().toString();
                String phone1 = phone.getText().toString();
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                String cpassword1 = confirm_password.getText().toString();

                if(password1.length()>=8)
        if (password1.equals(cpassword1)) {
            if (name1.length() != 0 && phone1.length() != 0 && email1.length() != 0 && password1.length() != 0 && cpassword1.length() != 0 && filepath!=null){

                databaseReference.child("owners").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(name1)){
                            Toast.makeText(getContext(), "Owner Already Exists", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            databaseReference.child("owners").child(name1).child("phone").setValue(phone1);
                            databaseReference.child("owners").child(name1).child("email").setValue(email1);
                            databaseReference.child("owners").child(name1).child("password").setValue(password1);
                            uploadpdf();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            else Toast.makeText(getContext(), "Invalid Values", Toast.LENGTH_SHORT).show();
        }
                else Toast.makeText(getContext(), "password too small", Toast.LENGTH_SHORT).show();

    }
});

        return root;
    }

    private void uploadpdf() {
        storageReference= FirebaseStorage.getInstance().getReference("license/"+name.getText().toString());
        storageReference.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        databaseReference.child("owners")
                                .child(name.getText().toString())
                                .child("verification")
                                .setValue(uri.toString());
                        Toast.makeText(getContext(), "Owner added Successfully", Toast.LENGTH_SHORT).show();

                        name.setText("");
                        phone.setText("");
                        email.setText("");
                        password.setText("");
                        confirm_password.setText("");
                        file.setVisibility(View.GONE);
                        pb.setVisibility(View.GONE);

                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void select_pdf() {


        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select a pdf file"),101);

//        Dexter.withContext(getContext())
//                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                .withListener(new PermissionListener() {
//                    @Override
//                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//
//
//
//                        //Toast.makeText(getContext(), "starting", Toast.LENGTH_SHORT).show();
//
//                        Log.d("current","permission granted");
//                    }
//
//                    @Override
//                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
//                        Toast.makeText(getContext(), "permission deniedS", Toast.LENGTH_SHORT).show();
//                        Log.d("current","permission denied");
//
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
//                        Toast.makeText(getContext(), "permission needed", Toast.LENGTH_SHORT).show();
//                        permissionToken.continuePermissionRequest();
//                        Log.d("current","permission rational");
//
//                    }
//                });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==101 && data!=null)
        {
            filepath=data.getData();
            file.setVisibility(View.VISIBLE);

        }
    }
}