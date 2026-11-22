package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Observador;
import edu.fiuba.algo3.modelo.partida.FaseTurno;
import edu.fiuba.algo3.modelo.partida.Partida;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class VistaPanelSuperior extends HBox implements Observador {
    private final Partida partida;
    private final Label turno;
    private final Label fase;
    private final Label energia;

    public VistaPanelSuperior(Partida partida, Button terminarTurno) {
        this.partida = partida;
        this.partida.agregarObservador(this);

        this.turno = new Label();
        this.fase = new Label();
        this.energia = new Label();

        setPadding(new Insets(10));
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(20);
        setStyle("-fx-background-color: #37474f;");

        for (Label label : new Label[]{this.turno, this.fase, this.energia}) {
            label.setStyle("-fx-text-fill: white; -fx-font-size: 15;");
        }

        terminarTurno.setStyle("-fx-font-size: 14;");
        HBox.setHgrow(terminarTurno, Priority.ALWAYS);
        getChildren().addAll(this.turno, this.fase, this.energia, terminarTurno);
        actualizar();
    }

    @Override
    public void actualizar() {
        this.turno.setText("Turno " + this.partida.obtenerNumeroDeTurno() + " / 5");
        this.energia.setText("Red Electrica: " + this.partida.obtenerTablero().obtenerRedElectrica());

        if (this.partida.obtenerFaseActual() == FaseTurno.JUGADOR) {
            this.fase.setText("Fase del Jugador");
        } else if (this.partida.obtenerFaseActual() == FaseTurno.VEKS) {
            this.fase.setText("Fase Vek");
        } else if (this.partida.obtenerFaseActual() == FaseTurno.RESOLUCION) {
            this.fase.setText("Fase de Resolucion");
        } else {
            this.fase.setText("Fase de Emergencia");
        }
    }
}
