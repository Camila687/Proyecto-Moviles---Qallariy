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

public class EditVendedorActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editUser, editPass,editNombre, editApellido, editDocumento, editTelefono;
    Button btnActualizar, btnCancelar;
    int id=0;
    Vendedor v;
    daoVendedor dao;
    Intent x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vendedor);

        editUser =(EditText)findViewById(R.id.EditUser);
        editPass =(EditText)findViewById(R.id.EditPass);
        editNombre =(EditText)findViewById(R.id.EditNombre);
        editApellido =(EditText)findViewById(R.id.EditApellido);
        editDocumento =(EditText)findViewById(R.id.EditDocumento);
        editTelefono =(EditText)findViewById(R.id.EditTelefono);
        btnActualizar=(Button) findViewById(R.id.btnEditActualizar);
        btnCancelar=(Button) findViewById(R.id.btnEditCancelar);
        btnActualizar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

        Bundle b=getIntent().getExtras();
        id=b.getInt("Id");
        dao=new daoVendedor(this);
        v=dao.getVendedorById(id);
        editUser.setText(v.getCorreo());
        editPass.setText(v.getPassword());
        editNombre.setText(v.getNombre());
        editApellido.setText(v.getApellidos());
        editDocumento.setText(v.getNumDocumento());
        editTelefono.setText(v.getTelefono());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEditActualizar:

                v.setCorreo(editUser.getText().toString());
                v.setPassword(editPass.getText().toString());
                v.setNombre(editNombre.getText().toString());
                v.setApellidos(editApellido.getText().toString());
                v.setNumDocumento(editDocumento.getText().toString());
                v.setTelefono(editTelefono.getText().toString());
                if(!v.isNull()) {
                    Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_SHORT).show();
                }else if (dao.updateVendedor(v)) {
                    Toast.makeText(this, "Actualizacion exitosa!!", Toast.LENGTH_SHORT).show();
                    Intent i2=new Intent(EditVendedorActivity.this,ProfileActivity.class);
                    i2.putExtra("Id",v.getId());
                    startActivity(i2);
                    finish();
                } else {
                    Toast.makeText(this, "No se puede actualizar", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnEditCancelar:
                Intent i1=new Intent(EditVendedorActivity.this,ProfileActivity.class);
                i1.putExtra("Id",v.getId());
                startActivity(i1);
                finish();
                break;
        }

    }
}