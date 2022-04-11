package com.example.mapbuddy;

//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class pathshower extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pathshower);
//    }
//}

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
public class pathshower extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathshower);

        Bundle bundle = getIntent().getExtras();
        String start = bundle.getString("start");
        String end = bundle.getString("end");

        if(start.equals("") || end.equals("")){
            return ;
        }
        pathfinder thiss = new pathfinder();
        ArrayList<String> path = thiss.givePath(start,end) ;

        ListView direction = (ListView) findViewById(R.id.directionListview) ;

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 , path )  ;

        if(path.size()==0){
            Toast.makeText(this, "Inappropriate location or destination", Toast.LENGTH_SHORT).show();
        }
        direction.setAdapter(arrayAdapter);
        direction.setVisibility(View.VISIBLE);
    }

    public  void get_back(View view){
        Intent intent = new Intent(this, page4.class);
        startActivity(intent);
    }
}