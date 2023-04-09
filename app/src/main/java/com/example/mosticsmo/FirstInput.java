package com.example.mosticsmo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.backup.BackupAgent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Request window teature
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getSupportActionBar().hide();
        setContentView(R.layout.first_input);
        TextView texthave = (TextView)findViewById(R.id.AutoLogin);
    }
    public void RunVhod(View v) {
        Intent i;
        i = new Intent(this, InputVhod.class);
        startActivity(i);
    }
}