package com.example.qallariy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.qallariy.adapter.ProductListAdapterRecyclerView;
import com.example.qallariy.dao.daoProducto;
import com.example.qallariy.models.Producto;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity implements IAxiliarProductList{

    RecyclerView listPRecycler;
    ArrayList<Producto> listArrayList;
    int id=0;
    //int IdVendedor =0;
    private ProductListAdapterRecyclerView productListAdapterRecyclerView;

    daoProducto dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        showToolbar(getResources().getString(R.string.toolbar_title_ver) , true);

        dao=new daoProducto(this);

        //sqLite = new SqLite(this,"negocio",null,1);

        listPRecycler=findViewById(R.id.negocioRecyclerListProduct);
        listArrayList=new ArrayList<>();
        mostrarDatos();


        productListAdapterRecyclerView= new ProductListAdapterRecyclerView(this,listArrayList);

        RecyclerView recyclerView=findViewById(R.id.negocioRecyclerListProduct);

        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(productListAdapterRecyclerView);
    }

    public void showToolbar(String title, boolean upButton) {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }

    private void mostrarDatos() {
        //SQLiteDatabase sqLiteDatabase = sqLite.getReadableDatabase();
        Producto prod = null;
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

        listArrayList = dao.selectProducto();


    }

    @Override
    public void OpcionDetalleProduct(Producto producto) {

    }
}