package com.example.mosticsmo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.backup.BackupAgent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirstInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser!=null)
        {startActivity(new Intent(FirstInput.this, OperationsActivity.class));}
        setContentView(R.layout.first_input);
    }
    public void RunVhod(View v) {
        Intent i;
        i = new Intent(this, InputVhod.class);
        startActivity(i);
    }
    public void RunRegist(View v) {
        Intent i;
        i = new Intent(this, RegistActivity.class);
        startActivity(i);
    }
}