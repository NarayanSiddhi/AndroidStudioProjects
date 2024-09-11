package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;
import android.app.WallpaperManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.InputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Image View for User Understanding [Not Mandatory]
    ImageView imgView;
    Button btnChangeWallpaper;

    //Array of File name
    String[] wallpaperFileName;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind UI Elements
        BindUiElements();

        btnChangeWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    handler.postDelayed(SelectedImage , 1000);
                }
                catch (Exception ex) {
                    Toast.makeText(MainActivity.this, "Exception :"+ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void BindUiElements() {
        imgView = (ImageView) findViewById(R.id.bgwallpaper);
        btnChangeWallpaper = (Button) findViewById(R.id.button);
    }


//The Runnable interface should be implemented by any class whose instances are intended to be executed by a thread.
//The class must define a method of no arguments called run.

    private Runnable SelectedImage=new Runnable() {
        @Override
        public void run() {
            try {

//Why asset is used instead of Drawable.
//Drawable can pick based on a file name and cannot access the file without knowing the file name.
//So we used asset, Where AssetManager class can get all details of the files available inside asset folder

                AssetManager assetManager = getAssets();
                wallpaperFileName = assetManager.list("wallpapers");

                //Generate a Random Number between 0 and Total Number of File available in the folder
                int randomNumber = new Random().nextInt(wallpaperFileName.length);


//Context - Context defines methods that access system resources, retrieve application's static assets, check permissions, perform UI manipulations and many more.
//getContext - View.getContext() Returns the context the view is currently running in. Usually the currently active Activity.
//getApplicationContext() - Returns the context for the entire application (the process all the Activities are running inside of)

                //WallpaperManager responsible for changing or assigning Wallpaper to phone programmatically
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());


//InputStream are used for reading byte based data
//A bitmap is an array of bits that specify the color of each pixel in a rectangular array of pixels

                InputStream inputStream = assetManager.open("wallpapers/"+wallpaperFileName[randomNumber]);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

//Assigning the Bitmap image to Wallpaper
                wallpaperManager.setBitmap(bitmap);


//Assign to Image View -> Optional only if needed
                imgView.setImageBitmap(bitmap);

                //Repeat the same task after a regular interval. Time in MilliSec
                handler.postDelayed(this,5000);
            }
            catch (Exception ex) {
                Toast.makeText(MainActivity.this, "Exception :"+ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    };
}