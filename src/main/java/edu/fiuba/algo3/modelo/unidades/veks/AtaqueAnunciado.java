
package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Tablero;

public class AtaqueAnunciado {
    private final Vek atacante;

    public AtaqueAnunciado(Vek atacante) {
        this.atacante = atacante;
    }

    public void ejecutar(Tablero tablero) {
        if (atacante.obtenerVida() <= 0) return;
        atacante.ejecutarAtaquePreparado(tablero);
    }
}