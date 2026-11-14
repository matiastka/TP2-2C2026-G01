package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.MovimientoVolador;

public class Avispon extends Vek {
    public Avispon(Posicion posicion){
        super(2, posicion, new MovimientoVolador());
        this.rango = 4;
    }

}
