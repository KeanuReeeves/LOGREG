package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText felnev,jelszo;
    private Button login,signup;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        events();
    }
    private void events()
    {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(reg);
                finish();
            }
        });
    }
    private void login()
    {
        if (db.adatellenorzes(felnev.getText().toString(),jelszo.getText().toString()))
        {
            Intent login = new Intent(MainActivity.this,LoggedInActivity.class);
            startActivity(login);
            finish();
        }
        else
        {
            Toast.makeText(MainActivity.this,"Sikertelen bejelentkezés",Toast.LENGTH_SHORT).show();
        }
    }
    private void init() {
        felnev=findViewById(R.id.FelNev_ET);
        jelszo=findViewById(R.id.Jelszo_ET);

        login=findViewById(R.id.Log_in_btn);
        signup=findViewById(R.id.Sign_up_btn);

        db=new DBHelper(MainActivity.this);
    }

}