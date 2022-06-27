package com.example.qallariy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.qallariy.dao.daoNegocio;
import com.example.qallariy.models.Negocio;
//import com.example.qallariy.models.SqLite;
import com.google.android.material.textfield.TextInputEditText;

public class AddNegocioActivity extends AppCompatActivity {

    private TextInputEditText txtCodigo,txtImage, txtNombre, txtDescripcion, txtCategoria;
    private Button btnGuardar, btnCancelAdd;
    int IdVendedor=0;

    daoNegocio dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_negocio);

        dao=new daoNegocio(this);

        Bundle b=getIntent().getExtras();
        IdVendedor=b.getInt("IdVendedor");

        txtCodigo=(TextInputEditText)findViewById(R.id.negocioCodigoAdd);
        txtImage=(TextInputEditText)findViewById(R.id.negocioImage);
        txtNombre=(TextInputEditText)findViewById(R.id.negocioNameAdd);
        txtDescripcion=(TextInputEditText)findViewById(R.id.descripcionNegocioAdd);
        txtCategoria=(TextInputEditText)findViewById(R.id.categoriaNegocioAdd);

        btnGuardar=(Button) findViewById(R.id.btnNegocioAdd);
        btnCancelAdd=(Button) findViewById(R.id.btnCancelNegocio);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtCodigo.getText().toString().equals("")||txtImage.getText().toString().equals("")||txtNombre.getText().toString().equals("")|| txtDescripcion.getText().toString().equals("")|| txtCategoria.getText().toString().equals("")) {
                    validarTextos();
                }else {
                    GuardarDatosNegocio(v);
                    limpiarTextos();
                    Toast.makeText(AddNegocioActivity.this, "Datos Registrados", Toast.LENGTH_SHORT).show();
                    Intent i2 = new Intent(AddNegocioActivity.this, NegociosActivity.class);
                    i2.putExtra("IdVendedor",IdVendedor);
                    startActivity(i2);
                    finish();
                }

            }
        });

    }

    public void goAddNegocioCancel(View view) {

        Intent intent = new Intent(this , NegociosActivity.class);
        intent.putExtra("IdVendedor",IdVendedor);
        startActivity(intent);

    }

    private void GuardarDatosNegocio(View v) {

        //SqLite sqLite=new SqLite(this,"negocio",null,1);

        //SQLiteDatabase sqLiteDatabase=sqLite.getWritableDatabase();

        int codigo=Integer.parseInt(txtCodigo.getText().toString());
        String image=txtImage.getText().toString();
        String nombreNegocio=txtNombre.getText().toString();
        String descripcion=txtDescripcion.getText().toString();
        String categoria=txtCategoria.getText().toString();
        Negocio n = new Negocio();
        n.setCodigo(codigo);
        n.setPicture(image);
        n.setDescription(descripcion);
        n.setName(nombreNegocio);
        n.setCategoria(categoria);
        n.setIdVendedor(IdVendedor);
        //Long resultado=sqLiteDatabase.insert("negocio",null,values);

        if (!n.isNull()) {
            Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_SHORT).show();
        } else if (dao.insertNegocio(n)) {
            Toast.makeText(this, "Registro exitoso!!", Toast.LENGTH_SHORT).show();
            Intent i2 = new Intent(AddNegocioActivity.this, NegociosActivity.class);
            startActivity(i2);
            finish();
        } else {
            Toast.makeText(this, "Negocio ya registrado!!", Toast.LENGTH_SHORT).show();
        }

        //Toast.makeText(this, "Resultado: " + resultado, Toast.LENGTH_SHORT).show();

    }

    private void limpiarTextos() {
        txtCodigo.setText("");
        txtImage.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtCategoria.setText("");
    }

    public void validarTextos() {
        if(txtCodigo.getText().toString().equals("")) {
            txtCodigo.setText("Falta el código");
        }
        if(txtImage.getText().toString().equals("")) {
            txtImage. setText("Colocar una imagen");
        }
        if(txtNombre.getText().toString().equals("")) {
            txtNombre.setText("Ingrese el nombre del negocio");
        }
        if(txtDescripcion.getText().toString().equals("")) {
            txtDescripcion.setText("Ingrese una descripción");
        }
        if(txtCategoria.getText().toString().equals("")) {
            txtCategoria.setText("Ingrese una categoria");
        }

    }
}