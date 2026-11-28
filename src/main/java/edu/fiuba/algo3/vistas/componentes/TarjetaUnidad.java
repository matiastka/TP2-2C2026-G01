package edu.fiuba.algo3.vistas.componentes;

import java.util.Optional;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.ReceptorDeOcupante;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TarjetaUnidad extends VBox implements ReceptorDeOcupante {
    private final Label casilla;
    private final Label unidad;
    private final Label terreno;
    private final Label vida;
    private final Label salud;
    private final Label estadoArma;
    private final Label ataque;

    public TarjetaUnidad() {
        this.casilla = crearEtiqueta();
        this.unidad = crearEtiqueta();
        this.terreno = crearEtiqueta();
        this.vida = crearEtiqueta();
        this.salud = crearEtiqueta();
        this.estadoArma = crearEtiqueta();
        this.ataque = crearEtiqueta();
        setSpacing(6);
        setStyle("-fx-background-color: #455a64; -fx-background-radius: 6; -fx-padding: 8;");
        getChildren().addAll(
                this.casilla,
                this.terreno,
                this.unidad,
                this.vida,
                this.salud,
                this.estadoArma,
                this.ataque
        );
        mostrarSinSeleccion();
    }

    public void mostrarSinSeleccion() {
        this.casilla.setText("Casilla: ninguna");
        this.terreno.setText("Terreno: Sin seleccionar");
        this.unidad.setText("Unidad: Ninguna");
        this.vida.setText("Vida: -");
        this.salud.setText("Estado: -");
        this.estadoArma.setText("Arma: -");
        this.ataque.setText("Ataque: -");
    }

    public void mostrarDetalles(Casilla casillaSeleccionada) {
        this.casilla.setText("Casilla: " + casillaSeleccionada.obtenerPosicion());
        this.terreno.setText("Terreno: " + casillaSeleccionada.obtenerTerreno().obtenerNombre());
        this.unidad.setText("Unidad: Ninguna");
        this.vida.setText("Vida: -");
        this.salud.setText("Estado: -");
        this.estadoArma.setText("Arma: -");
        this.ataque.setText("Ataque: -");

        casillaSeleccionada.obtenerOcupante().entregarseA(this);

        if (casillaSeleccionada.obtenerEmergencia().estaActiva()) {
            this.unidad.setText(this.unidad.getText() + " | Emergencia");
        }
    }

    private Label crearEtiqueta() {
        Label etiqueta = new Label();
        etiqueta.setStyle("-fx-text-fill: white;");
        etiqueta.setWrapText(true);
        return etiqueta;
    }

    @Override
    public void recibirMech(Mech mech) {
        mostrarUnidad("Mech", mech);
        this.estadoArma.setText("Arma: " + mech.obtenerEstadoArma());
    }

    @Override
    public void recibirVek(Vek vek) {
        mostrarUnidad("Vek", vek);
        this.ataque.setText("Ataque: " + vek.obtenerAtaqueAnunciado());
    }

    @Override
    public void recibirSinOcupante() {
    }

    private void mostrarUnidad(String tipo, Unidad unidadSeleccionada) {
        this.unidad.setText("Unidad: " + tipo + " " + unidadSeleccionada.obtenerNombre());
        this.vida.setText("Vida: " + unidadSeleccionada.obtenerVida());
        String estadoSalud = "Sano";
        if (unidadSeleccionada.estaContaminado()) {
            estadoSalud = "Contaminado";
        }
        this.salud.setText("Estado: " + estadoSalud);
    }
}
