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
    public void insertarInicio(String nombre, double precio, String categoria, int cantidad) {
        Producto nuevo = new Producto(nombre, precio, categoria, cantidad);
        nuevo.setSiguiente(primero);
        primero = nuevo;
    }

    //Metodo para buscar un producto
    public Producto buscar(String nombre) {
        if (esVacia()) {
            System.out.println("\nLa lista esta vacia.");
            return null;
        }
        Producto temp = primero;
        while (temp != null) {
            if (temp.getNombre().equalsIgnoreCase(nombre)) {
                return temp;
            }
            temp = temp.getSiguiente();
        }
        System.out.println("\nEl producto no esta en la lista.");
        return null;
    }

    //Metodo para modificar


    //Metodo para eliminar


    //Metodo para mostrar inventario
    public void mostrarInventario() {

        if (esVacia()) {
            System.out.println("\nLa lista esta vacia.");
            return;
        }

        Producto temp = primero;
        int contador = 1;

        System.out.println("\nINVENTARIO");

        while (temp != null) {
            System.out.println("\n PRODUCTO #" + contador + ": ");
            System.out.println(temp);

            temp = temp.getSiguiente();
            contador++;
        }
    }


    //Metodo para mostrar reporte de costos
    public void reporteCostos(){
        if (esVacia()) {
            System.out.println("\nLa lista esta vacia.");
            return;
        }

        Producto temp = primero;
        double TotalGeneral = 0;

        System.out.println( "REPORTE DE COSTOS \n");
        while (temp != null) {

            double subtotal = temp.getCantidad() * temp.getPrecio();
            System.out.println("Producto: " + temp.getNombre());
            System.out.println("Cantidad: " + temp.getCantidad());
            System.out.println("Precio unitario: ₡" + temp.getPrecio());
            System.out.println("Subtotal: ₡" + subtotal);
            System.out.println("-----------------------------");

            TotalGeneral += subtotal;

            temp = temp.getSiguiente();
        }

        System.out.println("TOTAL GENERAL: ₡" + TotalGeneral);
    }
}
