package com.yaw.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button tutorButton;
    public Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            button = (Button) findViewById(R.id.button);
            tutorButton = (Button) findViewById(R.id.btntutor);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openActivity();
                }
            });

            tutorButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* opentutor();*/
                    Intent i = new Intent(getApplicationContext(), tReport.class);
                    startActivity(i);
                    finish();
                }
            });

    }

    public void openActivity(){
        Intent main = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(main);
        finish();
    }

    public void opentutor(){
        Intent tutor = new Intent(MainActivity.this, tutorLogin.class);
        startActivity(tutor);
        finish();
    }

}
