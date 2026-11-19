package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EstadoTurnoAgotado implements EstadoTurnoMech {

    @Override
    public ResultadoAccion<EstadoTurnoMech> mover(Mech mech, Posicion destino, Tablero tablero) {
        return ResultadoAccion.rechazo(MotivoRechazoAccion.TURNO_AGOTADO);
    }

    @Override
    public ResultadoAccion<EstadoTurnoMech> atacar(Mech mech, Posicion posicion, Tablero tablero) {
        return ResultadoAccion.rechazo(MotivoRechazoAccion.TURNO_AGOTADO);
    }

    @Override
    public ResultadoAccion<EstadoTurnoMech> deshacerMovimiento(Mech mech, Tablero tablero) {
        return ResultadoAccion.rechazo(MotivoRechazoAccion.ACCION_NO_DISPONIBLE);
    }
}