package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.MovimientoTerrestre;

public class Luciernaga extends Vek {
    public Luciernaga(Posicion posicion){
        super(3, posicion, new MovimientoTerrestre());
        this.rango = 2;
    }
}
