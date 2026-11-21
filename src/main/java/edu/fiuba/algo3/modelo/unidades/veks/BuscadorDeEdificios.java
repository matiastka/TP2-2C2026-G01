package edu.fiuba.algo3.modelo.unidades.veks;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.tablero.BuscadorDeObjetivos;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;

class BuscadorDeEdificios implements BuscadorDeObjetivos {

    private final List<Posicion> edificios = new ArrayList<>();

    @Override
    public void encontrarEdificio(Casilla casilla) {
        this.edificios.add(casilla.obtenerPosicion());
    }

    List<Posicion> obtenerEdificios() {
        return this.edificios;
    }
}
