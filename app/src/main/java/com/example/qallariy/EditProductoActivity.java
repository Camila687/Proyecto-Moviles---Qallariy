package com.example.qallariy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.qallariy.adapter.ProductoAdapterRecyclerView;
import com.example.qallariy.dao.daoProducto;
import com.example.qallariy.models.Producto;
import com.google.android.material.textfield.TextInputEditText;

public class EditProductoActivity extends AppCompatActivity {

    private ProductoAdapterRecyclerView productoAdapterRecyclerView;
    private TextInputEditText txtCodigoPEditar, txtImagePEdit, txtNombrePEditar, txtDescripcionPEditar,txtPrecioPEditar,txtCantidadPEditar;
    private Producto producto;

    private Button btnUpdateP;

    daoProducto dao;
    int IdProducto=0;
    int IdNegocio=0;
    int IdVendedor=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_producto);

        dao=new daoProducto(this);

        Bundle b=getIntent().getExtras();
        IdProducto=b.getInt("IdProducto");
        IdNegocio=b.getInt("IdNegocio");
        IdVendedor=b.getInt("IdVendedor");

        txtCodigoPEditar = findViewById(R.id.productoCodigoEdit);
        txtImagePEdit = findViewById(R.id.productoImageEdit);
        txtNombrePEditar = findViewById(R.id.productoNameEdit);
        txtDescripcionPEditar = findViewById(R.id.descripcionProductoEdit);
        txtPrecioPEditar = findViewById(R.id.precioProductoEdit);
        txtCantidadPEditar = findViewById(R.id.cantidadProductoEdit);
        btnUpdateP = findViewById(R.id.btnProductoEdit);

        btnUpdateP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarDatos(v);

            }
        });

        producto=(Producto) dao.getProductoById(IdProducto);
        llenarDatos();
    }

    private void llenarDatos() {
        txtCodigoPEditar.setText(String.valueOf(producto.getCodigo()));
        txtCodigoPEditar.setEnabled(false);
        txtImagePEdit.setText(producto.getImage());
        txtNombrePEditar.setText(producto.getNombreP());
        txtDescripcionPEditar.setText(producto.getDescripcion());
        txtPrecioPEditar.setText(String.valueOf(producto.getPrecio()));
        txtCantidadPEditar.setText(String.valueOf(producto.getCantidad()));
    }

    public void goEditProductoCancel(View view) {

        Intent intent = new Intent(this , ProductoActivity.class);
        intent.putExtra("IdNegocio",IdNegocio);
        intent.putExtra("IdVendedor",IdVendedor);
        startActivity(intent);

    }

    private void editarDatos(View v) {

        /* SqLite sqLite=new SqLite(this,"negocio",null,1);*/

        //SQLiteDatabase sqLiteDatabase=sqLite.getWritableDatabase();
        Integer codigo=Integer.parseInt(txtCodigoPEditar.getText().toString());
        String image=txtImagePEdit.getText().toString();
        String nombre=txtNombrePEditar.getText().toString();
        String descripcion=txtDescripcionPEditar.getText().toString();
        double precio=Double.parseDouble(txtPrecioPEditar.getText().toString());
        Integer cantidad=Integer.parseInt(txtCantidadPEditar.getText().toString());

        Producto p = new Producto();
        p.setCodigo(codigo);
        p.setImage(image);
        p.setNombreP(nombre);
        p.setDescripcion(descripcion);
        p.setPrecio(precio);
        p.setCantidad(cantidad);
        p.setIdNegocio(IdNegocio);

        /*sqLiteDatabase.update("negocio",values,"codigo="+codigo,null);
        sqLiteDatabase.close();*/

        if(!p.isNull()) {
            Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_SHORT).show();
        }else if (dao.updateProducto(p)) {
            Toast.makeText(this, "Actualizacion exitosa!!", Toast.LENGTH_SHORT).show();
            Intent i2=new Intent(EditProductoActivity.this,ProductoActivity.class);
            i2.putExtra("IdNegocio",IdNegocio);
            i2.putExtra("IdVendedor",IdVendedor);
            startActivity(i2);
            finish();
        } else {
            Toast.makeText(this, "No se puede actualizar", Toast.LENGTH_SHORT).show();
        }

    }
}