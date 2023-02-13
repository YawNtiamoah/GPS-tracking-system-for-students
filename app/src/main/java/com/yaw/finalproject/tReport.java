package com.yaw.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Member;

public class tReport extends AppCompatActivity {

    EditText txtMon, txtTue,txtWed,txtThurs,txtFri;
    Button btnSubmit;
    DatabaseReference reff;
    /*tutReport TUTREPORT;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_report);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.tutor_report);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.parent_report:
                        startActivity(new Intent(getApplicationContext(),pReport.class));

                        overridePendingTransition(0,0);
                        return true;

                    case R.id.tutor_report:
                        return true;

                }
                return false;
            }
        });


        txtMon=(EditText)findViewById(R.id.txtMon);
        txtTue=(EditText)findViewById(R.id.txtTue);
        txtWed=(EditText)findViewById(R.id.txtWed);
        txtThurs=(EditText)findViewById(R.id.txtThurs);
        txtFri=(EditText)findViewById(R.id.txtFri);
        btnSubmit=(Button)findViewById(R.id.btnSubmit);
        /*TUTREPORT = new tutReport();*/

        reff= FirebaseDatabase.getInstance().getReference().child("tutor_report");
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* TUTREPORT.setMon(txtMon.getText().toString().trim());
                TUTREPORT.setTue(txtTue.getText().toString().trim());
                TUTREPORT.setWed(txtWed.getText().toString().trim());
                TUTREPORT.setThurs(txtThurs.getText().toString().trim());
                TUTREPORT.setFri(txtFri.getText().toString().trim());
*/
               String Mon = txtMon.getText().toString().trim();
                String Tue = txtTue.getText().toString().trim();
                String Wed = txtWed.getText().toString().trim();
                String Thurs = txtThurs.getText().toString().trim();
                String Fri = txtFri.getText().toString().trim();


                tutReport report =new tutReport(Mon, Tue, Wed, Thurs,Fri);
                reff.child("timetable").setValue(report);


                Toast.makeText(tReport.this, "Timetable inserted Successfully",Toast.LENGTH_LONG).show();
            }
        });




    }
}
