package edu.fiuba.algo3.modelo.unidades.veks;

import java.util.List;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public interface ReglaPrioridadVek {
    boolean intentarEjecutar(Vek vek, List<Posicion> posicionesAlcanzables, Tablero tablero);
}