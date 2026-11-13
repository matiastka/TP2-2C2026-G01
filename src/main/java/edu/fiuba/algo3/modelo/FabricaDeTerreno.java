package edu.fiuba.algo3.modelo;

public abstract class FabricaDeTerreno {
    public abstract Terreno crear();

    public Casilla crearCasilla(Posicion posicion) {
        return new Casilla(crear(), posicion);
    }


}
