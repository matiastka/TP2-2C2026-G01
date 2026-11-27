package edu.fiuba.algo3.modelo.partida;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.tablero.BuscadorDeEmergencias;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;

public class CasillasParaEmergencia implements BuscadorDeEmergencias {

    private final List<Posicion> posiciones = new ArrayList<>();

    @Override
    public void encontrarCasillaParaEmergencia(Casilla casilla) {
        this.posiciones.add(casilla.obtenerPosicion());
    }

    public List<Posicion> primeras(int cantidad) {
        return List.copyOf(this.posiciones.subList(0, Math.min(cantidad, this.posiciones.size())));
    }
}