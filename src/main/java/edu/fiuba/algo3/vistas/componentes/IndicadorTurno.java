package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.modelo.Observador;
import edu.fiuba.algo3.modelo.partida.Partida;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class IndicadorTurno extends HBox implements Observador {
    private final Partida partida;
    private final Label turno;
    private final Label fase;

    public IndicadorTurno(Partida partida) {
        this.partida = partida;
        this.turno = new Label();
        this.fase = new Label();
        this.turno.setStyle("-fx-text-fill: white; -fx-font-size: 15;");
        this.fase.setStyle("-fx-text-fill: white; -fx-font-size: 15;");
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(16);
        getChildren().addAll(this.turno, this.fase);
        this.partida.agregarObservador(this);
        actualizar();
    }

    @Override
    public void actualizar() {
        this.turno.setText("Turno " + this.partida.obtenerNumeroDeTurno() + " / 5");
        this.fase.setText("Fase del Jugador");
    }
}
