package edu.fiuba.algo3.modelo.unidades.ataques.efectos;

import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;

public class EfectoNulo implements EfectoImpacto {
    @Override
    public void aplicar(Ocupante ocupante, Posicion posicionImpacto, Direccion direccion, Tablero tablero) {
        // Sin efecto adicional
    }
}