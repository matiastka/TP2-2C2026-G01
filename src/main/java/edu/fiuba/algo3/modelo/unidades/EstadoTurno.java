package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public interface EstadoTurno {
    EstadoTurno mover(Unidad unidad, Posicion destino, Tablero tablero);
    EstadoTurno atacar(Mech mech, Posicion posicion, Tablero tablero);
    EstadoTurno deshacerMovimiento(Mech mech, Tablero tablero);
}