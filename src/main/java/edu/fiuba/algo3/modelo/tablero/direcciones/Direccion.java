package edu.fiuba.algo3.modelo.tablero.direcciones;

import edu.fiuba.algo3.modelo.tablero.Posicion;

import java.util.List;

public abstract class Direccion {
    public abstract Posicion desplazar(int fila, int columna);

    public static List<Direccion> cardinales() {
        return List.of(new Norte(), new Sur(), new Este(), new Oeste());
    }

    @Override
    public boolean equals(Object otra) {
        return otra != null && this.getClass() == otra.getClass();
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }


}
