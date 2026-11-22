package edu.fiuba.algo3.modelo.partida;

public class EnJuego implements EstadoPartida {
    @Override
    public void aceptar(VisitanteEstadoPartida visitante) {
        visitante.visitarEnJuego();
    }

    @Override
    public EstadoPartida terminarFaseJugador(Partida partida) {
        return partida.jugarRestoDelTurno();
    }

    @Override
    public void comenzarTurno(Partida partida) {
        partida.comenzarSiguienteTurno();
    }
}

