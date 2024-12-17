
/**
 * Write a description of class rgafo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Grafo {
    private  NodoGrafo primero;
    private  NodoGrafo ultimo;

    public Grafo() {
        this.primero=null;
        this.ultimo=null;
    }

    public  boolean estaVacio(){
        return  this.primero==null && this.ultimo==null;
    }

    public  boolean existeVertice(Object dato){
        if (estaVacio())
            return  false;
        NodoGrafo actual= primero;
        while (actual!=null){
            if(actual.getDato().toString().equals(dato.toString()))
                return  true;
            actual= actual.getSiguiente();
        }
        return false;
    }

    public  boolean agregarArista(Object origen, Object destino){
        if(!existeVertice(origen) || !existeVertice(destino)){
            System.out.println("No se puede agregar");
            return false;
        }

        NodoGrafo actual= primero;
        while(!actual.getDato().toString().equals(origen.toString())){
            actual= actual.getSiguiente();
        }
        actual.getListaAdyacencia().agregarAdyacencia(destino);
        return  true;
    }
    public  boolean agregarArista(Object origen, Object destino, float peso){
        if(!existeVertice(origen) || !existeVertice(destino))
            return false;
        NodoGrafo actual= primero;
        while(!actual.getDato().toString().equals(origen.toString())){
            actual= actual.getSiguiente();
        }
        actual.getListaAdyacencia().agregarAdyacencia(destino,peso);
        return  true;
    }

    public  boolean agregarNodo(Object dato){
        if(existeVertice(dato))
            return  false;
        NodoGrafo nodo= new NodoGrafo(dato);
        if(estaVacio()){
            this.primero=nodo;
            this.ultimo=nodo;
            return  true;
        }
        this.ultimo.setSiguiente(nodo);
        this.ultimo=nodo;
        return  true;
    }
    
        public boolean addVertice(Object dato) {
        return agregarNodo(dato); 
    }

    @Override
    public String toString() {
        StringBuilder cadena = new StringBuilder();
        NodoGrafo actual = primero;
    
        while (actual != null) {
            cadena.append(actual.getDato().toString()).append(" -> ");
    
            Arista aristaActual = actual.getListaAdyacencia().getPrimero(); 
            while (aristaActual != null) {
                cadena.append(aristaActual.toString()).append("; ");
                aristaActual = aristaActual.getSiguiente();
            }
    
            cadena.append("\n");
            actual = actual.getSiguiente();
        }
        return cadena.toString();
    }
}
