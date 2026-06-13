package gestionInventario;

//Clase ListaProductos: lista enlazada simple de productos
public class ListaProductos {

    //Atributo
    private Producto primero;

    //Metodo constructor
    public ListaProductos() {
        primero = null;
    }

    //Metodo esVacia
    private boolean esVacia() {
        return primero == null;
    }

    //Metodo para insertar un producto al inicio
    public void insertarInicio(String nombre, double precio, String categoria, String fechaVencimiento, int cantidad) {
        Producto nuevo = new Producto(nombre, precio, categoria, fechaVencimiento, cantidad);
        nuevo.setSiguiente(primero);
        primero = nuevo;
    }

    //Metodo para buscar un producto
    public Producto buscar(String nombre) {
        if (esVacia()) {
            System.out.println("La lista esta vacia.");
            return null;
        }
        Producto temp = primero;
        while (temp != null) {
            if (temp.getNombre().equalsIgnoreCase(nombre)) {
                return temp;
            }
            temp = temp.getSiguiente();
        }
        System.out.println("El producto no esta en la lista.");
        return null;
    }
}
