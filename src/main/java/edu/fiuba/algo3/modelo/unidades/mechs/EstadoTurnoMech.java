package edu.fiuba.algo3.modelo.unidades.mechs;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public interface EstadoTurnoMech {
    EstadoTurnoMech mover(Mech mech, Posicion destino, Tablero tablero);
    EstadoTurnoMech atacar(Mech mech, Posicion posicion, Tablero tablero);
    EstadoTurnoMech deshacerMovimiento(Mech mech, Tablero tablero);
}