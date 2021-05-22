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

public class LoginPage extends AppCompatActivity implements View.OnClickListener {
    private EditText loginEmail, loginPassword;
    private TextView signUpText;
    private Button login;
    private FirebaseAuth mAuth;

    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mAuth = FirebaseAuth.getInstance();

        loginEmail=findViewById(R.id.loginEmailId);
        loginPassword=findViewById(R.id.loginPassword);
        login=findViewById(R.id.loginButton);
        signUpText=findViewById(R.id.signUpTextId);
        progressBar=findViewById(R.id.progressBarId);
        signUpText.setOnClickListener(this);
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.loginButton:
               userlogin();
              break;
           case R.id.signUpTextId:
               Intent intent = new Intent(getApplicationContext(),SignUp.class);
               startActivity(intent);
               break;
       }
    }

    private void userlogin() {
        String email=loginEmail.getText().toString().trim();
        String pass=loginPassword.getText().toString().trim();
        if(email.isEmpty()){
            loginEmail.setError("Enter an email address");
            loginEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            loginEmail.setError("Enter a valid email address");
            loginEmail.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            loginPassword.setError("Enter a password");
            loginPassword.requestFocus();
            return;
        }
        if(pass.length()<8){
            loginPassword.setError("Minimum length of password is 8");
            loginPassword.requestFocus();
            return;
        }
       progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    finish();
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Login Unsuccesful",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
