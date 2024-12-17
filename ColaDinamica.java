
/**
 * Write a description of class asdasdsd here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class ColaDinamica<T> {
    private Nodo<T> primero;
    private Nodo<T> ultimo;

    public ColaDinamica() {
        this.primero = null;
        this.ultimo = null;
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public void agregar(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (estaVacia()) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }
    }

    public T eliminar() throws Exception {
        if (estaVacia()) {
            throw new Exception("La cola está vacía.");
        }
        T valor = primero.getValor();
        primero = primero.getSiguiente();
        if (primero == null) { // Si la cola quedó vacía
            ultimo = null;
        }
        return valor;
    }

    public T peek() throws Exception {
        if (estaVacia()) {
            throw new Exception("La cola está vacía.");
        }
        return primero.getValor();
    }
}
