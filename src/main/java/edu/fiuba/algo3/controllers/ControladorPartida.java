package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.modelo.partida.Partida;
import edu.fiuba.algo3.vistas.VistaBarraEstado;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControladorPartida implements EventHandler<ActionEvent> {
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
        }
    }

    @Override
    public void handle(ActionEvent evento) {
        terminarFaseJugador();
    }
}
