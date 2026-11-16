package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EfectoNulo implements EfectoImpacto {
    @Override
    public void aplicar(Ocupante ocupante, Posicion posicionImpacto, Direccion direccion, Tablero tablero) {
        // Sin efecto adicional
    }
}