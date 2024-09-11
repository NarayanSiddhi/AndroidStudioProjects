package com.example.xmljson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Button btnXmlParse, btnJsonParse;
    TextView txtXmlData, txtJsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BindUiData();

        btnXmlParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadXmlData();
            }
        });

        btnJsonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadJsonData();
            }
        });
    }

    private void BindUiData() {
        btnXmlParse = (Button) findViewById(R.id.xmldata);
        btnJsonParse = (Button) findViewById(R.id.jsondata);
        txtXmlData = (TextView) findViewById(R.id.parseddataxml);
        txtJsonData = (TextView) findViewById(R.id.parseddatajson);
    }

    private void ReadXmlData() {
        try {
            StringBuilder stringValue = new StringBuilder();
            InputStream inputStream = getAssets().open("citydetails.xml");
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(inputStream,null);
            int eventType = parser.getEventType();
            while (eventType != parser.END_DOCUMENT) {
                String tagName;
                if(eventType == parser.START_DOCUMENT) { }
                else if(eventType == parser.START_TAG) {
                    tagName = parser.getName();
                    parser.next();
                    stringValue.append("\n"+tagName+" : "+ parser.getText());
                }
                else if(eventType == parser.END_TAG) { }
                eventType = parser.next();
            }
            txtXmlData.setText(stringValue);
        }
        catch (Exception ex) {
            Toast.makeText(this, "Err" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void ReadJsonData() {
        try {
            StringBuilder stringValue = new StringBuilder();
            InputStream inputStream = getAssets().open("citydetails.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String fileData = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(fileData);

            String cityName = jsonObject.getJSONObject("Details").getString("City_Name");
            if(cityName.length() > 0) {
                stringValue.append("\n"+"City_Name :"+ cityName);
            }

            String latitude = jsonObject.getJSONObject("Details").getString("Latitude");
            if(latitude.length() > 0) {
                stringValue.append("\n"+"Latitude :"+ latitude);
            }

            String longitude = jsonObject.getJSONObject("Details").getString("Longitude");
            if(longitude.length() > 0) {
                stringValue.append("\n"+"Longitude :"+ longitude);
            }

            String temperature = jsonObject.getJSONObject("Details").getString("Temperature");
            if(temperature.length() > 0) {
                stringValue.append("\n"+"Temperature :"+ temperature);
            }

            String humidity = jsonObject.getJSONObject("Details").getString("Humidity");
            if(humidity.length() > 0) {
                stringValue.append("\n"+"Humidity :"+ humidity);
            }

            txtJsonData.setText(stringValue);
        }
        catch (Exception ex) {
            Toast.makeText(this, "Err:" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}