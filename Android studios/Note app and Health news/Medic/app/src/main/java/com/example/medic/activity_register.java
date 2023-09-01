package com.example.medic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medic.DATABASE.database;

public class activity_register extends AppCompatActivity {

    private Button register;
    private EditText username,password,email;
    private static database db=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register=findViewById(R.id.register);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);

        db=new database(activity_register.this);

       register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname=username.getText().toString().strip();
                String pass=password.getText().toString().strip();
                String mail=email.getText().toString().strip();
                if(uname==null||pass==null||mail==null|| pass.length()<6 ||uname.length()==0||mail.length()==0)
                    Toast.makeText(activity_register.this, "Invalid values", Toast.LENGTH_SHORT).show();
                else if(db.checkduplicateuser(uname))
                    Toast.makeText(activity_register.this, "Account Exists!!", Toast.LENGTH_SHORT).show();
                else
                    Register(uname,pass,mail);
            }
        });

    }
    public void Register(String uname,String pass,String mail)
    {
        boolean status=db.register(uname,mail,pass);
        if(status) {
            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();}

    }
}