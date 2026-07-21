package gestionInventario;

public class Tienda {
    // Atributos de la tienda que al final son inventario y la cola de los clientes 
    private ArbolProductos inventario;
    private ColaClientes colaClientes;

    // Constructor que inicializa las estructuras
    public Tienda() {
        this.inventario = new ArbolProductos();
        this.colaClientes = new ColaClientes();
    }

    // Getters para que puedas usarlas desde el Main
    public ArbolProductos getInventario() {
        return inventario;
    }

    public ColaClientes getColaClientes() {
        return colaClientes;
    }
}