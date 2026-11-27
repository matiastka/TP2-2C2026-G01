package edu.fiuba.algo3.modelo.unidades.veks.unidadesVek;

import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;
import edu.fiuba.algo3.modelo.excepciones.DestinoNoAlcanzableException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.ataques.AtaqueCuerpoACuerpo;
import edu.fiuba.algo3.modelo.unidades.ataques.efectos.EfectoNulo;
import edu.fiuba.algo3.modelo.unidades.movimientos.MovimientoTerrestre;
import edu.fiuba.algo3.modelo.unidades.veks.*;

public class Saltador extends Vek {
    @Override
    public void aceptar(VisitanteVisualCasilla visitante) {
        visitante.visitarSaltador(this);
    }
    public Saltador() {
        super(2, 3, new MovimientoTerrestre(), new AtaqueCuerpoACuerpo(2, new EfectoNulo()));
    }

    @Override
    protected void validarObjetivo(Posicion objetivo) {
        if (!obtenerPosicion().esAdyacente(objetivo)) {
            throw new DestinoNoAlcanzableException();
        }
    }
}