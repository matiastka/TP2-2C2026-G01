package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.ataques.AtaqueLineaRecta;
import edu.fiuba.algo3.modelo.unidades.excepciones.DestinoNoAlcanzableException;
import edu.fiuba.algo3.modelo.unidades.ataques.efectos.EfectoContaminar;
import edu.fiuba.algo3.modelo.unidades.movimientos.MovimientoTerrestre;

public class Luciernaga extends Vek {
    @Override
    public void aceptar(VisitanteVisualCasilla visitante) {
        visitante.visitarLuciernaga(this);
    }
    public Luciernaga() {
        super(3, 2, new MovimientoTerrestre(), new AtaqueLineaRecta(1, new EfectoContaminar()));
    }

    @Override
    protected void validarObjetivo(Posicion objetivo) {
        if (!obtenerPosicion().estaEnLineaRectaA(objetivo, 1)) {
            throw new DestinoNoAlcanzableException();
        }
    }

}
