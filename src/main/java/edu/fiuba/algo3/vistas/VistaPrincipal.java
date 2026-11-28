package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorCasilla;
import edu.fiuba.algo3.controllers.ControladorEstadoPartida;
import edu.fiuba.algo3.modelo.partida.Partida;
import edu.fiuba.algo3.vistas.seleccion.SeleccionJugador;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class VistaPrincipal extends BorderPane {
    private final Partida partida;
    private final VistaTablero tableroVisual;
    private final VistaBarraEstado barraEstado;
    private final VistaPanelLateral panelLateral;
    private final SeleccionJugador seleccion;
    private final VistaCartelFinPartida cartelFinPartida;

    public VistaPrincipal(
            Partida partida,
            VistaPanelSuperior panelSuperior,
            VistaBarraEstado barraEstado,
            VistaPanelLateral panelLateral
    ) {
        this.partida = partida;
        this.tableroVisual = new VistaTablero(partida);
        this.barraEstado = barraEstado;
        this.panelLateral = panelLateral;
        this.seleccion = new SeleccionJugador(
                this.partida,
                this.tableroVisual,
                this.panelLateral,
                this.barraEstado
        );

        ControladorCasilla controladorCasilla = new ControladorCasilla(
                this.partida,
                this.seleccion,
                this.barraEstado
        );
        this.panelLateral.configurarSeleccion(this.seleccion);
        this.tableroVisual.agregarListenerSeleccion(controladorCasilla);
        this.cartelFinPartida = new VistaCartelFinPartida();
        StackPane centro = new StackPane(this.tableroVisual, this.cartelFinPartida);

        this.tableroVisual.setPadding(new Insets(10));

        setTop(panelSuperior);
        setCenter(centro);
        setRight(this.panelLateral);
        setBottom(this.barraEstado);
        setStyle("-fx-background-color: #263238;");

        new ControladorEstadoPartida(this.partida, this);
    }

    public void refrescarAmenazas() {
        this.tableroVisual.refrescarAmenazas();
    }

    public void mostrarEnJuego() {
        this.cartelFinPartida.ocultar();
        this.tableroVisual.setDisable(false);
        this.panelLateral.habilitarAcciones();
        this.barraEstado.mostrarMensaje("La partida continua");
    }

    public void mostrarVictoria(int veksEliminados, int redElectrica) {
        this.cartelFinPartida.mostrarVictoria(veksEliminados, redElectrica);
        this.barraEstado.mostrarMensaje("Victoria");
        finalizarInteraccion();
    }

    public void mostrarDerrota(int veksEliminados, int redElectrica) {
        this.cartelFinPartida.mostrarDerrota(veksEliminados, redElectrica);
        this.barraEstado.mostrarError("Derrota");
        finalizarInteraccion();
    }

    private void finalizarInteraccion() {
        this.seleccion.terminar();
        this.tableroVisual.setDisable(true);
        this.panelLateral.deshabilitarAcciones();
    }
}
