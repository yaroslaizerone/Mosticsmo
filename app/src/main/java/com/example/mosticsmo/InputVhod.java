package com.example.mosticsmo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InputVhod extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_vhod);
    }
    public void InpytsystemActivity(View v) {
        Intent i;
        i = new Intent(this, InputInSystem.class);
        startActivity(i);
    }
    public void BackActivity(View v) {
        Intent i;
        i = new Intent(this, FirstInput.class);
        startActivity(i);
    }
    public void PasswordRecoveryActivity(View v) {
        Intent i;
        i = new Intent(this, PasswordRecovery.class);
        startActivity(i);
    }
}