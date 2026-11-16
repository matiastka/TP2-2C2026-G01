package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.unidades.AtaqueCuerpoACuerpo;
import edu.fiuba.algo3.modelo.unidades.EfectoNulo;
import edu.fiuba.algo3.modelo.unidades.MovimientoTerrestre;

public class Saltador extends Vek {
    public Saltador() {
        super(2, 3, new MovimientoTerrestre(), new AtaqueCuerpoACuerpo(2, new EfectoNulo()));
    }
}