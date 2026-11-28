package edu.fiuba.algo3.vistas.seleccion;

import java.util.List;

import edu.fiuba.algo3.modelo.tablero.BuscadorDeObjetivos;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;

class BuscadorObjetivosDeMech implements BuscadorDeObjetivos {
    private final Mech mech;
    private final List<Posicion> objetivos;

    BuscadorObjetivosDeMech(Mech mech, List<Posicion> objetivos) {
        this.mech = mech;
        this.objetivos = objetivos;
    }

    @Override
    public void encontrarMech(Casilla casilla) {
        Posicion posicion = casilla.obtenerPosicion();
        if (posicion.distanciaA(this.mech.obtenerPosicion()) <= this.mech.obtenerRango()
                && !posicion.equals(this.mech.obtenerPosicion())) {
            this.objetivos.add(posicion);
        }
    }

    @Override
    public void encontrarEdificio(Casilla casilla) {
        Posicion posicion = casilla.obtenerPosicion();
        if (posicion.distanciaA(this.mech.obtenerPosicion()) <= this.mech.obtenerRango()) {
            this.objetivos.add(posicion);
        }
    }
}
