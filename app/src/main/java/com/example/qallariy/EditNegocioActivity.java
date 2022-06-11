package com.example.qallariy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.qallariy.adapter.NegocioAdapterRecyclerView;
import com.example.qallariy.models.Negocio;
import com.example.qallariy.models.SqLite;
import com.google.android.material.textfield.TextInputEditText;

public class EditNegocioActivity extends AppCompatActivity {

    private NegocioAdapterRecyclerView negocioAdapterRecyclerView;

    private TextInputEditText txtCodigoEditar, txtImageEdit, txtNombreEditar, txtDescripcionEditar,txtCategoriaEditar;
    private Negocio negocio;

    private Button btnUpdate;

    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_negocio);

        Bundle b=getIntent().getExtras();
        id=b.getInt("Id");

        txtCodigoEditar = findViewById(R.id.negocioCodigoEdit);
        txtImageEdit = findViewById(R.id.negocioImageEdit);
        txtNombreEditar = findViewById(R.id.negocioNameEdit);
        txtDescripcionEditar = findViewById(R.id.descripcionNegocioEdit);
        txtCategoriaEditar = findViewById(R.id.costoPizzaEdit);
        btnUpdate = findViewById(R.id.btnNegocioEdit);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarDatos(v);

            }
        });

        negocio=(Negocio) getIntent().getSerializableExtra("negocio");
        llenarDatos();
    }

    private void llenarDatos() {
        txtCodigoEditar.setText(String.valueOf(negocio.getCodigo()));
        txtCodigoEditar.setEnabled(false);
        txtImageEdit.setText(negocio.getPicture());
        txtNombreEditar.setText(negocio.getName());
        txtDescripcionEditar.setText(negocio.getDescription());
        txtCategoriaEditar.setText(negocio.getCategoria());
    }

    public void goEditNegocioCancel(View view) {

        Intent intent = new Intent(this , NegociosActivity.class);
        intent.putExtra("Id",id);
        startActivity(intent);

    }

    private void editarDatos(View v) {

        SqLite sqLite=new SqLite(this,"negocio",null,1);

        SQLiteDatabase sqLiteDatabase=sqLite.getWritableDatabase();
        Integer codigo= Integer.parseInt(txtCodigoEditar.getText().toString());
        String image=txtImageEdit.getText().toString();
        String nombre=txtNombreEditar.getText().toString();
        String descripcion=txtDescripcionEditar.getText().toString();
        String categoria= txtCategoriaEditar.getText().toString();

        ContentValues values=new ContentValues();
        values.put("codigo",codigo);
        values.put("image",image);
        values.put("nombre",nombre);
        values.put("descripcion",descripcion);
        values.put("categoria",categoria);

        sqLiteDatabase.update("negocio",values,"codigo="+codigo,null);
        sqLiteDatabase.close();
        finish();

        Toast.makeText(this, "Datos Editados", Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(EditNegocioActivity.this,NegociosActivity.class);
        intent.putExtra("Id",id);
        startActivity(intent);

    }
}