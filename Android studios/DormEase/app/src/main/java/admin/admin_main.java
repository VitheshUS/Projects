package admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.dormease.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import admin.fragments.*;
import room_fragment.rooms;

public class admin_main extends AppCompatActivity {
private ImageView menu,rooms,owners,addowners;
BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        replacefragment(new rooms());





//        rooms.setOnClickListener(new View.OnClickListener() {    //room fragments
//            @Override
//            public void onClick(View v) {
//
//                replacefragment(new rooms());
//               // rooms.setBackgroundColor(Color.WHITE);
//
//            }
//        });
//
//        owners.setOnClickListener(new View.OnClickListener() {       //owner fragment
//            @Override
//            public void onClick(View v) {
//
//                replacefragment(new owners());
//               // owners.setBackgroundColor(Color.WHITE);
//
//            }
//        });
//
//        addowners.setOnClickListener(new View.OnClickListener() {     //add owner fragment
//            @Override
//            public void onClick(View v) {
//
//                replacefragment(new addowners());
//               // addowners.setBackgroundColor(Color.WHITE);
//
//            }
//        });


        navigationView=findViewById(R.id.admin_navigation_bar);

        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.admin_pg)
                {
                    replacefragment(new rooms());
                }
                else if(item.getItemId()==R.id.admin_owner)
                {
                    replacefragment(new owners());
                }
                else if(item.getItemId()==R.id.admin_add_owner)
                {
                    replacefragment(new addowners());

                }
                return true;
            }
        });
    }

    private void replacefragment(Fragment fragment) {

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
    }


}