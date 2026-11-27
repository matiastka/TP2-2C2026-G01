package edu.fiuba.algo3.modelo.partida.estado;

import edu.fiuba.algo3.modelo.excepciones.PartidaTerminadaException;
import edu.fiuba.algo3.modelo.partida.Partida;

public class Derrota implements EstadoPartida {
    @Override
    public void aceptar(VisitanteEstadoPartida visitante) {
        visitante.visitarDerrota();
    }

    @Override
    public EstadoPartida terminarFaseJugador(Partida partida) {
        throw new PartidaTerminadaException();
    }

    @Override
    public void comenzarTurno(Partida partida) {
    }
}
