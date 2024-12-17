
/**
 * Write a description of class cxczc here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class ListaLigada
{
    private Nodo primero;
    private Nodo ultimo;
    
    public ListaLigada() {
        this.primero = null;
        this.ultimo = null;
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public boolean agregarFrente(int valor) {
        Nodo nuevo = new Nodo(valor);
        if(estaVacia()){
            primero=nuevo;
            ultimo=nuevo;
            return true;
        }
        
        nuevo.setSiguiente(primero);
        primero=nuevo;
        return true;
    }

    public boolean agregarFin(int valor) {
        Nodo nuevo = new Nodo(valor);
        if (estaVacia()) {
            primero=nuevo;
            ultimo=nuevo;
            return true;
        } 
            ultimo.setSiguiente(nuevo);
            ultimo=nuevo;
            return true;
    }

    public int eliminarFrente() throws Exception {
        if (estaVacia()) {
            throw new Exception("La lista está vacía");
        }
        int auxValor=primero.getValor();
        primero=primero.getSiguiente();
        return auxValor;    
    }

    public int eliminarFin() throws Exception {
        if (estaVacia()) {
            throw new Exception("La lista está vacía");
        } 
        Nodo nodoAux=primero;
        while(nodoAux.getSiguiente() != null && nodoAux.getSiguiente().getSiguiente() != null){
            nodoAux = nodoAux.getSiguiente();
        }
        int auxValor=ultimo.getValor();
        ultimo=nodoAux;
        ultimo.setSiguiente(null);
        return auxValor;
    }
}