package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.AtaqueLineaRecta;
import edu.fiuba.algo3.modelo.unidades.DestinoNoAlcanzableException;
import edu.fiuba.algo3.modelo.unidades.EfectoContaminar;
import edu.fiuba.algo3.modelo.unidades.MovimientoTerrestre;

public class Luciernaga extends Vek {
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
