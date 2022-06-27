package com.example.qallariy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.qallariy.dao.daoProducto;
import com.example.qallariy.models.Producto;
import com.google.android.material.textfield.TextInputEditText;

import java.net.IDN;

public class AddProductoActivity extends AppCompatActivity {

    private TextInputEditText txtCodigoP,txtImageP, txtNombreP, txtDescripcionP, txtPrecioP, txtCantidadP;
    private Button btnGuardarP, btnCancelP;
    int IdNegocio=0;
    int IdVendedor=0;

    daoProducto dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_producto);

        dao=new daoProducto(this);

        Bundle b=getIntent().getExtras();
        IdNegocio =b.getInt("IdNegocio");
        IdVendedor=b.getInt("IdVendedor");


        txtCodigoP=(TextInputEditText)findViewById(R.id.productoCodigoAdd);
        txtImageP=(TextInputEditText)findViewById(R.id.productoImage);
        txtNombreP=(TextInputEditText)findViewById(R.id.productoNameAdd);
        txtDescripcionP=(TextInputEditText)findViewById(R.id.descripcionProductoAdd);
        txtPrecioP=(TextInputEditText)findViewById(R.id.precioProductoAdd);
        txtCantidadP=(TextInputEditText)findViewById(R.id.cantidadProductoAdd);

        btnGuardarP=(Button) findViewById(R.id.btnProductoAdd);
        btnCancelP=(Button) findViewById(R.id.btnCancelProducto);


        btnGuardarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtCodigoP.getText().toString().equals("")||txtImageP.getText().toString().equals("")||txtNombreP.getText().toString().equals("")|| txtDescripcionP.getText().toString().equals("")|| txtPrecioP.getText().toString().equals("")|| txtCantidadP.getText().toString().equals("")) {
                    validarTextos();
                }else {
                    GuardarDatosProducto(v);
                    limpiarTextos();
                    Toast.makeText(AddProductoActivity.this, "Datos Registrados", Toast.LENGTH_SHORT).show();
                    Intent i2 = new Intent(AddProductoActivity.this, ProductoActivity.class);
                    i2.putExtra("IdNegocio",IdNegocio);
                    i2.putExtra("IdVendedor",IdVendedor);
                    startActivity(i2);
                    finish();
                }
            }
        });
    }

    public void goAddProductoCancel(View view) {

        Intent intent = new Intent(this , ProductoActivity.class);
        intent.putExtra("IdNegocio",IdNegocio);
        intent.putExtra("IdVendedor",IdVendedor);
        startActivity(intent);

    }

    private void GuardarDatosProducto(View v) {

        //SqLite sqLite=new SqLite(this,"negocio",null,1);

        //SQLiteDatabase sqLiteDatabase=sqLite.getWritableDatabase();

        int codigo=Integer.parseInt(txtCodigoP.getText().toString());
        String image=txtImageP.getText().toString();
        String nombreProducto=txtNombreP.getText().toString();
        String descripcion=txtDescripcionP.getText().toString();
        double precio=Double.parseDouble(txtPrecioP.getText().toString());
        int cantidad=Integer.parseInt(txtCantidadP.getText().toString());
        Producto p = new Producto();
        p.setCodigo(codigo);
        p.setImage(image);
        p.setNombreP(nombreProducto);
        p.setDescripcion(descripcion);
        p.setPrecio(precio);
        p.setCantidad(cantidad);
        p.setIdNegocio(IdNegocio);
        //Long resultado=sqLiteDatabase.insert("negocio",null,values);

        if (!p.isNull()) {
            Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_SHORT).show();
        } else if (dao.insertProducto(p)) {
            Toast.makeText(this, "Registro exitoso!!", Toast.LENGTH_SHORT).show();
            Intent i2 = new Intent(AddProductoActivity.this, ProductoActivity.class);
            i2.putExtra("IdNegocio",IdNegocio);
            i2.putExtra("IdVendedor",IdVendedor);
            startActivity(i2);
            finish();
        } else {
            Toast.makeText(this, "Producto ya registrado!!", Toast.LENGTH_SHORT).show();
        }

        //Toast.makeText(this, "Resultado: " + resultado, Toast.LENGTH_SHORT).show();

    }

    private void limpiarTextos() {
        txtCodigoP.setText("");
        txtImageP.setText("");
        txtNombreP.setText("");
        txtDescripcionP.setText("");
        txtPrecioP.setText("");
        txtCantidadP.setText("");
    }

    public void validarTextos() {
        if(txtCodigoP.getText().toString().equals("")) {
            txtCodigoP.setText("Falta el código");
        }
        if(txtImageP.getText().toString().equals("")) {
            txtImageP. setText("Colocar una imagen");
        }
        if(txtNombreP.getText().toString().equals("")) {
            txtNombreP.setText("Ingrese el nombre del producto");
        }
        if(txtDescripcionP.getText().toString().equals("")) {
            txtDescripcionP.setText("Ingrese una descripción");
        }
        if(txtPrecioP.getText().toString().equals("")) {
            txtPrecioP.setText("Ingrese un precio");
        }

        if(txtCantidadP.getText().toString().equals("")) {
            txtCantidadP.setText("Ingrese una cantidad");
        }

    }
}