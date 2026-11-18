package edu.fiuba.algo3.modelo.unidades.veks;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class InteligenciaArtificialVek {

    private final List<ReglaPrioridadVek> reglas;

    public InteligenciaArtificialVek() {
        this.reglas = List.of(
                new ReglaAtacarEdificio(),
                new ReglaAtacarMech(),
                new ReglaAcercarseAEdificio()
        );
    }

    public void decidirTurno(Vek vek, Tablero tablero) {
        List<Posicion> posicionesValidas = obtenerPosicionesAlcanzablesYSeguras(vek, tablero);
        for (ReglaPrioridadVek regla : reglas) {
            if (regla.intentarEjecutar(vek, posicionesValidas, tablero)) {
                return;
            }
        }
    }

    private List<Posicion> obtenerPosicionesAlcanzablesYSeguras(Vek vek, Tablero tablero) {
        List<Posicion> candidatas = new ArrayList<>();
        for (int fila = 0; fila < tablero.cantidadFilas(); fila++) {
            for (int col = 0; col < tablero.cantidadColumnas(); col++) {
                Posicion pos = new Posicion(fila, col);
                if (vek.puedeMoverseYTerminarEn(pos, tablero)) {
                    candidatas.add(pos);
                }
            }
        }
        return candidatas;
    }
}