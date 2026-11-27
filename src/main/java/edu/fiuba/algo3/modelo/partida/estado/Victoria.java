package edu.fiuba.algo3.modelo.partida.estado;

import edu.fiuba.algo3.modelo.partida.Partida;

public class Victoria implements EstadoPartida {
    @Override
    public void aceptar(VisitanteEstadoPartida visitante) {
        visitante.visitarVictoria();
    }

    @Override
    public EstadoPartida terminarFaseJugador(Partida partida) {
        return this;
    }

    @Override
    public void comenzarTurno(Partida partida) {
    }
}