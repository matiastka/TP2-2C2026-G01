package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Ataque;

public class AtaqueEnDireccion implements AtaquePreparado {
    private final Direccion direccion;

    public AtaqueEnDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    @Override
    public void ejecutar(Ataque ataque, Posicion origen, Tablero tablero) {
        ataque.ejecutar(origen, this.direccion, tablero);
    }
}
