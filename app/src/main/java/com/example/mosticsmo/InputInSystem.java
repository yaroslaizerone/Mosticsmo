package com.example.mosticsmo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class InputInSystem extends AppCompatActivity {

    EditText email_input,password_input;
    ImageButton show_password;
    TextView reset_pass;
    int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_in_system);

        Button buttonsingin = findViewById(R.id.buttoninput);
        email_input = (EditText) findViewById(R.id.InputUserEmail);
        password_input = (EditText) findViewById(R.id.editTextTextPassword);
        show_password = (ImageButton) findViewById(R.id.ShowPassword);
        reset_pass = (TextView) findViewById(R.id.Resetpassword);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        buttonsingin.setOnClickListener(v-> LoginUser());
    }
    public void ShowPassword(View v) {
        if (i >= 1){
            show_password.setImageResource(R.drawable.seepassword);
            password_input.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            i--;
        } else {
            password_input.setInputType(129);
            show_password.setImageResource(R.drawable.notpassword);
            i++;
        }
    }
    void LoginUser(){
        String email = email_input.getText().toString();
        String password = password_input.getText().toString();

        boolean isValidared = validateData(email,password);
        if(!isValidared){
            return;
        }
        loginAccountInFirebase(email,password);
    }
    void loginAccountInFirebase(String email, String password){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if (firebaseAuth.getCurrentUser().isEmailVerified()){
                        startActivity(new Intent( InputInSystem.this, OperationsActivity.class));
                    }else{
                        Utility.showToast(InputInSystem.this, "E-mail не подтвержден, подтвердите пожалуйста ваш E-mail.");
                    }

                }else{
                    Utility.showToast(InputInSystem.this, task.getException().getLocalizedMessage());
                }
            }
        });
    }
    boolean validateData(String email, String password){
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            email_input.setError("Неверный вид e-mail!");
            return false;
        }
        if (password.length()<6){
            password_input.setError("Пароль не менее 6 символов!");
            return false;
        }
        return true;
    }

    void resetPassworduser(View v){
        String email = email_input.getText().toString();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if(email_input.length() != 0 & Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            firebaseAuth.sendPasswordResetEmail(email)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Utility.showToast(InputInSystem.this, "Письмо для изменения пароля было отправлено к вам на почту.");
                        }
                    });
        }
        else {
            email_input.setError("Неверный вид e-mail!");
        }

    }
}