package Maps.login_and_registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.MotionButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.dormease.R;

import admin.admin_main;

public class admin_login extends AppCompatActivity {
private EditText uname,password;
private MotionButton login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        uname=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.outlinedLogin);


        //////////////////////////////////////////////////////////////////           admin login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(admin_login.this, admin_main.class);
                String user=uname.getText().toString().strip();
                String pass=password.getText().toString().strip();
                if(user.equals("sahyadri")&&pass.equals("sahyadri")) {
                    startActivity(intent);
                    finish();
                }
            }
        });



    }
}