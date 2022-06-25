package com.example.qallariy.models;

import java.io.Serializable;

public class Negocio implements Serializable {

    private int codigo;
    private String picture;
    private String name;
    private String description;
    private String categoria;


    public Negocio() {

    }

    public Negocio(int codigo, String picture, String name, String description, String categoria) {
        this.codigo = codigo;
        this.picture = picture;
        this.name = name;
        this.description = description;
        this.categoria = categoria;

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


}
