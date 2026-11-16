package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EstadoVekYaSeMovio implements EstadoTurnoVek {

    @Override
    public EstadoTurnoVek mover(Vek vek, Posicion destino, Tablero tablero) {
        System.out.println("Movimiento inválido: el Vek ya se movió en este turno.");
        return this;
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