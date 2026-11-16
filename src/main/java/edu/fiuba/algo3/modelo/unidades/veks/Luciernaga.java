package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.unidades.AtaqueLineaRecta;
import edu.fiuba.algo3.modelo.unidades.EfectoContaminar;
import edu.fiuba.algo3.modelo.unidades.MovimientoTerrestre;

public class Luciernaga extends Vek {
    public Luciernaga() {
        super(3, 2, new MovimientoTerrestre(), new AtaqueLineaRecta(1, new EfectoContaminar()));
    }
}
