package edu.fiuba.algo3.modelo.partida.estado;

import edu.fiuba.algo3.modelo.partida.Partida;

public class EnJuego implements EstadoPartida {
    @Override
    public EstadoPartida terminarFaseJugador(Partida partida) {
        return partida.jugarRestoDelTurno();
    }

    @Override
    public void comenzarTurno(Partida partida) {
        partida.comenzarSiguienteTurno();
    }

    @Override
    public boolean estaTerminada() {
        return false;
    }

    @Override
    public boolean ganoElJugador() {
        return false;
    }
}

