package com.example.qallariy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.qallariy.adapter.NegocioAdapterRecyclerView;
import com.example.qallariy.models.Negocio;
import com.example.qallariy.models.SqLite;

import java.util.ArrayList;

public class NegociosActivity extends AppCompatActivity implements IAxiliarNegocio {

    RecyclerView negocioRecycler;
    ArrayList<Negocio> negocioArrayList;
    SqLite sqLite;
    int id=0;
    private NegocioAdapterRecyclerView negocioAdapterRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_negocios);

        Bundle b=getIntent().getExtras();
        id=b.getInt("Id");

        negocioRecycler=findViewById(R.id.negocioRecycler);
        RecyclerView recyclerView=findViewById(R.id.negocioRecycler);

        negocioArrayList=new ArrayList<>();
        sqLite = new SqLite(this,"negocio",null,1);

        negocioAdapterRecyclerView= new NegocioAdapterRecyclerView(this,negocioArrayList);

        mostrarDatos();

        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(negocioAdapterRecyclerView);




    }

    public void goNegocioAdd(View view) {

        Intent intent = new Intent(this , AddNegocioActivity.class);
        intent.putExtra("Id",id);
        startActivity(intent);

    }

    public void goProfile(View v) {

        Intent intent = new Intent(this , ProfileActivity.class);
        intent.putExtra("Id",id);
        startActivity(intent);

    }

    private void mostrarDatos() {
        SQLiteDatabase sqLiteDatabase = sqLite.getReadableDatabase();
        Negocio nego = null;

        Cursor cursor=sqLiteDatabase.rawQuery("select * from negocio",null);
        while (cursor.moveToNext()) {
            nego = new Negocio();
            nego.setCodigo(cursor.getInt(0));
            nego.setPicture(cursor.getString(1));
            nego.setName(cursor.getString(2));
            nego.setDescription(cursor.getString(3));
            nego.setCategoria(cursor.getString(4));
            negocioAdapterRecyclerView.agregarNegocio(nego);

            System.out.println("Si lee"+nego.getPicture());


        }
    }

    @Override
    public void OpcionEditar(Negocio negocio) {
        Intent intent=new Intent(NegociosActivity.this,EditNegocioActivity.class);
        intent.putExtra("negocio",negocio);
        intent.putExtra("Id",id);
        startActivity(intent);

    }

    @Override
    public void OpcionEliminar(Negocio negocio) {

        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Mensaje");
        alert.setMessage("Esta seguro de eliminar?? "+negocio.getName());
        alert.setCancelable(false);
        alert.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                eliminarNegocio(negocio);
            }
        });
        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();

    }

    private void eliminarNegocio(Negocio negocio) {

        SqLite sqLite=new SqLite(this,"negocio",null,1);
        SQLiteDatabase sqLiteDatabase=sqLite.getWritableDatabase();
        String codigo=String.valueOf(negocio.getCodigo());
        if (!codigo.isEmpty()) {
            sqLiteDatabase.delete("negocio","codigo="+codigo,null);
            Toast.makeText(this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();
            negocioAdapterRecyclerView.eliminarNegocio(negocio);
            sqLiteDatabase.close();
        } else {
            Toast.makeText(this, "Error: No se eliminó la pizza", Toast.LENGTH_SHORT).show();
        }
    }
}