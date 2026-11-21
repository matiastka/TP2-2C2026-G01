package edu.fiuba.algo3.modelo.unidades.movimientos;

import java.util.List;

import edu.fiuba.algo3.modelo.tablero.BuscadorDeDestinos;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;

public class AgregadorDeDestinos implements BuscadorDeDestinos {

    private final List<Posicion> destinos;

    public AgregadorDeDestinos(List<Posicion> destinos) {
        this.destinos = destinos;
    }

    @Override
    public void encontrarTerrenoSeguro(Casilla casilla) {
        this.destinos.add(casilla.obtenerPosicion());
    }
}
