package edu.fiuba.algo3.modelo.partida.estado;

import edu.fiuba.algo3.modelo.partida.Partida;

public class Derrota implements EstadoPartida {
    @Override
    public EstadoPartida terminarFaseJugador(Partida partida) {
        return this;
    }

    @Override
    public void comenzarTurno(Partida partida) {
    }

    @Override
    public void informarA(ReceptorDeEstadoPartida receptor) {
        receptor.partidaPerdida();
    }

}