
/**
 * Write a description of class sadsda here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class NodoAVL<T extends Comparable<T>> {
    private T valor;
    private NodoAVL<T> izq;
    private NodoAVL<T> der;
    private int altura;

    public NodoAVL(T valor) {
        this.valor = valor;
        this.altura = 1; 
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public NodoAVL<T> getIzq() {
        return izq;
    }

    public void setIzq(NodoAVL<T> izq) {
        this.izq = izq;
    }

    public NodoAVL<T> getDer() {
        return der;
    }

    public void setDer(NodoAVL<T> der) {
        this.der = der;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}

