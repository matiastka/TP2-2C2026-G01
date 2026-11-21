package edu.fiuba.algo3.modelo.tablero.emergencias;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class EmergenciaVek implements Emergencia {
    private static final int DANIO_POR_BLOQUEO = 1;

    private final Unidad vekPendiente;

    public EmergenciaVek(Unidad vekPendiente) {
        this.vekPendiente = vekPendiente;
    }

    @Override
    public void resolver(Casilla casilla, Tablero tablero) {
        casilla.recibirEmergencia(this, tablero);
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
