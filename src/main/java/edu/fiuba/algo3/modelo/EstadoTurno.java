package edu.fiuba.algo3.modelo;

public interface EstadoTurno {
    EstadoTurno mover(Unidad unidad, Posicion destino, Tablero tablero);
    EstadoTurno atacar(Mech mech, Posicion posicion, Tablero tablero);
    EstadoTurno deshacerMovimiento(Mech mech, Tablero tablero);
}