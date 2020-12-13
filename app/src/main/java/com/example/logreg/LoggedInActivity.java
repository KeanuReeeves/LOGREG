package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoggedInActivity extends AppCompatActivity {

    private TextView nev;
    private Button logout;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        init();
        Button();
    }

    private void Button()
    {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoggedInActivity.this, "Sikeres kijelentkezés", Toast.LENGTH_SHORT).show();
                Intent logout=new Intent(LoggedInActivity.this,MainActivity.class);
                startActivity(logout);
                finish();
            }
        });
    }

    private void init() {
        nev=findViewById(R.id.textNev);
        logout=findViewById(R.id.logout);
        db= new DBHelper(LoggedInActivity.this);
    }
}