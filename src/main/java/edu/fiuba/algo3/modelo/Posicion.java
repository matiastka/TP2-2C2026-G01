
package edu.fiuba.algo3.modelo;

import java.util.Objects;

public class Posicion {
    private final int fila;
    private final int columna;

    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public Posicion siguienteEn(Direccion direccion){
        return direccion.desplazar(fila, columna);
    }

    public Direccion direccionHacia(Posicion otra){
        int incrementoFila = Integer.compare(otra.fila, this.fila);
        int incrementoColumna = Integer.compare(otra.columna, this.columna);
        Posicion paso = new Posicion(fila + incrementoFila, columna + incrementoColumna);

        for (Direccion direccion : Direccion.values()) {
            if (siguienteEn(direccion).equals(paso)) {
                return direccion;
            }
        }
        return Direccion.NINGUNA;
    }

    public int getFila() { return fila; }
    public int getColumna() { return columna; }

    public int distanciaA(Posicion otra) {
        return Math.abs(this.fila - otra.fila) + Math.abs(this.columna - otra.columna);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Posicion)) return false;
        Posicion otra = (Posicion) o;
        return fila == otra.fila && columna == otra.columna;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fila, columna);
    }



}