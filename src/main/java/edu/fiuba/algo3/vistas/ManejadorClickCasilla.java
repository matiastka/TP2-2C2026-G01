package edu.fiuba.algo3.vistas;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

class ManejadorClickCasilla implements EventHandler<MouseEvent> {
    private final VistaCasilla vista;

    ManejadorClickCasilla(VistaCasilla vista) {
        this.vista = vista;
    }

    @Override
    public void handle(MouseEvent evento) {
        this.vista.seleccionar();
    }
}
