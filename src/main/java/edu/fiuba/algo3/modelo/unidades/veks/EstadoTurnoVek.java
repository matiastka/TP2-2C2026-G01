package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.ResultadoAccion;

public interface EstadoTurnoVek {
    ResultadoAccion<EstadoTurnoVek> mover(Vek vek, Posicion destino, Tablero tablero);
    ResultadoAccion<EstadoTurnoVek> prepararAtaque(Vek vek, Direccion direccion);
    ResultadoAccion<EstadoTurnoVek> resolverAtaque(Vek vek, Tablero tablero);
}