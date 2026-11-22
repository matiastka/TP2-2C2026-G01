package edu.fiuba.algo3.modelo.unidades.veks.anuncios;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.ataques.Ataque;
import java.util.List;

public class SinAtaquePreparado implements AtaquePreparado {
    @Override
    public void ejecutar(Ataque ataque, Posicion origen, Tablero tablero) {

    }
    @Override
    public List<Posicion> posicionesAmenazadas(Ataque ataque, Posicion origen, Tablero tablero) {
        return List.of();
    }

    @Override
    public String obtenerDescripcion() {
        return "Sin ataque anunciado";
    }
}
