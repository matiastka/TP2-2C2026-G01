package edu.fiuba.algo3.modelo.partida;

import java.util.List;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.veks.InteligenciaArtificialVek;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;

public class Turno {

    private final List<Vek> veks;
    private final InteligenciaArtificialVek ia;

    public Turno(List<Vek> veks, InteligenciaArtificialVek ia) {
        this.veks = veks;
        this.ia = ia;
    }

    public void faseVek(Tablero tablero) {
        for (Vek vek : this.veks) {
            this.ia.decidirTurno(vek, tablero);
        }
    }

    public void faseResolucion(Tablero tablero) {
        for (Vek vek : this.veks) {
            vek.resolverAtaque(tablero);
        }
    }

    public void faseEmergencia(Tablero tablero) {
        tablero.resolverEmergencias();
    }
}
