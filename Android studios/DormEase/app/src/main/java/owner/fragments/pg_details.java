package owner.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dormease.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class pg_details extends Fragment {

    EditText address,distance,lon,lat,avail,rent;
    FloatingActionButton current_loc;
    ExtendedFloatingActionButton save1;
    ImageView profile;
    ProgressBar pb;

    Uri profile_uri;

    private FirebaseAuth firebaseAuth;

    private LocationManager locationManager;

    private LocationListener locationListener;
    private Context context;

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://dormease-a30ae-default-rtdb.firebaseio.com/");

    StorageReference storageReference;

    FusedLocationProviderClient fusedLocationProviderClient;
    private final static int REQUEST=100;



    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.context = context;
    }


String name;
    public pg_details(String name) {
this.name=name;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_pg_details, container, false);
        // Inflate the layout for this fragment

        address=view.findViewById(R.id.address);
        distance=view.findViewById(R.id.distance);
        lon=view.findViewById(R.id.longitude);
        lat=view.findViewById(R.id.latitude);
        avail=view.findViewById(R.id.available);
        rent=view.findViewById(R.id.rent);
        save1=view.findViewById(R.id.save2);
        current_loc=view.findViewById(R.id.current_loc);

        profile=view.findViewById(R.id.profile);

        pb=view.findViewById(R.id.pb);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectphoto();
            }
        });


        firebaseAuth=FirebaseAuth.getInstance();

        current_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setlocation();
            }
        });

        save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.setVisibility(View.VISIBLE);
                profile.setImageURI(profile_uri);
                String add=address.getText().toString();
                String dis=distance.getText().toString();
                String longt=lon.getText().toString();
                String latit=lat.getText().toString();
                String available=avail.getText().toString();
                String rent1=rent.getText().toString();
                if(add.length()!=0&&dis.length()!=0&&longt.length()!=0&&latit.length()!=0&&available.length()!=0&&rent1.length()!=0)
                {

                    databaseReference.child("owners").child(name).child("pg").child("address").setValue(add);
                    databaseReference.child("owners").child(name).child("pg").child("distance").setValue(dis);
                    databaseReference.child("owners").child(name).child("pg").child("longitude").setValue(longt);
                    databaseReference.child("owners").child(name).child("pg").child("latitude").setValue(latit);
                    databaseReference.child("owners").child(name).child("pg").child("available").setValue(available);
                    databaseReference.child("owners").child(name).child("pg").child("rent").setValue(rent1);
                    if(profile_uri!=null)
                     saveprofile(name);

                    pb.setVisibility(View.GONE);
                }
                else {
                    Toast.makeText(getContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                    pb.setVisibility(View.GONE);
                }
            }
        });

        databaseReference.child("owners").child(name).child("pg").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                address.setText(snapshot.child("address").getValue(String.class));
                distance.setText(snapshot.child("distance").getValue(String.class));
                lon.setText(snapshot.child("longitude").getValue(String.class));
                lat.setText(snapshot.child("latitude").getValue(String.class));
                avail.setText(snapshot.child("available").getValue(String.class));
                rent.setText(snapshot.child("rent").getValue(String.class));

                try {

                    Uri uri = Uri.parse(snapshot.child("profile").getValue(String.class));
                    Glide.with(getContext()).load(uri).into(profile);
                }catch (NullPointerException e)
                {
                    Toast.makeText(getContext(), "Please add a profile picture", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
    private void saveprofile(String name) {

        storageReference= FirebaseStorage.getInstance().getReference("profiles"+"/"+name);
        storageReference.putFile(profile_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String url=uri.toString();


                        databaseReference.child("owners").child(name).child("pg").child("profile").setValue(url);


                        Toast.makeText(getContext(), "saved", Toast.LENGTH_SHORT).show();
                        pb.setVisibility(View.GONE);
                    }
                });



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();

                System.out.println(e);


            }
        });
    }

    private void selectphoto() {

        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100&&data!=null) {
            profile_uri = data.getData();
            profile.setImageURI(profile_uri);
        }
    }
    private void setlocation() {

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(getActivity());

        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
        {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location!=null)
                    {
                        Geocoder geocoder=new Geocoder(getContext(), Locale.getDefault());
                        List<Address>addresses= null;
                        try {
                            addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                            lat.setText(String.valueOf(addresses.get(0).getLatitude()));
                            lon.setText(String.valueOf(addresses.get(0).getLongitude()));
                            address.setText(String.valueOf(addresses.get(0).getAddressLine(0)));
                            Toast.makeText(getContext(), "Location Updated", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }
            });
        }
        else{
            askpermission();
        }
    }

    private void askpermission() {

        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==REQUEST)
        {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                setlocation();
            }

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}