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

public class CreateVendedorActivity extends AppCompatActivity implements View.OnClickListener {

    EditText co, pas,nom,ap,nd,nt;
    Button reg, can;
    daoVendedor dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_vendedor);

        co=(EditText)findViewById(R.id.email);
        pas=(EditText)findViewById(R.id.password_createAccount);
        nom=(EditText)findViewById(R.id.name);
        ap=(EditText)findViewById(R.id.lastname);
        nd=(EditText)findViewById(R.id.documentNumber);
        nt=(EditText)findViewById(R.id.phoneNumber);
        reg=(Button)findViewById(R.id.joinUs);
        can=(Button)findViewById(R.id.cancel);
        reg.setOnClickListener(this);
        can.setOnClickListener(this);
        dao=new daoVendedor(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.joinUs:
                Vendedor v = new Vendedor();
                v.setCorreo(co.getText().toString());
                v.setPassword(pas.getText().toString());
                v.setNombre(nom.getText().toString());
                v.setApellidos(ap.getText().toString());
                v.setNumDocumento(nd.getText().toString());
                v.setTelefono(nt.getText().toString());
                if (!v.isNull()) {
                    Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_SHORT).show();
                } else if (dao.insertVendedor(v)) {
                    Toast.makeText(this, "Registro exitoso!!", Toast.LENGTH_SHORT).show();
                    Intent i2 = new Intent(CreateVendedorActivity.this, LoginActivity.class);
                    startActivity(i2);
                    finish();
                } else {
                    Toast.makeText(this, "Usuario ya registrado!!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.cancel:
                Intent i = new Intent(CreateVendedorActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
                break;
        }

    }
}