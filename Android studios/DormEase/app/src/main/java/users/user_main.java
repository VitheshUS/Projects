package users;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.dormease.R;
import com.example.dormease.starting_activity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import owner.owner_main;
import room_fragment.rooms;

public class user_main extends AppCompatActivity {
    private ImageView logout;
    private NavigationView navigation_menu;
    private DrawerLayout drawerLayout;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        logout=findViewById(R.id.logout1);

        drawerLayout=findViewById(R.id.drawer);

        firebaseAuth=FirebaseAuth.getInstance();

        replacefragment(new rooms());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(user_main.this);
                builder.setTitle("Logout");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebaseAuth.signOut();
                        Intent intent=new Intent(user_main.this, starting_activity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                builder.setNegativeButton("No", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


//        navigation_menu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                if(item.getItemId()==R.id.menu_logout){
//                    firebaseAuth.signOut();
//                    Intent intent=new Intent(user_main.this, starting_activity.class);
//                    startActivity(intent);
//                    finish();
//                }
//                return false;
//            }
//        });

//        menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(GravityCompat.START);
//            }
//        });

    }

    private void replacefragment(Fragment fragment) {

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
    }

}