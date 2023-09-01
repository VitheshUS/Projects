package Maps.login_and_registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.MotionButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dormease.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class user_register extends AppCompatActivity {
    private MotionButton register;
    private EditText cpassword,password,email;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        register=findViewById(R.id.register);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        cpassword=findViewById(R.id.cpassword);


        firebaseAuth=FirebaseAuth.getInstance();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em=email.getText().toString();
                String pass=password.getText().toString();
                String cpass=cpassword.getText().toString();
                if(em.isEmpty() || pass.isEmpty() || cpass.isEmpty())
                {
                    Toast.makeText(user_register.this, "invalid input", Toast.LENGTH_SHORT).show();
                }
                else if(pass.equals(cpass))
                {
                    firebaseAuth.createUserWithEmailAndPassword(em,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                             if(task.isSuccessful()) {
                                 Toast.makeText(user_register.this, "Registration successfull", Toast.LENGTH_SHORT).show();
                                 finish();
                             }
                             else
                                 Toast.makeText(user_register.this, "unsuccessfull", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                else Toast.makeText(user_register.this, "please confirm the password", Toast.LENGTH_SHORT).show();

            }
        });

    }






}