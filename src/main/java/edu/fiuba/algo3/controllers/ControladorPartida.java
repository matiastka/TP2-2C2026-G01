package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.partida.Partida;
import edu.fiuba.algo3.modelo.partida.PartidaTerminadaException;
import edu.fiuba.algo3.modelo.unidades.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.vistas.VistaBarraEstado;

public class ControladorPartida {
    private final Partida partida;
    private final VistaBarraEstado barraEstado;

    public ControladorPartida(Partida partida, VistaBarraEstado barraEstado) {
        this.partida = partida;
        this.barraEstado = barraEstado;
    }

    public void terminarFaseJugador() {
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
