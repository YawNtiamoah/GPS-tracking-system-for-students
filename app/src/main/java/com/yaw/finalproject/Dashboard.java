package com.yaw.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//VIEW SCHEDULE VIEW SCHEDULE VIEW SCHEDULE VIEW SCHEDULE VIEW SCHEDULE VIEW SCHEDULE VIEW SCHEDULE VIEW SCHEDULE VIEW SCHEDULE
public class Dashboard extends AppCompatActivity {
    TextView a,b,c,d,e;
    Button btn;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        a=(TextView)findViewById(R.id.txtMonview);
        b=(TextView)findViewById(R.id.txtTueview);
        c=(TextView)findViewById(R.id.txtWedview);
        d=(TextView)findViewById(R.id.txtThursview);
        e=(TextView)findViewById(R.id.txtFriview);
        btn=(Button)findViewById(R.id.btn);



        /*

        reff= FirebaseDatabase.getInstance().getReference().child("timetable");

         */


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff= FirebaseDatabase.getInstance().getReference().child("tutor_report").child("timetable");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot Snapshot) {


                            String Mon = String.valueOf(Snapshot.child("mon").
                                    getValue());
                            String Tue= String.valueOf(Snapshot.child("tue").
                                    getValue());
                            String Wed=String.valueOf(Snapshot.child("wed").
                                    getValue());
                            String Thurs=String.valueOf(Snapshot.child("thurs").
                                    getValue());
                            String Fri=String.valueOf(Snapshot.child("fri").
                                    getValue());

                            a.setText(Mon);
                            b.setText(Tue);
                            c.setText(Wed);
                            d.setText(Thurs);
                            e.setText(Fri);



                        }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.dashboard:
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));

                        overridePendingTransition(0,0);
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(),Schedule.class));

                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }
}
