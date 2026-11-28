package edu.fiuba.algo3.modelo.partida.estado;

import edu.fiuba.algo3.modelo.partida.Partida;

public interface EstadoPartida {
    EstadoPartida terminarFaseJugador(Partida partida);
    void comenzarTurno(Partida partida);
    boolean estaTerminada();
    boolean ganoElJugador();
}

