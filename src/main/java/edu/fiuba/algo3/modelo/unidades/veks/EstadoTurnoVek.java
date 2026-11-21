package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public interface EstadoTurnoVek {
    EstadoTurnoVek mover(Vek vek, Posicion destino, Tablero tablero);
    EstadoTurnoVek prepararAtaque(Vek vek, Posicion objetivo);
    EstadoTurnoVek resolverAtaque(Vek vek, Tablero tablero);
}