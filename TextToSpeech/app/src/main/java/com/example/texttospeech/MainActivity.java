package com.example.texttospeech;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextToSpeech textToSpeech;
    Button btnSpeech;
    EditText txtSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSpeech= findViewById(R.id.text);
        btnSpeech= findViewById(R.id.btnChange);

        textToSpeech=new TextToSpeech(getApplicationContext(),
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status!=TextToSpeech.ERROR)
                            textToSpeech.setLanguage(Locale.US);
                    }
                });

        btnSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String textValue=txtSpeech.getText().toString();
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        textToSpeech.speak(textValue,TextToSpeech.QUEUE_FLUSH,null,null);
                    else
                        textToSpeech.speak(textValue,TextToSpeech.QUEUE_FLUSH,null);
                }
                catch (Exception ex){
                    Toast.makeText(MainActivity.this, ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        } );
    }
}