
package edu.fiuba.algo3.modelo;

public class Posicion {
    private int fila;
    private int columna;

    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() { return fila; }
    public int getColumna() { return columna; }

    public int distanciaA(Posicion otra) {
        return Math.abs(this.fila - otra.fila) + Math.abs(this.columna - otra.columna);
    }
}