package edu.fiuba.algo3.modelo.tablero.emergencias;

import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import java.util.List;

public class EmergenciaVek implements Emergencia {
    @Override
    public void aceptar(VisitanteVisualCasilla visitante) {
        visitante.visitarEmergenciaVek(this);
    }
    private static final int DANIO_POR_BLOQUEO = 1;

    private final Vek vekPendiente;

    public EmergenciaVek(Vek vekPendiente) {
        this.vekPendiente = vekPendiente;
    }

    public EmergenciaVek(Unidad unidadPendiente) {
        this.vekPendiente = (Vek) unidadPendiente;
    }

    @Override
    public List<Vek> resolver(Casilla casilla, Tablero tablero) {
        casilla.recibirEmergencia(this, tablero);
        if (casilla.obtenerOcupante() == this.vekPendiente) {
            return List.of(this.vekPendiente);
        }
        return List.of();
    }

    public void emergerEn(Casilla casilla, Tablero tablero) {
        this.vekPendiente.colocarseEn(casilla.obtenerPosicion(), tablero);
        casilla.aplicarEfectoA(this.vekPendiente);
        casilla.setEmergencia(new SinEmergencia());
    }

    public void serBloqueadaPor(Ocupante bloqueador) {
        bloqueador.recibirDanio(DANIO_POR_BLOQUEO);
    }
}
