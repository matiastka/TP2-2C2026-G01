package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorDeshacerMovimiento;
import edu.fiuba.algo3.controllers.ControladorPartida;
import edu.fiuba.algo3.modelo.Observador;
import edu.fiuba.algo3.modelo.partida.Partida;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.vistas.seleccion.SeleccionJugador;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VistaPanelLateral extends VBox implements Observador {
    private final Partida partida;
    private final Label casilla;
    private final Label unidad;
    private final Label terreno;
    private final Label vida;
    private final Label salud;
    private final Label estadoArma;
    private final Label ataque;
    private final VistaBarraEstado barraEstado;
    private EstadoSeleccionCasilla estadoCasilla = new SinCasillaSeleccionada();
    private final Button deshacerMovimiento;
    private final Button terminarTurno;

    public VistaPanelLateral(
            Partida partida,
            VistaBarraEstado barraEstado,
            ControladorPartida controladorPartida
    ) {
        this.partida = partida;
        this.partida.agregarObservador(this);
        this.barraEstado = barraEstado;

        this.casilla = crearEtiqueta();
        this.unidad = crearEtiqueta();
        this.terreno = crearEtiqueta();
        this.vida = crearEtiqueta();
        this.salud = crearEtiqueta();
        this.estadoArma = crearEtiqueta();
        this.ataque = crearEtiqueta();

        this.deshacerMovimiento = new Button("Deshacer movimiento");
        this.deshacerMovimiento.setMaxWidth(Double.MAX_VALUE);

        this.terminarTurno = new Button("Terminar turno");
        this.terminarTurno.setMaxWidth(Double.MAX_VALUE);
        this.terminarTurno.setOnAction(controladorPartida);

        setPadding(new Insets(12));
        setSpacing(8);
        setPrefWidth(210);
        setStyle("-fx-background-color: #37474f;");

        getChildren().addAll(
                this.casilla,
                this.terreno,
                this.unidad,
                this.vida,
                this.salud,
                this.estadoArma,
                this.ataque,
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

    private Label crearEtiqueta() {
        Label etiqueta = new Label();
        etiqueta.setStyle("-fx-text-fill: white;");
        etiqueta.setWrapText(true);
        return etiqueta;
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
        this.casilla.setText("Casilla: ninguna");
        this.terreno.setText("Terreno: Sin seleccionar");
        this.unidad.setText("Unidad: Ninguna");
        this.vida.setText("Vida: -");
        this.salud.setText("Estado: -");
        this.estadoArma.setText("Arma: -");
        this.ataque.setText("Ataque: -");
    }

    void mostrarDetalles(Casilla casillaSeleccionada) {
        this.casilla.setText("Casilla: " + casillaSeleccionada.obtenerPosicion());
        this.terreno.setText("Terreno: -");
        this.unidad.setText("Unidad: Ninguna");
        this.vida.setText("Vida: -");
        this.salud.setText("Estado: -");
        this.estadoArma.setText("Arma: -");
        this.ataque.setText("Ataque: -");
        casillaSeleccionada.aceptar(new VisitanteDetalleCasilla(this));
    }

    void mostrarTerreno(String nombre) {
        this.terreno.setText("Terreno: " + nombre);
    }

    void mostrarMech(Mech mech, String nombre) {
        mostrarUnidad(mech, nombre);
        this.estadoArma.setText("Arma: " + mech.obtenerEstadoArma());
    }

    void mostrarVek(Vek vek, String nombre) {
        mostrarUnidad(vek, nombre);
        this.estadoArma.setText("Arma: -");
        this.ataque.setText("Ataque: " + vek.obtenerAtaqueAnunciado());
    }

    void mostrarEmergencia() {
        this.unidad.setText(this.unidad.getText() + " | Emergencia");
    }

    private void mostrarUnidad(Unidad unidadSeleccionada, String nombre) {
        this.unidad.setText("Unidad: " + nombre);
        this.vida.setText("Vida: " + unidadSeleccionada.obtenerVida());
        String estadoSalud = "Sano";
        if (unidadSeleccionada.estaContaminado()) {
            estadoSalud = "Contaminado";
        }
        this.salud.setText("Estado: " + estadoSalud);
    }
}
