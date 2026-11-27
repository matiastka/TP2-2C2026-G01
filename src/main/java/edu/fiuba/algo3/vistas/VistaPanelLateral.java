package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorDeshacerMovimiento;
import edu.fiuba.algo3.controllers.ControladorTerminarTurno;
import edu.fiuba.algo3.modelo.Observador;
import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;
import edu.fiuba.algo3.modelo.partida.Partida;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.emergencias.EmergenciaVek;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Artilleria;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Canon;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Avispon;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Saltador;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VistaPanelLateral extends VBox implements Observador {
    private final Partida partida;
    private final VistaBarraEstado barraEstado;
    private final Label casilla;
    private final Label unidad;
    private final Label terreno;
    private final Label vida;
    private final Label salud;
    private final Label estadoArma;
    private final Label ataque;
    private Mech mechSeleccionado;
    private Casilla casillaSeleccionada;
    private final Button deshacerMovimiento;
    private final Button terminarTurno;

    public VistaPanelLateral(Partida partida, VistaBarraEstado barraEstado) {
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

        ControladorDeshacerMovimiento deshacer = new ControladorDeshacerMovimiento(
                partida.obtenerTablero(),
                barraEstado
        );
        ControladorTerminarTurno terminar = new ControladorTerminarTurno(partida, barraEstado);

        this.deshacerMovimiento = new Button("Deshacer movimiento");
        this.deshacerMovimiento.setMaxWidth(Double.MAX_VALUE);
        this.deshacerMovimiento.setOnAction(evento -> deshacer.deshacer(this.mechSeleccionado));

        this.terminarTurno = new Button("Terminar turno");
        this.terminarTurno.setMaxWidth(Double.MAX_VALUE);
        this.terminarTurno.setOnAction(evento -> terminar.terminar());

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
        seleccionar(null);
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
        this.casillaSeleccionada = casillaSeleccionada;
        this.mechSeleccionado = null;
        this.casilla.setText("Casilla: ninguna");
        this.terreno.setText("Terreno: Sin seleccionar");
        this.unidad.setText("Unidad: Ninguna");
        this.vida.setText("Vida: -");
        this.salud.setText("Estado: -");
        this.estadoArma.setText("Arma: -");
        this.ataque.setText("Ataque: -");

        if (casillaSeleccionada == null) {
            return;
        }

        this.casilla.setText("Casilla: " + casillaSeleccionada.obtenerPosicion());
        casillaSeleccionada.aceptar(new VisitanteDetalle());
    }

    @Override
    public void actualizar() {
        if (this.casillaSeleccionada != null) {
            seleccionar(this.casillaSeleccionada);
        }
    }

    private void mostrarUnidad(Unidad unidad, String nombre) {
        this.unidad.setText("Unidad: " + nombre);
        this.vida.setText("Vida: " + unidad.obtenerVida());
        this.salud.setText("Estado: " + (unidad.estaContaminado() ? "Contaminado" : "Sano"));
    }

    private class VisitanteDetalle implements VisitanteVisualCasilla {
        @Override
        public void visitarSuelo() {
            terreno.setText("Terreno: Suelo");
        }

        @Override
        public void visitarAgua() {
            terreno.setText("Terreno: Agua");
        }

        @Override
        public void visitarAcido() {
            terreno.setText("Terreno: Acido");
        }

        @Override
        public void visitarEdificio() {
            terreno.setText("Terreno: Edificio");
        }

        @Override
        public void visitarMontana() {
            terreno.setText("Terreno: Montana");
        }

        @Override
        public void visitarSinOcupante() {
        }

        @Override
        public void visitarCombate(Combate combate) {
            seleccionarMech(combate, "Mech Combate");
        }

        @Override
        public void visitarCanon(Canon canon) {
            seleccionarMech(canon, "Mech Canon");
        }

        @Override
        public void visitarArtilleria(Artilleria artilleria) {
            seleccionarMech(artilleria, "Mech Artilleria");
        }

        private void seleccionarMech(Mech mech, String nombre) {
            mechSeleccionado = mech;
            mostrarUnidad(mech, nombre);
            estadoArma.setText("Arma: " + mech.obtenerEstadoArma());
        }

        @Override
        public void visitarLuciernaga(Luciernaga luciernaga) {
            seleccionarVek(luciernaga, "Vek Luciernaga");
        }

        @Override
        public void visitarSaltador(Saltador saltador) {
            seleccionarVek(saltador, "Vek Saltador");
        }

        @Override
        public void visitarAvispon(Avispon avispon) {
            seleccionarVek(avispon, "Vek Avispon");
        }

        private void seleccionarVek(Vek vek, String nombre) {
            mostrarUnidad(vek, nombre);
            estadoArma.setText("Arma: -");
            ataque.setText("Ataque: " + vek.obtenerAtaqueAnunciado());
        }

        @Override
        public void visitarSinEmergencia() {
        }

        @Override
        public void visitarEmergenciaVek(EmergenciaVek emergencia) {
            unidad.setText(unidad.getText() + " | Emergencia");
        }
    }
}
