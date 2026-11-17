
package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import java.util.LinkedList;
import java.util.Queue;

public class GestorDeAtaques {
    private final Queue<AtaqueAnunciado> colaDeAtaques = new LinkedList<>();

    public void encolar(AtaqueAnunciado ataque) {
        colaDeAtaques.offer(ataque);
    }

    public void ejecutarFase(Tablero tablero) {
        while (!colaDeAtaques.isEmpty()) {
            AtaqueAnunciado ataque = colaDeAtaques.poll();
            ataque.ejecutar(tablero);
        }
    }
}