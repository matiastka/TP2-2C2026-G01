package edu.fiuba.algo3.modelo.tablero.emergencias;

import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import java.util.List;

public class SinEmergencia implements Emergencia {
    @Override
    public void aceptar(VisitanteVisualCasilla visitante) {
        visitante.visitarSinEmergencia();
    }

    @Override
    public List<Vek> resolver(Casilla casilla, Tablero tablero) {
        return List.of();
    }
}
