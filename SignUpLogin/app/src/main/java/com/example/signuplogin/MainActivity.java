package com.example.signuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText signuser,signpass;
    Button signbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        BindUIElements();

        signbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                String username=signuser.getText().toString();
                String password=signpass.getText().toString();

                String specialCharRegex=".*[@#$!%^&+=].*";
                String upperCaseRegex=".*[A-Z].*";
                String numberRegex=".*[0-9].*";
                String lowerCaseRegex=".*[a-z].*";

                if(username.length() <=0 && password.length() <=0){
                    Toast.makeText(MainActivity.this,"Username or Password cannot be empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!password.matches(specialCharRegex)){
                        Toast.makeText(MainActivity.this,"Password should contain special characters",Toast.LENGTH_SHORT).show();
                    }
                else if(!password.matches(upperCaseRegex)){
                        Toast.makeText(MainActivity.this,"Password should contain uppercase letters",Toast.LENGTH_SHORT).show();
                     }
                else if(!password.matches(numberRegex)){
                        Toast.makeText(MainActivity.this,"Password should contain numbers",Toast.LENGTH_SHORT).show();
                     }
                else if(!password.matches(lowerCaseRegex)){
                        Toast.makeText(MainActivity.this,"Password should contain lower case letters",Toast.LENGTH_SHORT).show();
                     }
                else if(password.length()<8){
                    Toast.makeText(MainActivity.this,"Password should be more than 8 characters",Toast.LENGTH_SHORT).show();
                    }
                else {
                        Bundle bundle=new Bundle();
                        bundle.putString("Username",username);
                        bundle.putString("Password",password);

                        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private void BindUIElements(){
        signuser= findViewById(R.id.uname);
        signpass= findViewById(R.id.pname);
        signbtn= findViewById(R.id.signbtn);
    }
}