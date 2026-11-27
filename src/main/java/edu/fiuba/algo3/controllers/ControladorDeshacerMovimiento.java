package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.vistas.VistaBarraEstado;

public class ControladorDeshacerMovimiento {
    private final Tablero tablero;
    private final VistaBarraEstado barraEstado;

    public ControladorDeshacerMovimiento(Tablero tablero, VistaBarraEstado barraEstado) {
        this.tablero = tablero;
        this.barraEstado = barraEstado;
    }

    public void deshacer(Mech mechSeleccionado) {
        if (mechSeleccionado == null) {
            this.barraEstado.mostrarError("No hay un Mech seleccionado");
            return;
        }

        try {
            mechSeleccionado.deshacerMovimiento(this.tablero);
            this.barraEstado.mostrarMensaje("Movimiento deshecho");
        } catch (AccionInvalidaException excepcion) {
            this.barraEstado.mostrarError(excepcion.getMessage());
        }
    }
}
