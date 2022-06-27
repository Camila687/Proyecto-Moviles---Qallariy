package com.example.qallariy.models;

public class Producto {
    int codigo;
    String nombre,descripcion;
    double precio;
    int cantidad;
    int idNegocio;

    public boolean isNull() {
        if(String.valueOf(codigo).equals("")&&nombre.equals("")&&descripcion.equals("")&&String.valueOf(precio).equals("")&&String.valueOf(cantidad).equals("")&&String.valueOf(idNegocio).equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public Producto() {

    }

    public Producto(int codigo, String nombre, String descripcion, double precio, int cantidad, int idNegocio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.idNegocio = idNegocio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
}
