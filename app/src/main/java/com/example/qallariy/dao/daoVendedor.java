package com.example.qallariy.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.qallariy.models.Vendedor;

import java.util.ArrayList;

public class daoVendedor {
    Context c;
    Vendedor v;
    ArrayList<Vendedor> lista;
    SQLiteDatabase sql;
    String bd="BDNeogocio";
    String table="create table if not exists vendedor(id integer primary key autoincrement,correo text, pass text, nombre text,ape text,nDocumento text, telefono text)";

    public daoVendedor(Context c) {
        this.c=c;
        sql=c.openOrCreateDatabase(bd,c.MODE_PRIVATE, null);
        sql.execSQL(table);
        v=new Vendedor();
    }

    public boolean insertVendedor(Vendedor v) {
        if (buscar(v.getCorreo())==0) {
            ContentValues cv=new ContentValues();
            cv.put("correo",v.getCorreo());
            cv.put("pass",v.getPassword());
            cv.put("nombre",v.getNombre());
            cv.put("ape",v.getApellidos());
            cv.put("nDocumento",v.getNumDocumento());
            cv.put("telefono",v.getTelefono());
            return(sql.insert("vendedor",null,cv)>0);

        }else {
            return false;
        }
    }

    private int buscar(String v) {
        int x=0;
        lista=selectVendedor();
        for (Vendedor ve:lista) {
            if(ve.getCorreo().equals(v)) {
                x++;
            }
        }
        return x;
    }

    private ArrayList<Vendedor> selectVendedor() {
        ArrayList<Vendedor> lista=new ArrayList<Vendedor>();
        lista.clear();;
        Cursor cr=sql.rawQuery("select * from vendedor",null);
        if(cr!=null&&cr.moveToFirst()) {
            Log.v("valor del cursor",cr.getString(3));
        do {
                Vendedor v=new Vendedor();
                v.setId(cr.getInt(0));
                v.setCorreo(cr.getString(1));
                v.setPassword(cr.getString(2));
                v.setNombre(cr.getString(3));
                v.setApellidos(cr.getString(4));
                v.setNumDocumento(cr.getString(5));
                v.setTelefono(cr.getString(6));
                lista.add(v);
            }while (cr.moveToNext());

        }
        return lista;

    }

    public int login(String v, String p) {
        int a=0;
        Cursor cr=sql.rawQuery("select * from vendedor",null);
        if(cr!=null&&cr.moveToFirst()) {
            do {
                if(cr.getString(1).equals(v)&&cr.getString(2).equals(p)) {
                    a++;
                }
            }while(cr.moveToNext());
        }
        return a;
    }

    public Vendedor getVendedor(String v, String p) {
        lista=selectVendedor();
        for (Vendedor ve:lista) {
            if (ve.getCorreo().equals(v)&&ve.getPassword().equals(p)) {
                return ve;
            }
        }
        return null;
    }

    public Vendedor getVendedorById(int id) {
        lista=selectVendedor();
        for (Vendedor ve:lista) {
            Log.v("id de parametro",String.valueOf(id));
            if(ve.getId()==id) {
                Log.v("Retorna vendedor",String.valueOf(lista.size()));
                Log.v("Id Vendedor",String.valueOf(ve.getId()));
            return ve;
            }
        }

        return null;
    }

    public boolean updateVendedor(Vendedor v) {
            ContentValues cv=new ContentValues();
            cv.put("correo",v.getCorreo());
            cv.put("pass",v.getPassword());
            cv.put("nombre",v.getNombre());
            cv.put("ape",v.getApellidos());
            cv.put("nDocumento",v.getNumDocumento());
            cv.put("telefono",v.getTelefono());
            return(sql.update("vendedor",cv,"id="+v.getId(),null)>0);

    }

    public boolean deleteVendedor(int id) {
        return (sql.delete("vendedor","id="+id,null)>0);
    }



}
