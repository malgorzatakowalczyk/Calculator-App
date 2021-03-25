package com.example.calculator1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    private Button exit;
    private Button simple;
    private Button advanced;
    private Button about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simple=(Button)findViewById(R.id.simple);
        simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openSimpleCalculator();

            }
        });
        advanced=(Button)findViewById(R.id.advanced);
        advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openAdvancedCalculator();

            }
        });


        about=(Button)findViewById(R.id.about);
        final AlertDialog.Builder aboutWindow = new AlertDialog.Builder(this);
        aboutWindow.setTitle("About");
        aboutWindow .setMessage("Developed by Malgorzata Kowalczyk");
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                aboutWindow .show();
            }
        });


        exit=(Button)findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);

            }
        });

    }
    public void openSimpleCalculator()
    {
        Intent intent= new Intent(this,SimpleCalculator.class);
        startActivity(intent);
    }
    public void openAdvancedCalculator()
    {
        Intent intent= new Intent(this,AdvancedCalculator.class);
        startActivity(intent);
    }
}