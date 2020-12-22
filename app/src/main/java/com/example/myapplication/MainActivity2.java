package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity2 extends AppCompatActivity {
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Set<String> notfound=new HashSet<>();
        notfound.add("pai nai");
        lst=findViewById(R.id.lst);
        SharedPreferences sharedPreferences=getBaseContext().getSharedPreferences("Date",Context.MODE_PRIVATE);
        Set<String> today=sharedPreferences.getStringSet("date",notfound);
        ArrayList<String> arrayList = new ArrayList<>(today);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        lst.setAdapter(arrayAdapter);
        lst.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete date?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String toRemove=arrayList.get(position);
                                arrayList.remove(position);
                                Set<String>values= sharedPreferences.getStringSet("date",notfound);
                                values.remove(toRemove);
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.clear();
                                editor.putStringSet("date",values);
                                editor.apply();
                                arrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
                return false;
            }
        });
    }
}