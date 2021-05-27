package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity implements View.OnClickListener {

    private EditText passwordEmail;
    private Button resetPassword;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        //getSupportActionBar().setTitle("Reset Password");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        passwordEmail=(EditText)findViewById(R.id.resetEmailId);
        resetPassword=(Button)findViewById(R.id.resetPasswordId);
        firebaseAuth=FirebaseAuth.getInstance();
        resetPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
         String userEmail=passwordEmail.getText().toString().trim();
        if(userEmail.isEmpty()){
            passwordEmail.setError("Enter your registered email address");
            passwordEmail.requestFocus();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            passwordEmail.setError("Enter a valid email address");
            passwordEmail.requestFocus();
            return;
        }
        else{
            firebaseAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(ResetPassword.this,"Password reset email is sent", Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(new Intent(ResetPassword.this,LoginPage.class));

                    }
                    else{
                        Toast.makeText(ResetPassword.this,"Error in sending password reset email", Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }
}