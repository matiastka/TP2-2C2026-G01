package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.MotivoRechazoAccion;
import edu.fiuba.algo3.modelo.unidades.ResultadoAccion;

public class EstadoVekConAtaquePreparado implements EstadoTurnoVek {

    @Override
    public ResultadoAccion<EstadoTurnoVek> mover(Vek vek, Posicion destino, Tablero tablero) {
        return ResultadoAccion.rechazo(MotivoRechazoAccion.ATAQUE_YA_PREPARADO);
    }

    @Override
    public ResultadoAccion<EstadoTurnoVek> prepararAtaque(Vek vek, Direccion direccion) {
        return ResultadoAccion.rechazo(MotivoRechazoAccion.ATAQUE_YA_PREPARADO);
    }

    @Override
    public ResultadoAccion<EstadoTurnoVek> resolverAtaque(Vek vek, Tablero tablero) {
        vek.ejecutarAtaquePreparado(tablero);
        return ResultadoAccion.exito(new EstadoVekDisponible());
    }
}