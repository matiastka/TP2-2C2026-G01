package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EstadoDisponible implements EstadoTurnoMech {

    @Override
    public EstadoTurnoMech mover(Mech mech, Posicion destino, Tablero tablero) {
        if (mech.ejecutarMovimiento(destino, tablero)) {
            return new EstadoYaSeMovio();
        }
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
        System.out.println("Acción inválida: el Mech todavía no se movió en este turno.");
        return this;
    }
}