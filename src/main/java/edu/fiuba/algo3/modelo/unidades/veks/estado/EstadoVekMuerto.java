package edu.fiuba.algo3.modelo.unidades.veks.estado;

import edu.fiuba.algo3.modelo.excepciones.AccionNoDisponibleException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;

public class EstadoVekMuerto implements EstadoTurnoVek {

    @Override
    public EstadoTurnoVek mover(Vek vek, Posicion destino, Tablero tablero) {
        throw new AccionNoDisponibleException();
    }

    @Override
    public EstadoTurnoVek prepararAtaque(Vek vek, Posicion posicion) {
        throw new AccionNoDisponibleException();
    }

    @Override
    public EstadoTurnoVek resolverAtaque(Vek vek, Tablero tablero) {
        return this;
    }
}