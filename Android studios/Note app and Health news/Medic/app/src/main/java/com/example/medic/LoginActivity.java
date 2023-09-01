package com.example.medic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medic.DATABASE.database;

public class LoginActivity extends AppCompatActivity {
 private EditText uname;
 private EditText pass;
 private Button login;
 private Button newuser;
 private static database db=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uname=findViewById(R.id.username);
        pass=findViewById(R.id.password);
        login=findViewById(R.id.outlinedLogin);
        newuser=findViewById(R.id.new_user);

        db=new database(LoginActivity.this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String un=uname.getText().toString().strip();
                String pa=pass.getText().toString().strip();
                if(un==null || pa==null || un.length()==0|| pa.length()==0)
                    Toast.makeText(LoginActivity.this, "Incorrect details", Toast.LENGTH_SHORT).show();
                else userlogin(un,pa);
            }
        });


        //////////////////////////////////////////////////////////////////////////////Remove this after project completion////////
        Intent intent=new Intent(this, HomeActivity.class);
        login.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(intent);
                finish();
                return false;
            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, activity_register.class);
                startActivity(intent);
            }
        });
    }
    private void userlogin(String user,String pswd)
    {
if(db.checkuser(user,pswd)){
    Intent intent=new Intent(this, HomeActivity.class);
    startActivity(intent);
    finish();
}
else
    Toast.makeText(this, "Invalid User", Toast.LENGTH_SHORT).show();
    }













    public void thrd()
    {
//        String[] ab = {
//                "Believe you can and you're halfway there.",
//                "Life is a journey, not a destination.",
//                "Change the world by being yourself.",
//                "Every moment is a fresh beginning.",
//                "Never regret anything that made you smile.",
//                "Die with memories, not dreams.",
//                "Aspire to inspire before we expire.",
//                "Tough times never last but tough people do.",
//                "The best way to predict the future is to create it."
//        };
//        Random r=new Random();
//        int k=r.nextInt(ab.length-1);
//        String st=ab[k];
//        Thread t=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                String set="";
//                for(int i=0;i<st.length()-1;i++){
//                    set=set.concat(String.valueOf(st.charAt(i)));
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    try {
//                        quotes.setText("");
//                        Thread.sleep(20);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    quotes.setText("");
//                    quotes.setText(set);
//                }
//            }
//        });
//        t.start();
    }
}