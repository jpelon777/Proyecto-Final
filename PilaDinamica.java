
/**
 * Write a description of class czxczxczxc here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class PilaDinamica<T> {
    private Nodo<T> tope;

    public PilaDinamica() {
        this.tope = null;
    }

    public boolean estaVacia() {
        return this.tope == null;
    }

    public void push(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        nuevo.setSiguiente(tope);
        tope = nuevo;
    }

    public T pop() throws Exception {
        if (estaVacia()) {
            throw new Exception("La pila está vacía.");
        }
        T valor = tope.getValor();
        tope = tope.getSiguiente();
        return valor;
    }

    public T peek() throws Exception {
        if (estaVacia()) {
            throw new Exception("La pila está vacía.");
        }
        return tope.getValor();
    }
}
