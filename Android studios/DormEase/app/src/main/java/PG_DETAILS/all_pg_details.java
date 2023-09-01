package PG_DETAILS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.dormease.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import Maps.MapsFragment;
import PG_DETAILS.fragments.pg_photos;
import PG_DETAILS.fragments.pgdetails_user;
import admin.fragments.addowners;
import admin.fragments.owners;
import owner.fragments.pg_details;
import room_fragment.rooms;

public class all_pg_details extends AppCompatActivity {
    NavigationBarView navigationView;
    DOB_class.owner_dob owner_dob;
    FloatingActionButton navigate;

    String source_lat="";
    String source_lon="";
    String dest_lan="";
    String dest_lon="";

    FusedLocationProviderClient fusedLocationProviderClient;
    private final static int REQUEST=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_pg_details);

        owner_dob= (DOB_class.owner_dob) getIntent().getSerializableExtra("owner_dob");

        //Toast.makeText(this, owner_dob.getName(), Toast.LENGTH_SHORT).show();


        //int a=getIntent().getIntExtra("mode",0);
//        if(a==1)
//            replacefragment(new pg_photos(owner_dob.getName()));
//        else
             replacefragment(new pgdetails_user(owner_dob));

        navigationView=findViewById(R.id.admin_navigation_bar);

        navigate=findViewById(R.id.navigate);
        navigate.setVisibility(View.GONE);

        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(all_pg_details.this, "currently unavailable", Toast.LENGTH_SHORT).show();

                getsourceloc();
                getdestloc();
                navigateloc();

            }
        });

        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.user_pg)
                {
                    navigate.setVisibility(View.GONE);
                    replacefragment(new pgdetails_user(owner_dob));
                }
                else if(item.getItemId()==R.id.user_image)
                {
                    navigate.setVisibility(View.GONE);
                    replacefragment(new pg_photos(owner_dob.getName()));
                } else if (item.getItemId()==R.id.user_location) {
                    navigate.setVisibility(View.VISIBLE);

                    replacefragment(new MapsFragment(owner_dob));
                }
                return true;
            }
        });

    }


    private void navigateloc() {
        String uri = "geo:" + source_lat + "," + source_lon +
                "?q=" + dest_lan + "," + dest_lon;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps"); // Specify the package name to ensure it opens in Google Maps
        startActivity(intent);
    }

    private void getdestloc() {
        dest_lan=owner_dob.getMp().get("latitude");
        dest_lon=owner_dob.getMp().get("longitude");
    }

    private void getsourceloc() {

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(all_pg_details.this);

        if(ContextCompat.checkSelfPermission(all_pg_details.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
        {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location!=null)
                    {
                        Geocoder geocoder=new Geocoder(all_pg_details.this, Locale.getDefault());
                        List<Address> addresses= null;
                        try {
                            addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                            source_lat=String.valueOf(addresses.get(0).getLatitude());
                            source_lon=String.valueOf(addresses.get(0).getLongitude());

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

        ActivityCompat.requestPermissions(all_pg_details.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==REQUEST)
        {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                getsourceloc();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void replacefragment(Fragment fragment) {

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
    }
}