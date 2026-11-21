package edu.fiuba.algo3.modelo.unidades.ataques;

import java.util.Optional;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public interface Ataque {
    void ejecutar(Posicion origen, Direccion direccion, Tablero tablero);
    Optional<Casilla> casillaImpactadaDesde(Posicion origen, Direccion direccion, Tablero tablero);
}