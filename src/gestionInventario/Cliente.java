package gestionInventario;

//Clase Cliente: representa a un cliente de la tienda y es el nodo de la ColaClientes.
//Cada cliente tiene su propio carrito, que es una ListaProductos (lista enlazada).
public class Cliente {

    //Atributos
    private String nombre;
    private int prioridad;            // 1 = basico, 2 = afiliado, 3 = premium
    private ListaProductos carrito;   // carrito personal del cliente
    private Cliente siguiente;        // siguiente cliente en la cola

    //Metodo constructor
    public Cliente(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.carrito = new ListaProductos();
        this.siguiente = null;
    }

    //Metodo para agregar un producto al carrito del cliente
    public void agregarAlCarrito(String nombre, double precio, String categoria, int cantidad) {
        carrito.insertarFinal(nombre, precio, categoria, cantidad);
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public ListaProductos getCarrito() {
        return carrito;
    }

    public Cliente getSiguiente() {
        return siguiente;
    }

    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public void setSiguiente(Cliente siguiente) {
        this.siguiente = siguiente;
    }

    //toString
    public String toString() {
        String tipo;
        if (prioridad == 3) tipo = "Premium";
        else if (prioridad == 2) tipo = "Afiliado";
        else tipo = "Basico";
        return "Cliente: " + nombre + " (prioridad " + prioridad + " - " + tipo + ")";
    }
}
