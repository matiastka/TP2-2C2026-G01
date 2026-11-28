package edu.fiuba.algo3.modelo.tablero.emergencias;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import java.util.List;

public class EmergenciaVek implements Emergencia {
    private static final int DANIO_POR_BLOQUEO = 1;

    private final Vek vekPendiente;

    public EmergenciaVek(Vek vekPendiente) {
        this.vekPendiente = vekPendiente;
    }

    @Override
    public List<Vek> resolver(Casilla casilla, Tablero tablero) {
        return casilla.recibirEmergencia(this, tablero);
    }

    @Override
    public boolean estaActiva() {
        return true;
    }

    public List<Vek> emergerEn(Casilla casilla, Tablero tablero) {
        this.vekPendiente.colocarseEn(casilla.obtenerPosicion(), tablero);
        casilla.aplicarEfectoA(this.vekPendiente);
        casilla.setEmergencia(new SinEmergencia());
        return List.of(this.vekPendiente);
    }

    public void serBloqueadaPor(Ocupante bloqueador) {
        bloqueador.recibirDanio(DANIO_POR_BLOQUEO);
    }
}