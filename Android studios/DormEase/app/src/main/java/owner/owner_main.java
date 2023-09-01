package owner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dormease.R;
import com.example.dormease.starting_activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import DOB_class.owner_dob;
import owner.fragments.*;
public class owner_main extends AppCompatActivity {

ImageView pgs,addpg,logout;
BottomNavigationView bottom_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_main);

        String username= getIntent().getStringExtra("username");


        displayfragment(new personal_details(username));


        bottom_bar=findViewById(R.id.owner_navigatio_bar);
        bottom_bar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.personal)
                {
                    displayfragment(new personal_details(username));
                }
                else if(item.getItemId()==R.id.pg)
                {
                    displayfragment(new pg_details(username));

                }
                else if(item.getItemId()==R.id.image)
                {
                    displayfragment(new gallary(username));

                }
                else if(item.getItemId()==R.id.logout)
                {

                    AlertDialog.Builder builder = new AlertDialog.Builder(owner_main.this);
                    builder.setTitle("Logout");
                    builder.setMessage("Are you sure?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            Intent intent=new Intent(owner_main.this, starting_activity.class);
                            startActivity(intent);
                            finish();
                        }
                });

                    builder.setNegativeButton("No", null);

                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
                return true;
            }
        });
    }

    private void displayfragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout2,fragment);
        fragmentTransaction.commit();
    }
}