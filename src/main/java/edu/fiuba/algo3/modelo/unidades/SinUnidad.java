package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class SinUnidad extends Unidad{
    public SinUnidad(Posicion posicion) {
        super(0, posicion, new MovimientoTerrestre());
        this.rango = 0;
    }

    @Override
    public boolean ocupaCasilla() {
        return false;
    }

    @Override public void recibirDanio(int danio) {
    }

    @Override public void moverA(Posicion d, Tablero t) {
    }

    @Override public void hundirse(){
    }
}
