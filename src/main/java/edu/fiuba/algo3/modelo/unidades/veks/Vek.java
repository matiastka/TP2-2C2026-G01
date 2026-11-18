package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Vek extends Unidad {
    protected Vek(int vida, Posicion posicion){
        super(vida, posicion);
    }

    @Override
    public void caerEnAgua(){
        recibirDanio(obtenerVida());
    }
}
