package edu.fiuba.algo3.modelo;

public class EstadoYaSeMovioSinDeshacer implements EstadoTurno {

    @Override
    public EstadoTurno mover(Unidad unidad, Posicion destino, Tablero tablero) {
        System.out.println("Movimiento inválido: la unidad ya se movió en este turno.");
        return this;
    }

    @Override
    public EstadoTurno atacar(Mech mech, Posicion posicion, Tablero tablero) {
        if (mech.ejecutarAtaqueValidado(posicion, tablero)) {
            return new EstadoTurnoAgotado();
        }
        return this;
    }

    @Override
    public EstadoTurno deshacerMovimiento(Mech mech, Tablero tablero) {
        System.out.println("El Mech ya deshizo un movimiento en este turno.");
        return this;
    }
}