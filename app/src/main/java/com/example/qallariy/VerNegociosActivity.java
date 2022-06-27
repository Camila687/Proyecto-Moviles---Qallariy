package com.example.qallariy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.example.qallariy.adapter.ListaAdapterRecyclerView;
import com.example.qallariy.dao.daoNegocio;
import com.example.qallariy.models.Negocio;
//import com.example.qallariy.models.SqLite;

import java.util.ArrayList;
import java.util.List;

public class VerNegociosActivity extends AppCompatActivity implements IAxiliarLista{

    RecyclerView listaRecycler;
    ArrayList<Negocio> listaArrayList;
    //SqLite sqLite;
    int id=0;
    private ListaAdapterRecyclerView listaAdapterRecyclerView;
    daoNegocio dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_negocios);

        dao=new daoNegocio(this);

        showToolbar(getResources().getString(R.string.toolbar_title_ver) , true);

        //sqLite = new SqLite(this,"negocio",null,1);

        listaRecycler=findViewById(R.id.negocioRecyclerLista);
        listaArrayList=new ArrayList<Negocio>();
        mostrarDatos();


        listaAdapterRecyclerView= new ListaAdapterRecyclerView(this,listaArrayList);

        RecyclerView recyclerView=findViewById(R.id.negocioRecyclerLista);

        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(listaAdapterRecyclerView);


    }

    public void showToolbar(String title, boolean upButton) {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }

    public void mostrarDatos() {
        //SQLiteDatabase sqLiteDatabase = sqLite.getReadableDatabase();
        Negocio nego = null;
        //ArrayList<Negocio> listaNegocios = new ArrayList();
        /*Cursor cursor=sqLiteDatabase.rawQuery("select * from negocio",null);
        Log.v("=======",String.valueOf("fuera del while"));
        while (cursor.moveToNext()) {
            nego = new Negocio();
            nego.setCodigo(cursor.getInt(0));
            nego.setPicture(cursor.getString(1));
            nego.setName(cursor.getString(2));
            nego.setDescription(cursor.getString(3));
            nego.setCategoria(cursor.getString(4));
            listaArrayList.add(nego);
            Log.v("=======",String.valueOf(cursor.getString(0)));

            Toast.makeText(this, "Si lee "+nego.getPicture(), Toast.LENGTH_SHORT).show();

        }*/

        //ArrayList<Negocio> listanegocios= new ArrayList<Negocio>();
        //listanegocios=dao.selectNegocio();
        //for(int i =0;i < listanegocios.size();i++){
         //   listaArrayList.add(listanegocios.get(i));
        //}
        listaArrayList = dao.selectNegocio();


    }

    @Override
    public void OpcionDetalle(Negocio negocio) {
        Intent intent = new Intent(this , ProductListActivity.class);
        intent.putExtra("Id",negocio.getCodigo());
        intent.putExtra("IdProducto",id);
        startActivity(intent);
    }



    /*public void verListNegocio(View view) {

        Intent intent = new Intent(this , VerDetalleActivity.class);
        startActivity(intent);

    }*/
}