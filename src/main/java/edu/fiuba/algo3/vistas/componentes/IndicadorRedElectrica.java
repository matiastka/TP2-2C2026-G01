package edu.fiuba.algo3.vistas.componentes;

import edu.fiuba.algo3.modelo.Observador;
import edu.fiuba.algo3.modelo.partida.Partida;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class IndicadorRedElectrica extends HBox implements Observador {
    private final Partida partida;
    private final Label valor;

    public IndicadorRedElectrica(Partida partida) {
        this.partida = partida;
        this.valor = new Label();
        this.valor.setStyle("-fx-text-fill: white; -fx-font-size: 15;");
        setSpacing(6);
        setStyle("-fx-background-color: #37474f;");
        getChildren().add(this.valor);
        this.partida.agregarObservador(this);
        actualizar();
    }

    @Override
    public void actualizar() {
        this.valor.setText(
                "Red Electrica: " + this.partida.obtenerTablero().obtenerRedElectrica()
        );
    }
}
