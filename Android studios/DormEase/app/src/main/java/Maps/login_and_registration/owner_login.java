package Maps.login_and_registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.MotionButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dormease.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import owner.owner_main;

public class owner_login extends AppCompatActivity {
private EditText username,password;
private MotionButton login;

    private FirebaseAuth firebaseAuth;

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://dormease-a30ae-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_login);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.outlinedLogin);

        firebaseAuth=FirebaseAuth.getInstance();


        ///////////////////////////////////////////////////////////  owner login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(owner_login.this, owner_main.class);

                String user=username.getText().toString();
                String pass=password.getText().toString();

                if(user.length()!=0&&pass.length()!=0)
                {
                  //  Toast.makeText(owner_login.this, "loading", Toast.LENGTH_SHORT).show();
                    databaseReference.child("owners").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(user)){
                            //     Toast.makeText(owner_login.this, "loading2", Toast.LENGTH_SHORT).show();
                                DataSnapshot usersnap=snapshot.child(user);
                                String pass1=usersnap.child("password").getValue(String.class);
                                if(pass1.equals(pass)){
                                    //   Toast.makeText(owner_login.this,"Login Successful", Toast.LENGTH_SHORT).show();
                                intent.putExtra("username",user);
                                startActivity(intent);
                                finish();
                                }
                                else
                                    Toast.makeText(owner_login.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                            }
                            else Toast.makeText(owner_login.this, "Owner does not Exist", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
                else Toast.makeText(owner_login.this, "Invalid Input", Toast.LENGTH_SHORT).show();

            }
        });
    }
}