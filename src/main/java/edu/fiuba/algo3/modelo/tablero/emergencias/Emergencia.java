package edu.fiuba.algo3.modelo.tablero.emergencias;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public interface Emergencia {
    void resolver(Casilla casilla, Tablero tablero);
}
