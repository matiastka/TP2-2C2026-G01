package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.unidades.Unidad;

public class EmergenciaVek implements Emergencia {
    private final Unidad vekPendiente;

    public EmergenciaVek(Unidad vekPendiente) {
        this.vekPendiente = vekPendiente;
    }

    @Override
    public boolean estaActiva() {
        return true;
    }

    @Override
    public void resolver(Casilla casilla, Tablero tablero) {
        if (casilla.estaLibre()) {
            this.vekPendiente.colocarseEn(casilla.obtenerPosicion(), tablero);
            tablero.enCasilla(casilla.obtenerPosicion()).aplicarEfectoA(this.vekPendiente);
            casilla.setEmergencia(new SinEmergencia());
            return;
        }
        casilla.obtenerOcupante().recibirDanio(1);
    }
}
