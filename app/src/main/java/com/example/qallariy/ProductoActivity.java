package com.example.qallariy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.qallariy.adapter.ProductoAdapterRecyclerView;
import com.example.qallariy.dao.daoProducto;
import com.example.qallariy.models.Producto;

import java.util.ArrayList;

public class ProductoActivity extends AppCompatActivity implements IAxiliarProducto{

    RecyclerView productoRecycler;
    ArrayList<Producto> productoArrayList;
    //SqLite sqLite;
    int IdNegocio=0;
    int IdVendedor =0;
    private ProductoAdapterRecyclerView productoAdapterRecyclerView;
    daoProducto dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        dao=new daoProducto(this);

        Bundle b=getIntent().getExtras();
        IdNegocio=b.getInt("IdNegocio");
        IdVendedor=b.getInt("IdVendedor");

        productoRecycler=findViewById(R.id.productoRecycler);
        RecyclerView recyclerView=findViewById(R.id.productoRecycler);

        productoArrayList=new ArrayList<>();
        //sqLite = new SqLite(this,"negocio",null,1);

        productoAdapterRecyclerView= new ProductoAdapterRecyclerView(this,productoArrayList);

        mostrarDatos();

        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(productoAdapterRecyclerView);
    }

    public void goProductoAgregar(View view) {

        Intent intent = new Intent(this , AddProductoActivity.class);
        intent.putExtra("IdNegocio",IdNegocio);
        intent.putExtra("IdVendedor",IdVendedor);
        startActivity(intent);

    }

    public void goVolver(View v) {

        Intent intent = new Intent(this , NegociosActivity.class);
        intent.putExtra("IdNegocio",IdNegocio);
        intent.putExtra("IdVendedor",IdVendedor);
        startActivity(intent);

    }

    private void mostrarDatos() {
        //SQLiteDatabase sqLiteDatabase = sqLite.getReadableDatabase();
        Producto prod = null;


        ArrayList<Producto> productos= new ArrayList<Producto>();
        productos=dao.ProductobyNegocio(IdNegocio);
        for(int i =0;i < productos.size();i++){
            productoAdapterRecyclerView.agregarProducto(productos.get(i));
            Log.v("========",productos.get(i).getDescripcion());
        }
    }

    @Override
    public void OpcionEditarProducto(Producto producto) {
        Intent i3=new Intent(ProductoActivity.this,EditProductoActivity.class);
        //i3.putExtra("producto", producto);
        //i3.p
        i3.putExtra("IdNegocio",IdNegocio);
        i3.putExtra("IdVendedor",IdVendedor);
        i3.putExtra("IdProducto",producto.getCodigo());
        startActivity(i3);

    }

    @Override
    public void OpcionEliminarProducto(Producto producto) {
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Mensaje");
        alert.setMessage("Esta seguro de eliminar?? "+producto.getNombreP());
        alert.setCancelable(false);
        alert.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(dao.deleteProducto(producto.getCodigo())) {
                    Intent c=new Intent(ProductoActivity.this,ProductoActivity.class);
                    c.putExtra("IdNegocio",producto.getIdNegocio());
                    c.putExtra("IdVendedor",IdVendedor);
                    startActivity(c);
                    Toast.makeText(ProductoActivity.this, "Se elminó sin problemas", Toast.LENGTH_SHORT).show();
                    productoAdapterRecyclerView.eliminarProducto(producto);
                    finish();
                }else {
                    Toast.makeText(ProductoActivity.this, "Error: No se eliminó el producto", Toast.LENGTH_SHORT).show();
                }

            }
        });
        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();

    }
}