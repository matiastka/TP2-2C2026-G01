package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.partida.Partida;
import edu.fiuba.algo3.vistas.componentes.BotonAccionTurno;
import edu.fiuba.algo3.vistas.componentes.IndicadorRedElectrica;
import edu.fiuba.algo3.vistas.componentes.IndicadorTurno;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class VistaPanelSuperior extends HBox {
    public VistaPanelSuperior(Partida partida, BotonAccionTurno terminarTurno) {
        IndicadorTurno indicadorTurno = new IndicadorTurno(partida);
        IndicadorRedElectrica indicadorRedElectrica = new IndicadorRedElectrica(partida);
        setPadding(new Insets(10));
        setSpacing(12);
        setStyle("-fx-background-color: #37474f;");
        HBox.setHgrow(terminarTurno, Priority.ALWAYS);
        getChildren().addAll(indicadorTurno, indicadorRedElectrica, terminarTurno);
    }
}
