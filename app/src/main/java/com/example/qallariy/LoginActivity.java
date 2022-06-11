package com.example.qallariy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qallariy.dao.daoVendedor;
import com.example.qallariy.models.Vendedor;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText cor, pass;
    Button btnLogin;
    daoVendedor dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cor=(EditText) findViewById(R.id.username);
        pass=(EditText) findViewById(R.id.password);
        btnLogin=(Button) findViewById(R.id.login);

        btnLogin.setOnClickListener(this);
        dao=new daoVendedor(this);
    }

    public void goCreateAccount(View view) {

        Intent intent = new Intent(this , CreateVendedorActivity.class);
        startActivity(intent);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                String v=cor.getText().toString();
                String p=pass.getText().toString();
                if(v.equals("")&&p.equals("")) {
                    Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_SHORT).show();

                }else if(dao.login(v,p)==1) {
                    Vendedor ux=dao.getVendedor(v,p);
                    Toast.makeText(this, "Datos correctos", Toast.LENGTH_SHORT).show();
                    Intent i2=new Intent(LoginActivity.this, NegociosActivity.class);
                    i2.putExtra("Id",ux.getId());
                    startActivity(i2);
                    finish();
                } else {
                    Toast.makeText(this, "Usuario y/o Password incorrectos", Toast.LENGTH_SHORT).show();
                }
                break;


        }
    }
}