package edu.fiuba.algo3.modelo.tablero;

public class Norte extends Direccion {

    @Override
    public Posicion desplazar(int fila, int columna) {
        return new Posicion(fila - 1, columna);
    }
}
