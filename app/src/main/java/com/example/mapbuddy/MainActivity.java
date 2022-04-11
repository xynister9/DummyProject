package com.example.mapbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText name= findViewById(R.id.editTextTextPersonName);
        final EditText mail= findViewById(R.id.editTextTextEmailAddress);
        final EditText number= findViewById(R.id.editTextPhone);
        Button buttontwo=findViewById(R.id.button2);
        buttontwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String newname =name.getText().toString();
                final String newmail =mail.getText().toString();
                final String newnumber =number.getText().toString();
                if(newname.isEmpty() || newmail.isEmpty() || newnumber.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter your details :( ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent activity2intent = new Intent(getApplicationContext(), splashscreen.class);
                    startActivity(activity2intent);
                }
            }
        });
    }
}