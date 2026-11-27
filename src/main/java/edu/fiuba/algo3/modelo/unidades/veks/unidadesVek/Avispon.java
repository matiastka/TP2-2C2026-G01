package edu.fiuba.algo3.modelo.unidades.veks.unidadesVek;

import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;
import edu.fiuba.algo3.modelo.excepciones.DestinoNoAlcanzableException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.ataques.AtaqueCuerpoACuerpo;
import edu.fiuba.algo3.modelo.unidades.ataques.efectos.EfectoNulo;
import edu.fiuba.algo3.modelo.unidades.movimientos.MovimientoVolador;
import edu.fiuba.algo3.modelo.unidades.veks.*;


public class Avispon extends Vek {
    @Override
    public void aceptar(VisitanteVisualCasilla visitante) {
        visitante.visitarAvispon(this);
    }
    public Avispon() {
        super(2, 4, new MovimientoVolador(), new AtaqueCuerpoACuerpo(1, new EfectoNulo()));
    }

    @Override
    public void contaminarse() {
        // El Avispón es volador y no se contamina
    }

    @Override
    protected void validarObjetivo(Posicion objetivo) {
        if (!obtenerPosicion().esAdyacente(objetivo)) {
            throw new DestinoNoAlcanzableException();
        }
    }
}