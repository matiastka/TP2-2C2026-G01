package edu.fiuba.algo3.vistas;

import java.util.function.Consumer;
import java.util.Optional;

import edu.fiuba.algo3.modelo.Observador;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class VistaCasilla extends StackPane implements Observador {
    private final Casilla casilla;
    private Ocupante ocupanteAnterior;
    private final Label identificador;
    private final Label vida;
    private final Label emergencia;
    private String estiloTerreno;
    private boolean amenazada;
    private boolean destinoValido;
    private boolean objetivoValido;
    private boolean emergenciaActiva;
    private Consumer<Casilla> seleccionListener = new SinListenerSeleccionCasilla();

    public VistaCasilla(Casilla casilla) {
        this.casilla = casilla;
        this.casilla.agregarObservador(this);
        this.ocupanteAnterior = this.casilla.obtenerOcupante();
        this.ocupanteAnterior.agregarObservador(this);
        this.identificador = new Label("");
        this.vida = new Label("");
        this.emergencia = new Label("");

        this.identificador.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: white;");
        this.vida.setStyle("-fx-font-size: 11; -fx-text-fill: white;");
        this.emergencia.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: #ff5252;");

        StackPane.setAlignment(this.vida, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(this.emergencia, Pos.TOP_LEFT);
        setPrefSize(72, 72);
        getChildren().addAll(this.identificador, this.vida, this.emergencia);

        actualizar();
        setOnMouseClicked(new ManejadorClickCasilla(this));
    }

    public void agregarListenerSeleccion(Consumer<Casilla> listener) {
        this.seleccionListener = listener;
    }

    void seleccionar() {
        this.seleccionListener.accept(this.casilla);
    }

    @Override
    public void actualizar() {
        this.ocupanteAnterior.quitarObservador(this);
        this.ocupanteAnterior = this.casilla.obtenerOcupante();
        this.ocupanteAnterior.agregarObservador(this);

        this.identificador.setText("");
        this.vida.setText("");
        this.emergencia.setText("");
        this.estiloTerreno = obtenerEstiloTerreno(this.casilla.obtenerTerreno().obtenerNombre());
        Optional<Unidad> unidad = this.casilla.obtenerOcupante().obtenerUnidad();
        if (unidad.isPresent()) {
            dibujarUnidad(unidad.get(), obtenerIdentificador(unidad.get().obtenerNombre()));
        }
        this.emergenciaActiva = this.casilla.obtenerEmergencia().estaActiva();
        if (this.emergenciaActiva) {
            this.emergencia.setText("⚠");
        }
        aplicarEstilo();
    }

    public void resaltarAmenaza(boolean resaltar) {
        this.amenazada = resaltar;
        aplicarEstilo();
    }

    public void resaltarDestino(boolean resaltar) {
        this.destinoValido = resaltar;
        aplicarEstilo();
    }

    public void resaltarObjetivo(boolean resaltar) {
        this.objetivoValido = resaltar;
        aplicarEstilo();
    }

    public void limpiarResaltados() {
        this.amenazada = false;
        this.destinoValido = false;
        this.objetivoValido = false;
        aplicarEstilo();
    }

    private void aplicarEstilo() {
        String borde = "-fx-border-color: #263238; -fx-border-width: 2;";
        if (this.amenazada) {
            borde = "-fx-border-color: #ff5252; -fx-border-width: 4;";
        } else if (this.destinoValido) {
            borde = "-fx-border-color: #80deea; -fx-border-width: 4;";
        } else if (this.objetivoValido) {
            borde = "-fx-border-color: #ffeb3b; -fx-border-width: 4;";
        }
        if (this.emergenciaActiva) {
            borde = "-fx-border-color: #ff9800; -fx-border-width: 5;";
        }
        setStyle(this.estiloTerreno + borde);
    }

    private void dibujarUnidad(Unidad unidad, String identificador) {
        this.identificador.setText(identificador);
        this.vida.setText(String.valueOf(unidad.obtenerVida()));
        if (unidad.estaContaminado()) {
            this.identificador.setText(identificador + "*");
        }
    }

    private String obtenerEstiloTerreno(String nombre) {
        switch (nombre) {
            case "Suelo":
                return "-fx-background-color: #81c784;";
            case "Agua":
                return "-fx-background-color: #64b5f6;";
            case "Acido":
                return "-fx-background-color: #aed581;";
            case "Edificio":
                return "-fx-background-color: #b0bec5;";
            case "Montana":
                return "-fx-background-color: #a1887f;";
            default:
                throw new IllegalArgumentException("Terreno no soportado: " + nombre);
        }
    }

    private String obtenerIdentificador(String nombre) {
        switch (nombre) {
            case "Combate":
                return "C";
            case "Canon":
                return "A";
            case "Artilleria":
                return "R";
            case "Luciernaga":
                return "L";
            case "Saltador":
                return "S";
            case "Avispon":
                return "V";
            default:
                throw new IllegalArgumentException("Unidad no soportada: " + nombre);
        }
    }
}
