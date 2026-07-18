package gestionInventario;

//Clase ColaClientes: cola de prioridad de clientes (implementada como lista enlazada de Cliente).
//Se atiende primero al de MAYOR prioridad (3 = premium). Ojo: es al reves que en GestionTickets.
//Si hay empate de prioridad, se atiende primero al que llego antes (el que esta mas cerca del frente).
public class ColaClientes {

    //Atributo
    private Cliente frente;

    //Metodo constructor
    public ColaClientes() {
        frente = null;
    }

    //Metodo que indica si la cola esta vacia
    public boolean estaVacia() {
        return frente == null;
    }

    //Encolar un cliente respetando la prioridad (y FIFO en caso de empate)
    public void encolar(Cliente nuevo) {
        //Si la cola esta vacia, o el nuevo tiene MAYOR prioridad que el frente, va al frente
        if (estaVacia() || nuevo.getPrioridad() > frente.getPrioridad()) {
            nuevo.setSiguiente(frente);
            frente = nuevo;
            return;
        }
        //Avanzar mientras el siguiente tenga prioridad MAYOR O IGUAL a la del nuevo.
        //Al usar >=, el nuevo queda DESPUES de los de su misma prioridad -> se respeta el orden de llegada (FIFO).
        Cliente temp = frente;
        while (temp.getSiguiente() != null && temp.getSiguiente().getPrioridad() >= nuevo.getPrioridad()) {
            temp = temp.getSiguiente();
        }
        nuevo.setSiguiente(temp.getSiguiente());
        temp.setSiguiente(nuevo);
    }

    //Ver al cliente del frente (el proximo a atender) sin sacarlo de la cola
    public Cliente verFrente() {
        if (estaVacia()) {
            System.out.println("No hay clientes en la cola.");
            return null;
        }
        return frente;
    }

    //Atender: saca y retorna al cliente del frente (el de mayor prioridad)
    public Cliente atender() {
        if (estaVacia()) {
            System.out.println("No hay clientes en la cola.");
            return null;
        }
        Cliente atendido = frente;
        frente = frente.getSiguiente();
        atendido.setSiguiente(null);
        return atendido;
    }

    //Mostrar la cola en el orden en que se atenderan los clientes
    public void mostrarCola() {
        if (estaVacia()) {
            System.out.println("No hay clientes en la cola.");
            return;
        }
        System.out.println("\nCOLA DE CLIENTES (orden de atencion):");
        Cliente temp = frente;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.getSiguiente();
        }
    }
}
