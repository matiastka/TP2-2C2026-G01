package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.vistas.VistaBarraEstado;
import edu.fiuba.algo3.vistas.seleccion.AccionSobreMech;

class AccionDeshacerMovimiento implements AccionSobreMech {
    private final Tablero tablero;
    private final VistaBarraEstado barraEstado;

    AccionDeshacerMovimiento(Tablero tablero, VistaBarraEstado barraEstado) {
        this.tablero = tablero;
        this.barraEstado = barraEstado;
    }

    @Override
    public void ejecutar(Mech mech) {
        mech.deshacerMovimiento(this.tablero);
        this.barraEstado.mostrarMensaje("Movimiento deshecho");
    }

    @Override
    public void ejecutarSinMechSeleccionado() {
        this.barraEstado.mostrarError("No hay un Mech seleccionado");
    }
}
