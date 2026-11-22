package edu.fiuba.algo3.modelo.partida;

public class Victoria implements EstadoPartida {
    @Override
    public void aceptar(VisitanteEstadoPartida visitante) {
        visitante.visitarVictoria();
    }

    @Override
    public EstadoPartida terminarFaseJugador(Partida partida) {
        throw new PartidaTerminadaException();
    }

    @Override
    public void comenzarTurno(Partida partida) {
    }
}
