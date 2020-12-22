package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1=findViewById(R.id.btn1);
        Button btn2=findViewById(R.id.btn2);
        Button setTime=findViewById(R.id.btn3);
        TextView txt=findViewById(R.id.textView);
        SharedPreferences sharedPreferences1=getBaseContext().getSharedPreferences("Time",Context.MODE_PRIVATE);
        int value=sharedPreferences1.getInt("time",10);
        String toShow="Time set for "+ value+" minutes";
        txt.setText(toShow);
        btn1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Set<String>dates=new HashSet<>();
                Set<String>empty=new HashSet<>();
                Calendar rightNow = Calendar.getInstance();
                int hour=LocalDateTime.now().getHour();
                int minute=LocalDateTime.now().getMinute();
                SharedPreferences sharedPreferences1=getBaseContext().getSharedPreferences("Time",Context.MODE_PRIVATE);
                int value=sharedPreferences1.getInt("time",10);
                String toShow="Time set for "+ value+" minutes";
                txt.setText(toShow);
                minute+=value;
                if(minute>60){
                    int divided= (int) Math.floor(minute/60);
                    hour+=divided;
                    minute-=(divided*60);

                }
                Intent intent=new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR,hour);
                intent.putExtra(AlarmClock.EXTRA_MINUTES,minute);
                startActivity(intent);
                Date currentTime = Calendar.getInstance().getTime();
                SharedPreferences sharedPreferences=getSharedPreferences("Date",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                if(sharedPreferences.contains("date")){
                    Set<String> today=sharedPreferences.getStringSet("date",empty);
                    today.add(currentTime.toString());
                    editor.clear();
                    editor.putStringSet("date",today);
                }else{
                    editor.putStringSet("date",dates);
                }
                editor.apply();


            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);
            }
        });
        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity3.class);
                startActivity(intent);
            }
        });
    }



}