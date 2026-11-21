package edu.fiuba.algo3.modelo.tablero.direcciones;

import edu.fiuba.algo3.modelo.tablero.Posicion;

public class Norte extends Direccion {

    @Override
    public Posicion desplazar(int fila, int columna) {
        return new Posicion(fila - 1, columna);
    }
}
