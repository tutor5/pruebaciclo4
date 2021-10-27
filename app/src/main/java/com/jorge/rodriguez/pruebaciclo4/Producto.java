package com.jorge.rodriguez.pruebaciclo4;

public class Producto {

    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;

    private long id;

    public Producto(String codigo, String nombre, String descripcion, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    // Constructor para cuando instanciamos desde la BD
    public Producto(String codigo, String nombre, String descripcion, double precio, long id) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
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

    @Override
    public String toString() {
        return "producto{" +
                "codigo='" + codigo + '\'' +
                "nombre='" + nombre + '\'' +
                "descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                '}';
    }






}
