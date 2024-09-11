package com.example.signuplogin;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText loginuser,loginpass;
    Button loginbtn;
    Integer clickCount=0;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Bundle bundle=getIntent().getExtras();
        String signupUsername = bundle.getString("Username");
        String signupPassword = bundle.getString("Password");
        BindUIElements();
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginUsername = loginuser.getText().toString();
                String loginPassword = loginpass.getText().toString();
                if(clickCount < 2) {
                    if(loginUsername.equals(signupUsername) && loginPassword.equals(signupPassword)) {
                        Toast.makeText(LoginActivity.this, "Successful Login", Toast.LENGTH_LONG).show();
                    }
                    else {
                        clickCount++;
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, "Failed Login Attempt", Toast.LENGTH_SHORT).show();
                    loginbtn.setEnabled(false);
                }
            }
        });
    }
    private void BindUIElements() {
        loginuser = (EditText) findViewById(R.id.uname);
        loginpass = (EditText) findViewById(R.id.pname);
        loginbtn = (Button) findViewById(R.id.logbtn);
    }
}
