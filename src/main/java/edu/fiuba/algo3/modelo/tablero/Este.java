package edu.fiuba.algo3.modelo.tablero;

public class Este extends Direccion {

    @Override
    public Posicion desplazar(int fila, int columna) {
        return new Posicion(fila, columna + 1);
    }
}
