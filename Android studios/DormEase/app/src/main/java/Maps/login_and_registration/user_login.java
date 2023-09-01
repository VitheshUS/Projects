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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import users.user_main;

public class user_login extends AppCompatActivity {
    private EditText uname;
    private EditText pass;
    private MotionButton login;
    private MotionButton newuser;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        uname=findViewById(R.id.username);
        pass=findViewById(R.id.password);
        login=findViewById(R.id.outlinedLogin);
        newuser=findViewById(R.id.new_user);

        firebaseAuth=FirebaseAuth.getInstance();

        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(user_login.this, user_register.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=uname.getText().toString();
                String password=pass.getText().toString();

                if(email.isEmpty()||password.isEmpty())
                    Toast.makeText(user_login.this, "Invalid Input", Toast.LENGTH_SHORT).show();

                else
                {

                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {
                                Intent intent=new Intent(user_login.this, user_main.class);
                                startActivity(intent);
                                finish();
                            }
                            else Toast.makeText(user_login.this, "login failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}