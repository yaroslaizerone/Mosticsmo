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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistActivity extends AppCompatActivity {

    EditText email_input,password_input,repitpassword_input;
    ImageView show_password, show_rpassword;
    int i,j = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        Button buttonsingup = findViewById(R.id.Singup);
        email_input = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password_input = (EditText) findViewById(R.id.editTextTextPassword);
        repitpassword_input = (EditText) findViewById(R.id.Repitpassword);
        show_password = (ImageButton) findViewById(R.id.ShowPassword);
        show_rpassword = (ImageButton) findViewById(R.id.ShowRpassword);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        buttonsingup.setOnClickListener(v-> createAccount());
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
    public void ShowRpassword(View v) {
        if (j == 1){
            repitpassword_input.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            show_rpassword.setImageResource(R.drawable.seepassword);
            j--;
        } else {
            repitpassword_input.setInputType(129);
            show_rpassword.setImageResource(R.drawable.notpassword);
            j++;
        }
    }
    void createAccount(){
        String email = email_input.getText().toString();
        String password = password_input.getText().toString();
        String rpassword = repitpassword_input.getText().toString();

        boolean isValidared = validateData(email,password,rpassword);
        if(!isValidared){
            return;
        }
        createAccountInFirebase(email,password);
    }

    void createAccountInFirebase(String email, String password){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegistActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegistActivity.this, "Аккаунт создан, подтвердите свой e-mail.", Toast.LENGTH_SHORT).show();
                            firebaseAuth.getCurrentUser().sendEmailVerification();
                            firebaseAuth.signOut();
                        }else{
                            Toast.makeText(RegistActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    boolean validateData(String email, String password, String rpassword){
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            email_input.setError("Неверный вид e-mail!");
            return false;
        }
        if (password.length()<6){
            password_input.setError("Пароль не менее 6 символов!");
            return false;
        }
        if (!password.equals(rpassword) & password.length() != 0 & rpassword.length() != 0){
            repitpassword_input.setError("Пароли не совпадают!");
            return false;
        }
        return true;
    }
}