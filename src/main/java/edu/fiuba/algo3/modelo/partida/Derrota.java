package edu.fiuba.algo3.modelo.partida;

public class Derrota implements EstadoPartida {

    @Override
    public EstadoPartida terminarFaseJugador(Partida partida) {
        throw new PartidaTerminadaException();
    }

    @Override
    public void comenzarTurno(Partida partida) {
    }
}
