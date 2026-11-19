package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public interface EstadoTurnoMech {
    ResultadoAccion<EstadoTurnoMech> mover(Mech mech, Posicion destino, Tablero tablero);
    ResultadoAccion<EstadoTurnoMech> atacar(Mech mech, Posicion posicion, Tablero tablero);
    ResultadoAccion<EstadoTurnoMech> deshacerMovimiento(Mech mech, Tablero tablero);
}