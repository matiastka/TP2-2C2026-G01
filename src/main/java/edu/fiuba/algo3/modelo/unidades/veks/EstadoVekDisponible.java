package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EstadoVekDisponible implements EstadoTurnoVek {

    @Override
    public EstadoTurnoVek mover(Vek vek, Posicion destino, Tablero tablero) {
        if (vek.ejecutarMovimientoVek(destino, tablero)) {
            return new EstadoVekYaSeMovio();
        }
        return this;
    }

    @Override
    public EstadoTurnoVek prepararAtaque(Vek vek, Direccion direccion) {
        vek.guardarDireccionAtaque(direccion);
        return new EstadoVekConAtaquePreparado();
    }

    @Override
    public EstadoTurnoVek resolverAtaque(Vek vek, Tablero tablero) {
        // No tiene ataque preparado todavía
        return this;
    }
}