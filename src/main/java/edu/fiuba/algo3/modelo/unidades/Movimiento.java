package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public interface Movimiento {
    boolean puedeLlegar(Posicion origen, Posicion destino, int rango, Tablero tablero);

    void caerEnAcido(Unidad unidad);

    void caerEnAgua(Unidad unidad);
}
