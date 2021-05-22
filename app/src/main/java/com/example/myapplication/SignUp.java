package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private EditText signUpEmail, signUpPassword;
    private TextView loginText;
    private Button signUp;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        progressBar=findViewById(R.id.progressBarId);
        signUpEmail=findViewById(R.id.signUpnEmailId);
        signUpPassword=findViewById(R.id.signUpPassword);
        signUp=findViewById(R.id.signUpButton);
        loginText=findViewById(R.id.loginTextId);

        loginText.setOnClickListener(this);
        signUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signUpButton:
                userRegister();
                break;
            case R.id.loginTextId:
                Intent intent = new Intent(getApplicationContext(),LoginPage.class);
                startActivity(intent);
                break;
        }
    }

    private void userRegister() {
        String email=signUpEmail.getText().toString().trim();
        String pass=signUpPassword.getText().toString().trim();
        if(email.isEmpty()){
            signUpEmail.setError("Enter an email address");
            signUpEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            signUpEmail.setError("Enter a valid email address");
            signUpEmail.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            signUpPassword.setError("Enter a password");
            signUpPassword.requestFocus();
            return;
        }
        if(pass.length()<8){
            signUpPassword.setError("Minimum length of password is 8");
            signUpPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    //Toast.makeText(getApplicationContext(),"Register is successful", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"User is already registered",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Error : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}
