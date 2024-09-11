package com.example.countervalue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnStart, btnStop, btnReset;
    TextView txtCounter;

    Handler handler = new Handler();
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BindUiElements();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.post(IncrementCount);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(IncrementCount);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=0;
                txtCounter.setText(String.valueOf(count));
            }
        });
    }

    private void BindUiElements() {
        btnStart = (Button) findViewById(R.id.start);
        btnStop = (Button) findViewById(R.id.stop);
        btnReset=(Button)findViewById(R.id.reset);
        txtCounter=(TextView) findViewById(R.id.counter);
    }

    private Runnable IncrementCount=new Runnable() {
        @Override
        public void run() {
            try{
                count++;
                txtCounter.setText(String.valueOf(count));
                handler.postDelayed(this,1000);
            }
            catch(Exception ex){
                Toast.makeText(MainActivity.this, "Exception: "+ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    };
}