package edu.fiuba.algo3.modelo.unidades.veks.estado;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;

public interface EstadoTurnoVek {
    EstadoTurnoVek mover(Vek vek, Posicion destino, Tablero tablero);
    EstadoTurnoVek prepararAtaque(Vek vek, Posicion objetivo);
    EstadoTurnoVek resolverAtaque(Vek vek, Tablero tablero);
}