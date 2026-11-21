package edu.fiuba.algo3.modelo.partida;

public interface EstadoPartida {
    EstadoPartida terminarFaseJugador(Partida partida);
    void comenzarTurno(Partida partida);
}

