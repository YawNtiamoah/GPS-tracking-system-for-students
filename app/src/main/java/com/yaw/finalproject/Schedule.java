package com.yaw.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.view.MenuItem;

//REPORT REPORT REPORT REPORT REPORT REPORT REPORT REPORT REPORT REPORT REPORT REPORT REPORT REPORT REPORT REPORT REPORT REPORT REPORT

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Schedule extends AppCompatActivity {

    EditText txtName, txtWard,txtReport;
    Button btnReport;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        txtName=(EditText)findViewById(R.id.txtName);
        txtWard=(EditText)findViewById(R.id.txtWard);
        txtReport=(EditText)findViewById(R.id.txtReport);
        btnReport=(Button)findViewById(R.id.btnReport);

        reff= FirebaseDatabase.getInstance().getReference().child("parent_report");
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//created a new method for report if error revert report method.
                report();

                Toast.makeText(Schedule.this, "Report submitted Successfully",Toast.LENGTH_LONG).show();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.about);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(),Dashboard.class));

                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));

                        overridePendingTransition(0,0);
                        return true;
                    case R.id.about:
                        return true;
                }
                return false;
            }
        });
    }
    public void report (){
        String Name = txtName.getText().toString().trim();
        String Ward = txtWard.getText().toString().trim();
        String Report = txtReport.getText().toString().trim();


        parReport report =new parReport(Name, Ward, Report);
        reff.child("report").setValue(report);
    }
}
