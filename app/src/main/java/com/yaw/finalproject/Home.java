package com.yaw.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;



//TRACK TRACK TRACK TRACK TRACK TRACK TRACK TRACK TRACK TRACK TRACK TRACK TRACK TRACK TRACK TRACK TRACK TRACK TRACK TRACK TRACK TRACK TRACK


public class Home extends AppCompatActivity {

    EditText etMessage;
    EditText getEtMessageLive;
    EditText etTelNr;
    int  MY_PERMISSIONS_REQUEST_SEND_SMS =1;

    String SENT = "SMS_SENT";
    String DELIVERED = "SMS_DELIVERED";
    PendingIntent sentPI, deliveredPI;
    BroadcastReceiver smsSentReceiver, smsDeliverReceiver;

    ImageButton btnLogout;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private TextView mytextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mytextView = findViewById(R.id.mytextView);
        btnLogout = findViewById(R.id.logout);

        ActivityCompat.requestPermissions(Home.this, new String[]{Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);

        etMessage = (EditText) findViewById(R.id.etMessage);
        getEtMessageLive = (EditText) findViewById(R.id.etMessageLive);
        etTelNr = (EditText) findViewById(R.id.etTelNr);

        sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(),Dashboard.class));

                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(),Schedule.class));

                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(Home.this, MainActivity.class);
                startActivity(intToMain);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        smsSentReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(Home.this, "GPS set", Toast.LENGTH_SHORT).show();
                        break;

                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(Home.this, "Generic failure!", Toast.LENGTH_SHORT).show();
                        break;

                    case SmsManager. RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(Home.this, "No service!", Toast.LENGTH_SHORT).show();


                    case  SmsManager. RESULT_ERROR_NULL_PDU:
                        Toast.makeText(Home.this, "Null PDU!", Toast.LENGTH_SHORT).show();


                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(Home.this, "Radio Off", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        smsDeliverReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(Home.this, "GPS set", Toast.LENGTH_SHORT).show();
                        break;

                    case Activity.RESULT_CANCELED:
                        Toast.makeText(Home.this, "GPS not set!", Toast.LENGTH_SHORT).show();
                        break;

                }

            }
        };

        registerReceiver(smsSentReceiver, new IntentFilter((SENT)));
        registerReceiver(smsDeliverReceiver, new IntentFilter());
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(smsDeliverReceiver);
        unregisterReceiver(smsSentReceiver);
    }

    public void onclick(View view) {
        String message = etMessage.getText().toString();
        String telNr = etTelNr.getText().toString();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
        else
        {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(telNr, null, message, sentPI, deliveredPI);
        }
    }

    public void onclick2(View view) {
        String message = getEtMessageLive.getText().toString();
        String telNr = etTelNr.getText().toString();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
        else
        {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(telNr, null, message, sentPI, deliveredPI);
        }

        Toast.makeText(Home.this, "Live Tracking Set",Toast.LENGTH_LONG).show();
    }

    public void Onclick1(View view) {

        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        cursor.moveToFirst();

        mytextView.setText(cursor.getString(12));

    }
}
