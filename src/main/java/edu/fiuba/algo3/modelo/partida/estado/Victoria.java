package edu.fiuba.algo3.modelo.partida.estado;

import edu.fiuba.algo3.modelo.partida.Partida;

public class Victoria implements EstadoPartida {
    @Override
    public EstadoPartida terminarFaseJugador(Partida partida) {
        return this;
    }

    @Override
    public void comenzarTurno(Partida partida) {
    }

    @Override
    public boolean estaTerminada() {
        return true;
    }

    @Override
    public boolean ganoElJugador() {
        return true;
    }
}