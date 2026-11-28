package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.vistas.VistaBarraEstado;
import edu.fiuba.algo3.vistas.seleccion.SeleccionJugador;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControladorDeshacerMovimiento implements EventHandler<ActionEvent> {
    private final Tablero tablero;
    private final VistaBarraEstado barraEstado;
    private final SeleccionJugador seleccion;

    public ControladorDeshacerMovimiento(
            Tablero tablero,
            VistaBarraEstado barraEstado,
            SeleccionJugador seleccion
    ) {
        this.tablero = tablero;
        this.barraEstado = barraEstado;
        this.seleccion = seleccion;
    }

    public void deshacer() {
        try {
            this.seleccion.ejecutarSobreMech(new AccionDeshacerMovimiento(
                    this.tablero,
                    this.barraEstado
            ));
        } catch (AccionInvalidaException excepcion) {
            this.barraEstado.mostrarError(excepcion.getMessage());
        }
    }

    @Override
    public void handle(ActionEvent evento) {
        deshacer();
    }
}
