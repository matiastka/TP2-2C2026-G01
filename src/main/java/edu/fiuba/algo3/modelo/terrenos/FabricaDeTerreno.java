package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;

public abstract class FabricaDeTerreno {
    public abstract Terreno crear();

    public Casilla crearCasilla(Posicion posicion) {
        return new Casilla(crear(), posicion);
    }


}
