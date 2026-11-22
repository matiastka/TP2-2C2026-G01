package edu.fiuba.algo3.vistas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.fiuba.algo3.modelo.Observador;
import edu.fiuba.algo3.modelo.partida.Partida;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import javafx.scene.layout.GridPane;
import java.util.function.Consumer;

public class VistaTablero extends GridPane implements Observador {
    private final Partida partida;
    private final Map<Posicion, VistaCasilla> casillas;
    private Consumer<Casilla> seleccionListener;

    public VistaTablero(Partida partida) {
        this.partida = partida;
        this.partida.agregarObservador(this);
        this.casillas = new HashMap<>();
        setHgap(3);
        setVgap(3);
        construir();
    }

    private void construir() {
        getChildren().clear();
        this.casillas.clear();
        Tablero tablero = this.partida.obtenerTablero();

        for (int fila = 0; fila < tablero.cantidadFilas(); fila++) {
            for (int columna = 0; columna < tablero.cantidadColumnas(); columna++) {
                Posicion posicion = new Posicion(fila, columna);
                VistaCasilla vistaCasilla = new VistaCasilla(tablero.enCasilla(posicion));
                vistaCasilla.agregarListenerSeleccion(this::seleccionar);
                this.casillas.put(posicion, vistaCasilla);
                add(vistaCasilla, columna, fila);
            }
        }
        refrescarAmenazas();
    }

    public void agregarListenerSeleccion(Consumer<Casilla> listener) {
        this.seleccionListener = listener;
    }

    private void seleccionar(Casilla casilla) {
        if (this.seleccionListener != null) {
            this.seleccionListener.accept(casilla);
        }
    }

    public void refrescarAmenazas() {
        limpiarResaltados();
        Tablero tablero = this.partida.obtenerTablero();
        for (Vek vek : this.partida.obtenerVeks()) {
            for (Posicion posicion : vek.obtenerPosicionesAmenazadas(tablero)) {
                VistaCasilla casilla = this.casillas.get(posicion);
                if (casilla != null) {
                    casilla.resaltarAmenaza(true);
                }
            }
        }
    }

    public void resaltarDestinos(List<Posicion> posiciones) {
        limpiarResaltados();
        for (Posicion posicion : posiciones) {
            VistaCasilla casilla = this.casillas.get(posicion);
            if (casilla != null) {
                casilla.resaltarDestino(true);
            }
        }
    }

    public void resaltarObjetivos(List<Posicion> posiciones) {
        limpiarResaltados();
        for (Posicion posicion : posiciones) {
            VistaCasilla casilla = this.casillas.get(posicion);
            if (casilla != null) {
                casilla.resaltarObjetivo(true);
            }
        }
    }

    public void resaltarDestinosYObjetivos(List<Posicion> destinos, List<Posicion> objetivos) {
        limpiarResaltados();
        for (Posicion posicion : destinos) {
            VistaCasilla casilla = this.casillas.get(posicion);
            if (casilla != null) {
                casilla.resaltarDestino(true);
            }
        }
        for (Posicion posicion : objetivos) {
            VistaCasilla casilla = this.casillas.get(posicion);
            if (casilla != null) {
                casilla.resaltarObjetivo(true);
            }
        }
    }

    public void limpiarResaltados() {
        for (VistaCasilla casilla : this.casillas.values()) {
            casilla.limpiarResaltados();
        }
    }

    @Override
    public void actualizar() {
        construir();
    }
}
