package edu.fiuba.algo3.modelo;

public class EstadoTurnoAgotado implements EstadoTurno {

    @Override
    public EstadoTurno mover(Unidad unidad, Posicion destino, Tablero tablero) {
        System.out.println("Movimiento inválido: la unidad ya agotó sus acciones en este turno.");
        return this;
    }

    @Override
    public EstadoTurno atacar(Mech mech, Posicion posicion, Tablero tablero) {
        System.out.println("Ataque inválido: el Mech ya atacó en este turno.");
        return this;
    }

    @Override
    public EstadoTurno deshacerMovimiento(Mech mech, Tablero tablero) {
        System.out.println("El Mech ya atacó en este turno. No se puede volver a la posicion anterior.");
        return this;
    }
}