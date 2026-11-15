package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.unidades.Movimiento;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Vek extends Unidad {
    protected Vek(int vida, int rango, Movimiento movimiento){
        super(vida, rango, movimiento);
    }

    @Override
    protected void hundirse(){
        recibirDanio(obtenerVida());
    }
}
