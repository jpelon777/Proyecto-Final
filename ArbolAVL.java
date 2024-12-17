import java.util.ArrayList;

/**
 * Write a description of class czxczxc here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class ArbolAVL<T extends Comparable<T>> {
    protected NodoAVL<T> raiz;
    private int size;  

    public ArbolAVL() {
        this.raiz = null;
        this.size = 0;
    }

    public void insertar(T valor) {
        this.raiz = generarNodo(valor, raiz);
        size++;  
    }
    
    public T buscar(T valor) {
        return buscarRecursivo(raiz, valor);
    }
    
    private T buscarRecursivo(NodoAVL<T> nodo, T valor) {
        if (nodo == null) {
            return null; 
        }
    
        int comparacion = valor.compareTo(nodo.getValor());
        if (comparacion == 0) {
            return nodo.getValor(); 
        } else if (comparacion < 0) {
            return buscarRecursivo(nodo.getIzq(), valor); 
        } else {
            return buscarRecursivo(nodo.getDer(), valor); 
        }
    }


    private NodoAVL<T> generarNodo(T valor, NodoAVL<T> nodo) {
        if (nodo == null) {
            return new NodoAVL<>(valor);
        }

        if (valor.compareTo(nodo.getValor()) < 0) {
            nodo.setIzq(generarNodo(valor, nodo.getIzq()));
        } else if (valor.compareTo(nodo.getValor()) > 0) {
            nodo.setDer(generarNodo(valor, nodo.getDer()));
        }

        int nuevaAltura = 1 + Math.max(determinarAltura(nodo.getIzq()), determinarAltura(nodo.getDer()));
        nodo.setAltura(nuevaAltura);

        int balance = determinarAltura(nodo.getDer()) - determinarAltura(nodo.getIzq());

        if (balance >= 2) {
            if (valor.compareTo(nodo.getDer().getValor()) > 0) {
                return rotacionIzq(nodo);
            }
        } else if (balance <= -2) {
            if (valor.compareTo(nodo.getIzq().getValor()) < 0) {
                return rotacionDer(nodo);
            }
        }

        return nodo;
    }

    private int determinarAltura(NodoAVL<T> nodo) {
        return (nodo == null) ? 0 : nodo.getAltura();
    }

    private NodoAVL<T> rotacionIzq(NodoAVL<T> x) {
        NodoAVL<T> y = x.getDer();
        NodoAVL<T> z = y.getIzq();

        y.setIzq(x);
        x.setDer(z);

        x.setAltura(1 + Math.max(determinarAltura(x.getIzq()), determinarAltura(x.getDer())));
        y.setAltura(1 + Math.max(determinarAltura(y.getIzq()), determinarAltura(y.getDer())));

        return y;
    }

    private NodoAVL<T> rotacionDer(NodoAVL<T> x) {
        NodoAVL<T> y = x.getIzq();
        NodoAVL<T> z = y.getDer();

        y.setDer(x);
        x.setIzq(z);

        x.setAltura(1 + Math.max(determinarAltura(x.getIzq()), determinarAltura(x.getDer())));
        y.setAltura(1 + Math.max(determinarAltura(y.getIzq()), determinarAltura(y.getDer())));

        return y;
    }

    public ArrayList<T> obtenerDatos() {
        ArrayList<T> lista = new ArrayList<>();
        inOrden(raiz, lista);
        return lista;
    }

    private void inOrden(NodoAVL<T> nodo, ArrayList<T> lista) {
        if (nodo != null) {
            inOrden(nodo.getIzq(), lista);
            lista.add(nodo.getValor());  
            inOrden(nodo.getDer(), lista);
        }
    }

    public int size() {
        return this.size;
    }
}

