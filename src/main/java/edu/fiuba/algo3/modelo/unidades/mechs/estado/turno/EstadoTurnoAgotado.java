package edu.fiuba.algo3.modelo.unidades.mechs.estado.turno;

import edu.fiuba.algo3.modelo.excepciones.AccionNoDisponibleException;
import edu.fiuba.algo3.modelo.excepciones.TurnoAgotadoException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;

public class EstadoTurnoAgotado implements EstadoTurnoMech {

    @Override
    public EstadoTurnoMech mover(Mech mech, Posicion destino, Tablero tablero) {
        throw new TurnoAgotadoException();
    }

    @Override
    public EstadoTurnoMech atacar(Mech mech, Posicion posicion, Tablero tablero) {
        throw new TurnoAgotadoException();
    }

    @Override
    public EstadoTurnoMech deshacerMovimiento(Mech mech, Tablero tablero) {
        throw new AccionNoDisponibleException();
    }
}