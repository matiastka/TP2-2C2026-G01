package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorDeshacerMovimiento;
import edu.fiuba.algo3.controllers.ControladorPartida;
import edu.fiuba.algo3.modelo.Observador;
import edu.fiuba.algo3.modelo.partida.Partida;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.vistas.seleccion.SeleccionJugador;
import edu.fiuba.algo3.vistas.componentes.BotonAccionTurno;
import edu.fiuba.algo3.vistas.componentes.TarjetaUnidad;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class VistaPanelLateral extends VBox implements Observador {
    private final Partida partida;
    private final VistaBarraEstado barraEstado;
    private final TarjetaUnidad tarjetaUnidad;
    private EstadoSeleccionCasilla estadoCasilla = new SinCasillaSeleccionada();
    private final BotonAccionTurno deshacerMovimiento;
    private final BotonAccionTurno terminarTurno;

    public VistaPanelLateral(
            Partida partida,
            VistaBarraEstado barraEstado,
            ControladorPartida controladorPartida
    ) {
        this.partida = partida;
        this.partida.agregarObservador(this);
        this.barraEstado = barraEstado;

        this.tarjetaUnidad = new TarjetaUnidad();
        this.deshacerMovimiento = new BotonAccionTurno("Deshacer movimiento");
        this.terminarTurno = new BotonAccionTurno("Terminar turno");
        this.terminarTurno.setOnAction(controladorPartida);

        setPadding(new Insets(12));
        setSpacing(8);
        setPrefWidth(210);
        setStyle("-fx-background-color: #37474f;");

        getChildren().addAll(
                this.tarjetaUnidad,
                this.deshacerMovimiento,
                this.terminarTurno
        );
        this.estadoCasilla.actualizar(this);
    }

    public void configurarSeleccion(SeleccionJugador seleccion) {
        ControladorDeshacerMovimiento controladorDeshacer = new ControladorDeshacerMovimiento(
                this.partida.obtenerTablero(),
                this.barraEstado,
                seleccion
        );
        this.deshacerMovimiento.setOnAction(controladorDeshacer);
    }

    public void deshabilitarAcciones() {
        setDisable(true);
    }

    public void habilitarAcciones() {
        setDisable(false);
    }

    public void seleccionar(Casilla casillaSeleccionada) {
        this.estadoCasilla = new CasillaSeleccionada(casillaSeleccionada);
        this.estadoCasilla.actualizar(this);
    }

    @Override
    public void actualizar() {
        this.estadoCasilla.actualizar(this);
    }

    void mostrarSinSeleccion() {
        this.tarjetaUnidad.mostrarSinSeleccion();
    }

    void mostrarDetalles(Casilla casillaSeleccionada) {
        this.tarjetaUnidad.mostrarDetalles(casillaSeleccionada);
    }
}
