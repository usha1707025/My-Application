package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private EditText name,age,hgtfeet,hgtinch,wgt,pressure,gluc,skin,insu;
    private Button submitButton,resetButton;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        name=(EditText)findViewById(R.id.userName);
        age=(EditText)findViewById(R.id.userAge);

        radioGroup=(RadioGroup)findViewById(R.id.radioGroupId);

        hgtfeet=(EditText)findViewById(R.id.heightFeet);
        hgtinch=(EditText)findViewById(R.id.heightInch);
        wgt=(EditText)findViewById(R.id.weight);
        pressure=(EditText)findViewById(R.id.bloodPressure);
        gluc=(EditText)findViewById(R.id.glucose);
        skin=(EditText)findViewById(R.id.skinThickness);
        insu=(EditText)findViewById(R.id.insulin);

        submitButton=(Button)findViewById(R.id.submitButtonId);
        resetButton=(Button)findViewById(R.id.resetButtonId);
    }
}
