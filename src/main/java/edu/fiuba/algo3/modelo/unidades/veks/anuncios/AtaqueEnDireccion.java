package edu.fiuba.algo3.modelo.unidades.veks.anuncios;

import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.ataques.Ataque;

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
