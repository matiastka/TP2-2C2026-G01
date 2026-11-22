package edu.fiuba.algo3.vistas;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class VistaCartelFinPartida extends StackPane {
    private final Label titulo;
    private final Label subtitulo;
    private final VBox tarjeta;
    private final Label resumen;

    public VistaCartelFinPartida() {
        this.titulo = new Label();
        this.subtitulo = new Label();
        this.tarjeta = new VBox(12);
        this.resumen = new Label();
        this.resumen.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: white;");

        this.titulo.setStyle("-fx-font-size: 34; -fx-font-weight: bold;");
        this.subtitulo.setWrapText(true);
        this.subtitulo.setMaxWidth(360);
        this.subtitulo.setStyle("-fx-font-size: 16; -fx-alignment: center;");
        this.tarjeta.setAlignment(Pos.CENTER);
        this.tarjeta.setMaxSize(420, 220);
        this.tarjeta.setStyle("-fx-background-radius: 12; -fx-border-radius: 12;");

        this.tarjeta.getChildren().addAll(this.titulo, this.subtitulo, this.resumen);
        getChildren().add(this.tarjeta);
        setAlignment(Pos.CENTER);
        setVisible(false);
        setManaged(false);
    }

    public void mostrarVictoria(int contadorVeksEliminados, int contadorRedElectrica) {
        this.titulo.setText("¡VICTORIA!");
        this.subtitulo.setText("Has defendido la Red Eléctrica durante todos los turnos.");
        this.titulo.setStyle("-fx-font-size: 34; -fx-font-weight: bold; -fx-text-fill: #fff59d;");
        this.subtitulo.setStyle("-fx-font-size: 16; -fx-text-fill: #c8e6c9; -fx-alignment: center;");
        this.tarjeta.setStyle("-fx-background-color: rgba(46,125,50,0.95);"
                + "-fx-background-radius: 12;"
                + "-fx-border-color: #ffd54f;"
                + "-fx-border-width: 4;"
                + "-fx-border-radius: 12;");
        mostrarResumen(contadorVeksEliminados, contadorRedElectrica);
        mostrar();
    }

    public void mostrarDerrota(int contadorVeksEliminados, int contadorRedElectrica) {
        this.titulo.setText("DERROTA");
        this.subtitulo.setText("La Red Eléctrica fue destruida o todos los Mechs cayeron.");
        this.titulo.setStyle("-fx-font-size: 34; -fx-font-weight: bold; -fx-text-fill: #ffccbc;");
        this.subtitulo.setStyle("-fx-font-size: 16; -fx-text-fill: #ffcdd2; -fx-alignment: center;");
        this.tarjeta.setStyle("-fx-background-color: rgba(183,28,28,0.95);"
                + "-fx-background-radius: 12;"
                + "-fx-border-color: #ef5350;"
                + "-fx-border-width: 4;"
                + "-fx-border-radius: 12;");
        mostrarResumen(contadorVeksEliminados, contadorRedElectrica);
        mostrar();
    }

    public void ocultar() {
        setVisible(false);
        setManaged(false);
    }

    private void mostrar() {
        setManaged(true);
        setVisible(true);
        setStyle("-fx-background-color: rgba(15,23,42,0.68);");
    }

    private void mostrarResumen(int veksEliminados, int redElectrica) {
        this.resumen.setText("Vek eliminados: " + veksEliminados
                + "   |   Red Eléctrica final: " + redElectrica);
    }

}
