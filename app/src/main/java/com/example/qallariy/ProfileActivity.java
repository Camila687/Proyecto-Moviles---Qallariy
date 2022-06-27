package com.example.qallariy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qallariy.dao.daoVendedor;
import com.example.qallariy.models.Vendedor;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnEditar, btnEliminar, btnNegocios, btnSalir;
    TextView nombre;
    int IdVendedor=0;
    Vendedor v = new Vendedor();
    daoVendedor dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);





        nombre=(TextView)findViewById(R.id.nombreUsuario);
        btnEditar=(Button)findViewById(R.id.btnEditar);
        btnEliminar=(Button)findViewById(R.id.btnEliminar);
        btnNegocios=(Button)findViewById(R.id.btnNegocios);
        btnSalir=(Button)findViewById(R.id.btnSalir);
        btnEditar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnNegocios.setOnClickListener(this);
        btnSalir.setOnClickListener(this);

        Bundle b=getIntent().getExtras();
        IdVendedor=b.getInt("IdVendedor");

        dao=new daoVendedor(this);
        v=dao.getVendedorById(IdVendedor);

        Log.v("===========",String.valueOf(IdVendedor));


        if(v==null){
            nombre.setText(v.getNombre()+" "+v.getApellidos());
        } else {
            Log.v("Vendedor es null","null");        }


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnEditar:
                Intent a=new Intent(ProfileActivity.this,EditVendedorActivity.class);
                a.putExtra("IdVendedor",IdVendedor);
                startActivity(a);
                break;
            case R.id.btnEliminar:
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setMessage("Estas seguro de eliminar tu cuenta??");
                b.setCancelable(false);
                b.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(dao.deleteVendedor(IdVendedor)) {
                            Toast.makeText(ProfileActivity.this, "Se elminó sin problemas", Toast.LENGTH_SHORT).show();
                            Intent a=new Intent(ProfileActivity.this,LoginActivity.class);
                            startActivity(a);
                            finish();
                        }else {
                            Toast.makeText(ProfileActivity.this, "Error: No se eliminó la cuenta", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                b.show();
                break;
            case R.id.btnNegocios:
                Intent c=new Intent(ProfileActivity.this,NegociosActivity.class);
                c.putExtra("IdVendedor",IdVendedor);
                startActivity(c);
                finish();
                break;
            case R.id.btnSalir:
                Intent i2=new Intent(ProfileActivity.this,LoginActivity.class);
                startActivity(i2);
                finish();
                break;
        }


    }
}