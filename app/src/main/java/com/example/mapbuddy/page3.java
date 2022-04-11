package com.example.mapbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class page3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

        Button buttonone=findViewById(R.id.buttonone);
        buttonone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(page3.this, "Welcome to Map Buddy :)", Toast.LENGTH_SHORT).show();

                Intent activity2intent= new Intent(getApplicationContext(),page4.class);
                startActivity(activity2intent);
            }
        });
    }
}