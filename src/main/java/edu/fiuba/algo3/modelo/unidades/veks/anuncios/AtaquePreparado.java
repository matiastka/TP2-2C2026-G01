package edu.fiuba.algo3.modelo.unidades.veks.anuncios;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.ataques.Ataque;

public interface AtaquePreparado {
    void ejecutar(Ataque ataque, Posicion origen, Tablero tablero);
}
