package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.unidades.AtaqueCuerpoACuerpo;
import edu.fiuba.algo3.modelo.unidades.EfectoNulo;
import edu.fiuba.algo3.modelo.unidades.MovimientoVolador;

public class Avispon extends Vek {
    public Avispon() {
        super(2, 4, new MovimientoVolador(), new AtaqueCuerpoACuerpo(1, new EfectoNulo()));
    }

    @Override
    public void contaminarse() {
        // El Avispón es volador y no se contamina
    }
}