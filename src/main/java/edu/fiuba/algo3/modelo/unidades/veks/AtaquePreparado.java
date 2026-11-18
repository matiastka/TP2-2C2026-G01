package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Ataque;

public interface AtaquePreparado {
    void ejecutar(Ataque ataque, Posicion origen, Tablero tablero);
}
