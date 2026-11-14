package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class SinUnidad extends Unidad{
    public SinUnidad(Posicion posicion) {
        super(0, posicion);
        this.rango = 0;
    }

    @Override
    public boolean ocupaCasilla() {
        return false;
    }

    @Override public void recibirDanio(int danio) {
        return;
    }   // no hay a quién dañar

    @Override public void moverA(Posicion d, Tablero t) {
        return;
    }
}
