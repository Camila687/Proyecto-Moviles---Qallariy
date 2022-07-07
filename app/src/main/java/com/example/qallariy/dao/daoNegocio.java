package com.example.qallariy.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.qallariy.models.Negocio;

import java.util.ArrayList;

public class daoNegocio {
    Context c;
    Negocio n;
    ArrayList<Negocio> lista;
    SQLiteDatabase sql;
    String bd="BDNeogocio";
    String table="create table if not exists negocio(codigo int,image varchar,nombre varchar, descripcion varchar,categoria varchar, direccion varchar, idVendedor int)";

    public daoNegocio(Context c) {
        this.c=c;
        sql=c.openOrCreateDatabase(bd,c.MODE_PRIVATE, null);
        sql.execSQL(table);
        n=new Negocio();
    }

    public boolean insertNegocio(Negocio n) {
        if (buscar(String.valueOf(n.getCodigo()))==1) {
            ContentValues cv=new ContentValues();
            cv.put("codigo",n.getCodigo());
            cv.put("image",n.getPicture());
            cv.put("nombre",n.getName());
            cv.put("descripcion",n.getDescription());
            cv.put("categoria",n.getCategoria());
            cv.put("direccion",n.getDireccion());
            cv.put("idVendedor",n.getIdVendedor());
            return(sql.insert("negocio",null,cv)>0);

        }else {
            return false;
        }
    }

    private int buscar(String n) {
        int x=1;
        lista=selectNegocio();
        for (Negocio ne:lista) {
            if(String.valueOf(ne.getIdVendedor()).equals(n)) {
                x++;
            }
        }
        return x;
    }

    public ArrayList<Negocio> selectNegocio() {
        ArrayList<Negocio> lista=new ArrayList<Negocio>();
        lista.clear();;
        Cursor cr=sql.rawQuery("select * from negocio",null);
        if(cr!=null&&cr.moveToFirst()) {
            do {
                Negocio n=new Negocio();
                n.setCodigo(cr.getInt(0));
                n.setPicture(cr.getString(1));
                n.setName(cr.getString(2));
                n.setDescription(cr.getString(3));
                n.setCategoria(cr.getString(4));
                n.setDireccion(cr.getString(5));
                n.setIdVendedor(cr.getInt(6));
                lista.add(n);
            }while (cr.moveToNext());

        }
        if(lista == null) {Log.v("lista","nulonulonulo");}
        if(lista.size() == 0) {Log.v("lista","vaciavaciavaciaa");}

        return lista;

    }


    public Negocio getNegocio(int n) {
        lista=selectNegocio();
        for (Negocio ne:lista) {
            if (String.valueOf(ne.getIdVendedor()).equals(n)) {
                return ne;
            }
        }
        return null;
    }

    public Negocio getNegocioById(int id) {
        lista=selectNegocio();
        for (Negocio ne:lista) {
            if(ne.getCodigo()==id) {
                return ne;
            }
        }
        return null;
    }

    public boolean updateNegocio(Negocio n) {
            ContentValues cv=new ContentValues();
            cv.put("codigo",n.getCodigo());
            cv.put("image",n.getPicture());
            cv.put("nombre",n.getName());
            cv.put("descripcion",n.getDescription());
            cv.put("categoria",n.getCategoria());
            cv.put("direccion",n.getDireccion());
            cv.put("idVendedor",n.getIdVendedor());
            return(sql.update("negocio",cv,"codigo="+n.getCodigo(),null)>0);

    }

    public boolean deleteNegocio(int id) {
        Log.v("=====",String.valueOf(id));
        return (sql.delete("negocio","codigo="+id,null)>0);
    }

    public ArrayList<Negocio> NegociobyVendedor(int id) {
        ArrayList<Negocio> lista=new ArrayList<Negocio>();
        lista.clear();;
        Cursor cr=sql.rawQuery("select * from negocio where idVendedor="+id,null);
        if(cr!=null&&cr.moveToFirst()) {
            do {
                Negocio n=new Negocio();
                n.setCodigo(cr.getInt(0));
                n.setPicture(cr.getString(1));
                n.setName(cr.getString(2));
                n.setDescription(cr.getString(3));
                n.setCategoria(cr.getString(4));
                n.setDireccion(cr.getString(5));
                n.setIdVendedor(cr.getInt(6));
                lista.add(n);
            }while (cr.moveToNext());

        }
        return lista;

    }



}
