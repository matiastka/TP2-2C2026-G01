package edu.fiuba.algo3.vistas;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class VistaBarraEstado extends HBox {
    private final Label mensaje;

    public VistaBarraEstado() {
        this.mensaje = new Label("Fase del Jugador");
        this.mensaje.setStyle("-fx-text-fill: white;");
        setPadding(new Insets(10));
        setStyle("-fx-background-color: #263238;");
        getChildren().add(this.mensaje);
    }

    public void mostrarMensaje(String texto) {
        this.mensaje.setStyle("-fx-text-fill: #a5d6a7;");
        this.mensaje.setText(texto);
    }

    public void mostrarError(String texto) {
        this.mensaje.setStyle("-fx-text-fill: #ef9a9a;");
        this.mensaje.setText(texto);
    }
}
