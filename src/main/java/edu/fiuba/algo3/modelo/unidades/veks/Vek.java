package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.Movimiento;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Vek extends Unidad {
    protected Vek(int vida, Posicion posicion, Movimiento movimiento){
        super(vida, posicion, movimiento);
    }

    @Override
    public void hundirse(){
        recibirDanio(obtenerVida());
    }
}
