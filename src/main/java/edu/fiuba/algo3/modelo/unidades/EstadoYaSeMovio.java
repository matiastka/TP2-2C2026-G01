package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EstadoYaSeMovio implements EstadoTurnoMech {

    @Override
    public ResultadoAccion<EstadoTurnoMech> mover(Mech mech, Posicion destino, Tablero tablero) {
        return ResultadoAccion.rechazo(MotivoRechazoAccion.MOVIMIENTO_YA_REALIZADO);
    }

    @Override
    public ResultadoAccion<EstadoTurnoMech> atacar(Mech mech, Posicion posicion, Tablero tablero) {
        return mech.ejecutarAtaqueValidado(posicion, tablero)
                .map(ignorado -> new EstadoTurnoAgotado());
    }

    @Override
    public ResultadoAccion<EstadoTurnoMech> deshacerMovimiento(Mech mech, Tablero tablero) {
        mech.ejecutarDeshacerMovimiento(tablero);
        return ResultadoAccion.exito(new EstadoDisponibleSinDeshacer());
    }
}