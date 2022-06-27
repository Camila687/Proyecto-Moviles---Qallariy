package com.example.qallariy.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.qallariy.models.Producto;

import java.util.ArrayList;

public class daoProducto {
    Context c;
    Producto p;
    ArrayList<Producto> lista;
    SQLiteDatabase sql;
    String bd="BDNeogocio";
    String table="create table if not exists producto(codigo int,nombre varchar,descripcion varchar, precio decimal,cantidad int, idNegocio int)";

    public daoProducto(Context c) {
        this.c=c;
        sql=c.openOrCreateDatabase(bd,c.MODE_PRIVATE, null);
        sql.execSQL(table);
        p=new Producto();
    }

    public boolean insertProducto(Producto p) {
        if (buscar(String.valueOf(p.getCodigo()))==0) {
            ContentValues cv=new ContentValues();
            cv.put("codigo",p.getCodigo());
            cv.put("nombre",p.getNombre());
            cv.put("descripcion",p.getDescripcion());
            cv.put("precio",p.getPrecio());
            cv.put("cantidad",p.getCantidad());
            cv.put("idNegocio",p.getIdNegocio());
            return(sql.insert("producto",null,cv)>0);

        }else {
            return false;
        }
    }

    private int buscar(String p) {
        int x=0;
        lista=selectProducto();
        for (Producto pr:lista) {
            if(String.valueOf(pr.getIdNegocio()).equals(p)) {
                x++;
            }
        }
        return x;
    }

    private ArrayList<Producto> selectProducto() {
        ArrayList<Producto> lista=new ArrayList<Producto>();
        lista.clear();;
        Cursor cr=sql.rawQuery("select * from producto",null);
        if(cr!=null&&cr.moveToFirst()) {
            do {
                Producto p=new Producto();
                p.setCodigo(cr.getInt(0));
                p.setNombre(cr.getString(1));
                p.setDescripcion(cr.getString(2));
                p.setPrecio(cr.getDouble(3));
                p.setCantidad(cr.getInt(4));
                p.setIdNegocio(cr.getInt(5));
                lista.add(p);
            }while (cr.moveToNext());

        }
        Log.v("======",lista.get(0).getNombre());
        return lista;

    }

    public Producto getProducto(int p) {
        lista=selectProducto();
        for (Producto pr:lista) {
            if (String.valueOf(pr.getIdNegocio()).equals(p)) {
                return pr;
            }
        }
        return null;
    }

    public Producto getProductoById(int id) {
        lista=selectProducto();
        for (Producto pr:lista) {
            if(pr.getCodigo()==id) {
                return pr;
            }
        }
        return null;
    }

    public boolean updateProducto(Producto p) {
        ContentValues cv=new ContentValues();
        cv.put("codigo",p.getCodigo());
        cv.put("nombre",p.getNombre());
        cv.put("descripcion",p.getDescripcion());
        cv.put("precio",p.getPrecio());
        cv.put("cantidad",p.getCantidad());
        cv.put("idNegocio",p.getIdNegocio());
        return(sql.update("producto",cv,"id="+p.getCodigo(),null)>0);

    }

    public boolean deleteProducto(int id) {
        return (sql.delete("producto","id="+id,null)>0);
    }

}
