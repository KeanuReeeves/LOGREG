package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText email,felnev,jelszo,fullname;
    private Button reg,back;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
        onClickListeners();
    }

    private void adatRogzites() {
        String email1=email.getText().toString().trim();
        String felnev1=felnev.getText().toString().trim();
        String jelszo1=jelszo.getText().toString().trim();
        String fullnev=fullname.getText().toString().trim();

        if (felnev1.isEmpty())
        {
            Toast.makeText(this, "A felhasználónév megadása kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (email1.isEmpty())
        {
            Toast.makeText(this, "Az Email cím megadása kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (jelszo1.isEmpty())
        {
            Toast.makeText(this, "A jelszó megadása kötelező!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (fullnev.isEmpty())
        {
            Toast.makeText(this, "A teljes név megadása kötelező!!", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean sikeres= db.adatRogzites(email1,felnev1,jelszo1,fullnev);
        if (sikeres)
        {
            Toast.makeText(this, "Sikeres felvétel!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Sikertelen felvétel!", Toast.LENGTH_SHORT).show();
        }

    }

    private void onClickListeners()
    {
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adatRogzites();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(back);
                finish();
            }
        });
    }

    private void init() {
        email=findViewById(R.id.et_email_reg);
        felnev=findViewById(R.id.et_felhasznalonev_reg);
        jelszo=findViewById(R.id.et_jelszo_reg);
        fullname=findViewById(R.id.et_fullname_reg);

        reg=findViewById(R.id.reg_btn_reg);
        back=findViewById(R.id.back_btn_reg);

        db=new DBHelper(RegisterActivity.this);
    }
}