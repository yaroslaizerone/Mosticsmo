package com.example.mosticsmo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class InputInSystem extends AppCompatActivity {

    EditText email_input,password_input;
    ImageButton show_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_in_system);

        Button buttonsingin = findViewById(R.id.buttoninput);
        email_input = (EditText) findViewById(R.id.InputUserEmail);
        password_input = (EditText) findViewById(R.id.editTextTextPassword);
        show_password = (ImageButton) findViewById(R.id.ShowPassword);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    }
}