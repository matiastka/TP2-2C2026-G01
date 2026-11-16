package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EstadoYaSeMovioSinDeshacer implements EstadoTurnoMech {

    @Override
    public EstadoTurnoMech mover(Mech mech, Posicion destino, Tablero tablero) {
        System.out.println("Movimiento inválido: la unidad ya se movió en este turno.");
        return this;
    }

    @Override
    public EstadoTurnoMech atacar(Mech mech, Posicion posicion, Tablero tablero) {
        if (mech.ejecutarAtaqueValidado(posicion, tablero)) {
            return new EstadoTurnoAgotado();
        }
        return this;
    }

    @Override
    public EstadoTurnoMech deshacerMovimiento(Mech mech, Tablero tablero) {
        System.out.println("El Mech ya deshizo un movimiento en este turno.");
        return this;
    }
}