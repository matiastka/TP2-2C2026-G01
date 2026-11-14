package edu.fiuba.algo3.modelo;

public class EstadoDisponible implements EstadoTurno {

    @Override
    public EstadoTurno mover(Unidad unidad, Posicion destino, Tablero tablero) {
        if (unidad.ejecutarMovimiento(destino, tablero)) {
            return new EstadoYaSeMovio();
        }
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
        System.out.println("Acción inválida: el Mech todavía no se movió en este turno.");
        return this;
    }
}