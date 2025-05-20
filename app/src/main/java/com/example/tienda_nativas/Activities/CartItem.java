package com.example.tienda_nativas.Activities;

public class CartItem {
    private String nombre;
    private int precio;
    private int imagenResourceId;

    public CartItem(String nombre, int precio, int imagenResourceId) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagenResourceId = imagenResourceId;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public int getImagenResourceId() {
        return imagenResourceId;
    }
}
