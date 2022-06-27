package com.example.qallariy.models;

public class Producto {
    int codigo;
    String image,nombreP,descripcion;
    double precio;
    int cantidad;
    int idNegocio;

    public boolean isNull() {
        if(String.valueOf(codigo).equals("")&&image.equals("")&&nombreP.equals("")&&descripcion.equals("")&&String.valueOf(precio).equals("")&&String.valueOf(cantidad).equals("")&&String.valueOf(idNegocio).equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public Producto() {

    }

    public Producto(int codigo,String image, String nombreP, String descripcion, double precio, int cantidad, int idNegocio) {
        this.codigo = codigo;
        this.image = image;
        this.nombreP = nombreP;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.idNegocio = idNegocio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", image='" + image + '\'' +
                ", nombreP='" + nombreP + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                ", idNegocio=" + idNegocio +
                '}';
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdNegocio() {
        return idNegocio;
    }

    public void setIdNegocio(int idNegocio) {
        this.idNegocio = idNegocio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
