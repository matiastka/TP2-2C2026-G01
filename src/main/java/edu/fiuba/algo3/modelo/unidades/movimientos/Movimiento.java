package edu.fiuba.algo3.modelo.unidades.movimientos;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Unidad;

import java.util.List;

public interface Movimiento {
    void validarCamino(Posicion origen, Posicion destino, int rango, Tablero tablero);

    void caerEnAcido(Unidad unidad);

    void caerEnAgua(Unidad unidad);

    void agregarSiPuedeTerminarEn(Casilla casilla, List<Posicion> destinos);
}
