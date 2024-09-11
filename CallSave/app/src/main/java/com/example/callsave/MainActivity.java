package com.example.callsave;

import static android.Manifest.permission.CALL_PHONE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    EditText e1;
    Button call,save,clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.edittext);
        call=findViewById(R.id.btn_call);
        save=findViewById(R.id.btn_save);
        clear=findViewById(R.id.btn_clear);
        e1.setShowSoftInputOnFocus(false);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MakeCall();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveContact();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e1.setText("");
            }
        });
    }
    public void numberClick(View view){
        String value = e1.getText().toString(); 

        if(view.getId()==R.id.btn_zero){
            value += "0";
        }
        else if(view.getId()==R.id.btn_One) {
            value += "1";
        }
        else if(view.getId()==R.id.btn_Two) {
            value += "2";
        }
        else if(view.getId()==R.id.btn_Three) {
            value += "3";
        }
        else if(view.getId()==R.id.btn_four) {
            value += "4";
        }
        else if(view.getId()==R.id.btn_five) {
            value += "5";
        }
        else if(view.getId()==R.id.btn_six) {
            value += "6";
        }
        else if(view.getId()==R.id.btn_seven) {
            value += "7";
        }
        else if(view.getId()==R.id.btn_eight) {
            value += "8";
        }
        else if(view.getId()==R.id.btn_nine) {
            value += "9";
        }
        else if(view.getId()==R.id.btn_zero) {
            value += "0";
        }
        else if(view.getId()==R.id.btn_star) {
            value += "*";
        }
        else if(view.getId()==R.id.btn_hash) {
            value += "#";
        }

        e1.setText(""+value);
    }

    private  void MakeCall()
    {
        try{
                if(e1.length()>0)
                {
                    Intent intent=new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:"+e1.getText().toString()));
                    if(ContextCompat.checkSelfPermission(getApplicationContext(),CALL_PHONE)== PackageManager.PERMISSION_GRANTED){
                        startActivity(intent);
                    }
                    else
                    {
                            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                            {
                                requestPermissions(new String[]{CALL_PHONE},1);
                            }
                    }
                }
            }
        catch (Exception ex){
            Toast.makeText(this, ""+ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }
    private void saveContact(){
        try{
            Intent intent=new Intent(ContactsContract.Intents.Insert.ACTION);
            intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            intent.putExtra(ContactsContract.Intents.Insert.PHONE,e1.getText().toString());
            startActivity(intent);
        }
        catch(Exception ex){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}