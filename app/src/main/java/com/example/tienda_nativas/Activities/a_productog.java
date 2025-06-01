package com.example.tienda_nativas.Activities;
//ackage com.example.tienda_nativas.Models;

import java.io.Serializable;

public class a_productog implements Serializable {
    private int id;
    private String nombre, descripcion, imagen;
    private double precio;
    private int cantidad;

    public a_productog(int id, String nombre, String descripcion, double precio, int cantidad, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imagen = imagen;
    }

    public a_productog(String nombre, String descripcion, double precio, int cantidad, String imagen) {
        this(-1, nombre, descripcion, precio, cantidad, imagen);
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }
    public String getImagen() { return imagen; }

    public void setId(int id) { this.id = id; }
}