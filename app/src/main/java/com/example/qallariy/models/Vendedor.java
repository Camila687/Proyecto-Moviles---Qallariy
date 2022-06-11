package com.example.qallariy.models;

public class Vendedor {
    int id;
    String Nombre, Apellidos, Correo, Password,NumDocumento, Telefono;

    public Vendedor() {

    }

    public Vendedor(String nombre, String apellidos, String correo, String password, String numDocumento, String telefono) {
        Nombre = nombre;
        Apellidos = apellidos;
        Correo = correo;
        Password = password;
        NumDocumento = numDocumento;
        Telefono = telefono;
    }

    public boolean isNull() {
        if(Nombre.equals("")&&Apellidos.equals("")&&Correo.equals("")&&Password.equals("")&&NumDocumento.equals("")&&Telefono.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "id=" + id +
                ", Nombre='" + Nombre + '\'' +
                ", Apellidos='" + Apellidos + '\'' +
                ", Correo='" + Correo + '\'' +
                ", Password='" + Password + '\'' +
                ", NumDocumento='" + NumDocumento + '\'' +
                ", Telefono='" + Telefono + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNumDocumento() {
        return NumDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        NumDocumento = numDocumento;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
}
