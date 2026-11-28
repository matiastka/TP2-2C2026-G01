package edu.fiuba.algo3.vistas.componentes;

import javafx.scene.control.Button;

public class BotonAccionTurno extends Button {

    public BotonAccionTurno(String texto) {
        super(texto);
        setStyle("-fx-font-size: 14;");
        setMaxWidth(Double.MAX_VALUE);
    }
}
