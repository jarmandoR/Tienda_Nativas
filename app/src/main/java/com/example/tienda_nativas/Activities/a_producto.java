package com.example.tienda_nativas.Activities;

//package com.example.tienda_nativas.Models;

public class a_producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;
    private String imagen; // Ruta o nombre del archivo

    public a_producto() {}

    public a_producto(String nombre, String descripcion, double precio, int cantidad, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imagen = imagen;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
}