package edu.fiuba.algo3.modelo.unidades.veks.ia;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.modelo.unidades.veks.ia.reglas.ReglaAcercarseAEdificio;
import edu.fiuba.algo3.modelo.unidades.veks.ia.reglas.ReglaAtacarEdificio;
import edu.fiuba.algo3.modelo.unidades.veks.ia.reglas.ReglaAtacarMech;  
import edu.fiuba.algo3.modelo.unidades.veks.ia.reglas.ReglaPrioridadVek;

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
                vek.agregarSiPuedeTerminarEn(pos, candidatas, tablero);
            }
        }
        return candidatas;
    }
}