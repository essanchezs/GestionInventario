package gestionInventario;

import java.util.ArrayList;


public class Producto {

    //Atributos
    private String nombre;
    private double precio;
    private String categoria;
    private String fechaVencimiento;
    private int cantidad;
    private ArrayList<String> listaImagenes;
    private Producto siguiente;

    //Metodo constructor
    public Producto(String nombre, double precio, String categoria, String fechaVencimiento, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        this.listaImagenes = new ArrayList<>();
        this.siguiente = null;
    }

    //Metodo para agregar una imagen al producto
    public void agregarImagen(String ruta) {
        listaImagenes.add(ruta);
    }

    //Metodo getter
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public ArrayList<String> getListaImagenes() {
        return listaImagenes;
    }

    public Producto getSiguiente() {
        return siguiente;
    }

    //Metodo setter
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setSiguiente(Producto siguiente) {
        this.siguiente = siguiente;
    }

    //Metodo to String
    public String toString() {
        String fecha = (fechaVencimiento == null) ? "No aplica" : fechaVencimiento;
        return "\nProducto: " + nombre +
               "\nPrecio: " + precio +
               "\nCategoria: " + categoria +
               "\nFecha de vencimiento: " + fecha +
               "\nCantidad: " + cantidad +
               "\nImagenes: " + listaImagenes;
    }
}
