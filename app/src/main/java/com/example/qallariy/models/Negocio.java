package com.example.qallariy.models;

import java.io.Serializable;

public class Negocio implements Serializable {

    private int codigo;
    private String picture;
    private String name;
    private String description;
    private String categoria;
    private int idVendedor;

    public boolean isNull() {
        if(String.valueOf(codigo).equals("")&&picture.equals("")&&name.equals("")&&description.equals("")&&categoria.equals("")&&String.valueOf(idVendedor).equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public Negocio() {

    }

    public Negocio(int codigo, String picture, String name, String description, String categoria, int idVendedor) {
        this.codigo = codigo;
        this.picture = picture;
        this.name = name;
        this.description = description;
        this.categoria = categoria;
        this.idVendedor=idVendedor;
    }

    @Override
    public String toString() {
        return "Negocio{" +
                "codigo=" + codigo +
                ", picture='" + picture + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", categoria='" + categoria + '\'' +
                ", idVendedor=" + idVendedor +
                '}';
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }
}
