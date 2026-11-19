package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Terreno;

public interface Movimiento {
    ResultadoAccion<Void> validarCamino(Posicion origen, Posicion destino, int rango, Tablero tablero);

    void caerEnAcido(Unidad unidad);

    void caerEnAgua(Unidad unidad);

    boolean esSeguroTerminarEn(Terreno terreno);
}
