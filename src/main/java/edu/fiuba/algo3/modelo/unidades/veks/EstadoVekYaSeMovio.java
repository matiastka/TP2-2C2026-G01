package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.MovimientoYaRealizadoException;

public class EstadoVekYaSeMovio implements EstadoTurnoVek {

    @Override
    public EstadoTurnoVek mover(Vek vek, Posicion destino, Tablero tablero) {
        throw new MovimientoYaRealizadoException();
    }

    @Override
    public EstadoTurnoVek prepararAtaque(Vek vek, Direccion direccion) {
        vek.guardarDireccionAtaque(direccion);
        return new EstadoVekConAtaquePreparado();
    }

    @Override
    public EstadoTurnoVek resolverAtaque(Vek vek, Tablero tablero) {
        return this;
    }
}