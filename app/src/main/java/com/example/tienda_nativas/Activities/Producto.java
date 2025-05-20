package com.example.tienda_nativas.Activities;

public class Producto {
    private String nombre;
    private String precio;
    private int imagen;

    public Producto(String nombre, String precio, int imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public int getImagen() {
        return imagen;
    }
}