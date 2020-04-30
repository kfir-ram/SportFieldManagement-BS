package com.myapp.fieldsbs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.NameList;

import java.util.ArrayList;
import java.util.Objects;

public class FootballActivity extends AppCompatActivity {

    TextView a,b,c;
    Button btn;
    DatabaseReference fieldRef;
    FirebaseAuth fAuth;
    String id;
    ArrayList<String> activityList = new ArrayList<>();
    ArrayList<String> houseNumberList = new ArrayList<>();
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<String> operatorList = new ArrayList<>();
    ArrayList<String> typeList = new ArrayList<>();
    ArrayList<String> lightList = new ArrayList<>();
    ArrayList<String> neighborhoodList = new ArrayList<>();
    ArrayList<String> streetList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football);

        a = findViewById(R.id.text1);
        b = findViewById(R.id.text2);
        c = findViewById(R.id.text3);
        btn = findViewById(R.id.btn1);
        fAuth = FirebaseAuth.getInstance();
        id= fAuth.getUid();

        fieldRef = FirebaseDatabase.getInstance().getReference().child("Fields");
        fieldRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(Objects.requireNonNull(ds.child("Activity").getValue().toString().equals("פתוח ללא הגבלה"))
                    || Objects.requireNonNull(ds.child("Activity").getValue().toString().equals("פעיל"))
                    || Objects.requireNonNull(ds.child("Activity").getValue().toString().equals("כן"))
                    || Objects.requireNonNull(ds.child("Activity").getValue().toString().equals(""))
                    ){
                        if (Objects.requireNonNull(ds.child("SportType").getValue().toString().contains("כדורגל"))
                        || Objects.requireNonNull(ds.child("SportType").getValue().toString().contains("קטרגל"))
                        || Objects.requireNonNull(ds.child("SportType").getValue().toString().contains("קט רגל"))
                        || Objects.requireNonNull(ds.child("Type").getValue().toString().contains("כדורגל"))
                        || Objects.requireNonNull(ds.child("Type").getValue().toString().contains("ספורט משולב"))
                        || Objects.requireNonNull(ds.child("Type").getValue().toString().contains("מגרש משולב"))
                        || Objects.requireNonNull(ds.child("Type").getValue().toString().contains("אצטדיון"))
                        || Objects.requireNonNull(ds.child("Type").getValue().toString().contains("מיני פיץ"))
                        || Objects.requireNonNull(ds.child("Type").getValue().toString().contains("קט רגל"))
                        ){
                            i++;


                            activityList.add(ds.child("Activity").getValue().toString());
                            houseNumberList.add(ds.child("HouseNumbe").getValue().toString());
                            nameList.add(ds.child("Name").getValue().toString());
                            operatorList.add(ds.child("Operator").getValue().toString());
                            typeList.add(ds.child("Type").getValue().toString());
                            lightList.add(ds.child("lighting").getValue().toString());
                            neighborhoodList.add(ds.child("neighborho").getValue().toString());
                            streetList.add(ds.child("street").getValue().toString());

                        }
                    }
                }
                System.out.println("we've got " + i + " fields.");



                a.setText(activityList.get(0));
                b.setText(nameList.get(0));
                c.setText(typeList.get(0));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }













}