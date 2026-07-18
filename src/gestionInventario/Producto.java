package gestionInventario;

import java.time.LocalDate;
import java.util.ArrayList;


public class Producto {

    //Atributos
    private String nombre;
    private double precio;
    private String categoria;
    private LocalDate fechaRegistro;
    private int cantidad;
    private ArrayList<String> listaImagenes;
    private Producto siguiente;   // siguiente nodo cuando el Producto va en una lista (el carrito del Cliente)
    private Producto izquierda;   // hijo izquierdo cuando el Producto es nodo del arbol (ArbolProductos)
    private Producto derecha;     // hijo derecho cuando el Producto es nodo del arbol (ArbolProductos)

    //Metodo constructor
    public Producto(String nombre, double precio, String categoria, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.fechaRegistro = LocalDate.now();
        this.cantidad = cantidad;
        this.listaImagenes = new ArrayList<>();
        this.siguiente = null;
        this.izquierda = null;
        this.derecha = null;
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

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
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

    public Producto getIzquierda() {
        return izquierda;
    }

    public Producto getDerecha() {
        return derecha;
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

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setSiguiente(Producto siguiente) {
        this.siguiente = siguiente;
    }

    public void setIzquierda(Producto izquierda) {
        this.izquierda = izquierda;
    }

    public void setDerecha(Producto derecha) {
        this.derecha = derecha;
    }

    //Metodo to String
    public String toString() {
        String fecha = (fechaRegistro == null) ? "No aplica" : fechaRegistro.toString();
        return "\nProducto: " + nombre +
               "\nPrecio: " + precio +
               "\nCategoria: " + categoria +
               "\nFecha de registro: " + fecha +
               "\nCantidad: " + cantidad +
               "\nImagenes: " + listaImagenes;
    }
}
