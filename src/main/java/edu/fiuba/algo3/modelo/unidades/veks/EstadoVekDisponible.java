package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.ResultadoAccion;

public class EstadoVekDisponible implements EstadoTurnoVek {

    @Override
    public ResultadoAccion<EstadoTurnoVek> mover(Vek vek, Posicion destino, Tablero tablero) {
        return vek.ejecutarMovimientoVek(destino, tablero)
                .map(ignorado -> new EstadoVekYaSeMovio());
    }

    @Override
    public ResultadoAccion<EstadoTurnoVek> prepararAtaque(Vek vek, Direccion direccion) {
        vek.guardarDireccionAtaque(direccion);
        return ResultadoAccion.exito(new EstadoVekConAtaquePreparado());
    }

    @Override
    public ResultadoAccion<EstadoTurnoVek> resolverAtaque(Vek vek, Tablero tablero) {
        // No tiene ataque preparado todavía
        return ResultadoAccion.exito(this);
    }
}