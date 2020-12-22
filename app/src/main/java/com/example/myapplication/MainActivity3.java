package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        EditText edit=findViewById(R.id.time);
        Button btn=findViewById(R.id.setTime);
        SharedPreferences sharedPreferences=getSharedPreferences("Time", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("time",45);
        editor.apply();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "onClick: clicked");
                String timeForAlarm=edit.getText().toString();
                if(timeForAlarm.equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter a input",Toast.LENGTH_SHORT).show();
                }else{
                    int timeValue = Integer.parseInt(timeForAlarm);
                    SharedPreferences sharedPreferences=getSharedPreferences("Time", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.clear();
                    editor.putInt("time", timeValue);
                    editor.apply();
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}