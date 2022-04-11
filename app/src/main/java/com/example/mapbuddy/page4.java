//package com.example.mapbuddy;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class page4 extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_page4);
//    }
//}

package com.example.mapbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;



public class page4 extends AppCompatActivity {

    AutoCompleteTextView location_box ;
    AutoCompleteTextView destination_box ;
    Spinner items ;
    Spinner items2 ;
    ArrayList<String> places = new ArrayList<String>(Arrays.asList(  "Administration Block" , "Auditorium" , "Back Gate"  , "Boys Hostel" , "CV Raman Block" ,
            "Canteen" , "Civil Enginnering Department" , "Computer Department" , "Electrical Engineering Department" , "Electronics Engineering Department" ,
            "Girls Hostel" , "Ground" , "Health Dispensary" , "Lal Chowk" , "Library"  , "MBA Park" , "Main Gate" , "Mechanical Engineering Department" ,
            "Photocopy Shop" , "Royal Enfield Workshop" , "Shakuntalam Hall" , "Sports Office" , "Student Window" , "VC Office") ) ;
    private Object AdapterView;

    boolean fil ,fid ;

    public void initialise(){
        fil = true ;
        fid = true ;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(page4.this , android.R.layout.simple_spinner_dropdown_item , places) ;
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(page4.this , android.R.layout.simple_spinner_dropdown_item ,places ) ;

        location_box.setAdapter(adapter);
        items.setAdapter(adapter2);

        items.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                if(fil){
                    location_box.setHint("Enter your location...");
                    fil = false ;
                }
                else {
                    String text = items.getSelectedItem().toString();
                    location_box.setText(text);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });


        destination_box.setAdapter(adapter);
        items2.setAdapter(adapter2);

        items2.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                if(fid){
                    destination_box.setHint("Enter your Destination...");
                    fid = false ;
                }
                else {
                    String text = items2.getSelectedItem().toString();
                    destination_box.setText(text);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);

        location_box = (AutoCompleteTextView) findViewById(R.id.location ) ;
        destination_box = (AutoCompleteTextView) findViewById(R.id.destination ) ;
        items  = (Spinner) findViewById(R.id.items ) ;
        items2  = (Spinner) findViewById(R.id.items2 ) ;

        initialise();

    }

    public void gomethod(View view){

        String start = location_box.getText().toString();
        String end = destination_box.getText().toString();

        if(start.equals("") || end.equals("")){
            Toast.makeText(this, "Inappropriate location or destination", Toast.LENGTH_SHORT).show();
            return ;
        }
        Intent intent = new Intent(this, pathshower.class);
        intent.putExtra("start", start);
        intent.putExtra("end", end);
        startActivity(intent);

    }
}

;