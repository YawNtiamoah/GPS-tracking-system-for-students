package com.yaw.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import org.w3c.dom.Text;

public class pReport extends AppCompatActivity {

    TextView a,b,c;
    Button btn;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_report);

        a=(TextView)findViewById(R.id.txtNameView);
        b=(TextView)findViewById(R.id.txtWardView);
        c=(TextView)findViewById(R.id.txtReportView);
        btn=(Button)findViewById(R.id.btnReport);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff= FirebaseDatabase.getInstance().getReference().child("parent_report").child("report");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot Snapshot) {


                        String Name = String.valueOf(Snapshot.child("name").
                                getValue());
                        String Ward= String.valueOf(Snapshot.child("ward").
                                getValue());
                        String Report=String.valueOf(Snapshot.child("report").
                                getValue());

                        a.setText(Name);
                        b.setText(Ward);
                        c.setText(Report);




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });
            }
        });





        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.parent_report);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.parent_report:
                        return true;

                    case R.id.tutor_report:
                        startActivity(new Intent(getApplicationContext(),tReport.class));

                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
    }
}
