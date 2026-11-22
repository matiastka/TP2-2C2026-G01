package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.partida.Partida;
import edu.fiuba.algo3.modelo.partida.PartidaTerminadaException;
import edu.fiuba.algo3.modelo.unidades.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.vistas.VistaBarraEstado;

public class ControladorTerminarTurno {
    private final Partida partida;
    private final VistaBarraEstado barraEstado;

    public ControladorTerminarTurno(Partida partida, VistaBarraEstado barraEstado) {
        this.partida = partida;
        this.barraEstado = barraEstado;
    }

    public void terminar() {
        try {
            this.partida.terminarFaseJugador();
            this.barraEstado.mostrarMensaje("Se resolvio el turno");
        } catch (AccionInvalidaException excepcion) {
            this.barraEstado.mostrarError(excepcion.getMessage());
        } catch (PartidaTerminadaException excepcion) {
            this.barraEstado.mostrarError(excepcion.getMessage());
        }
    }
}
