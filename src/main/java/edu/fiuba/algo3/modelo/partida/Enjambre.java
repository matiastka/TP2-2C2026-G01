package edu.fiuba.algo3.modelo.partida;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.veks.ia.InteligenciaArtificialVek;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;

public class Enjambre {

    private final List<Vek> veks;
    private final InteligenciaArtificialVek ia;
    private int eliminados;

    public Enjambre(List<Vek> veks, InteligenciaArtificialVek ia) {
        this.veks = new ArrayList<>(veks);
        this.ia = ia;
        this.eliminados = 0;
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
        this.veks.addAll(tablero.resolverEmergencias());
    }
    public void retirarDestruidos(Tablero tablero) {
        for (Vek vek : List.copyOf(this.veks)) {
            if (vek.retirarseSiEstaDestruida(tablero)) {
                this.veks.remove(vek);
                this.eliminados++;
            }
        }
    }

    public int cantidadDeEliminados() {
        return this.eliminados;
    }

    public List<Vek> integrantes() {
        return List.copyOf(this.veks);
    }
}