package edu.fiuba.algo3.modelo.unidades;

import java.util.Optional;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public interface Ataque {
    void ejecutar(Posicion origen, Direccion direccion, Tablero tablero);
    Optional<Casilla> casillaImpactadaDesde(Posicion origen, Direccion direccion, Tablero tablero);
}